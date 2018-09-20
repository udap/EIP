pragma solidity ^0.4.24;

import '../Tradable.sol';
import './IDebit.sol';
import "../ISingularWallet.sol";
// import './DebitFactory.sol';

/**

The ides is to create an autonomous money sum that can be used in trading.


todo: needs to work with some kind of issuer.

@author Bing Ran<bran@udap.io>
*/
contract DebitBasic is IDebit, Tradable {

    uint256 faceValue;
    DebitBasic public whoCanDeposit;
    DebitBasic public whoCanDeduct;
    uint256 deductionLimit;
    address currencyType_;
    
    // XXX: fill up parent constructor
    constructor(
        string _symbol,          ///< currency symbol
        address _currencyType,           ///< currency type
        ISingularWallet _wal     ///< owner
    ) 
    Tradable (
        "DebitBasic", 
        _symbol, 
        "DebitBasic", 
        "", 
        0,
        _currencyType,
        _wal
    )
    public
    {
    }

    function currencyType()
    public view
    returns(
        address
    ){
        return currencyType_;
    }


    function allowDeposit(DebitBasic who) 
    ownerOnly 
    external {
        whoCanDeposit = who;
    }
    
    function allowDeduct(DebitBasic who, uint256 howMuch) 
    ownerOnly 
    external {
        whoCanDeduct = who;
        deductionLimit = howMuch;
    }
    
    /**
     * the net value of this coin container.
     */
    function denomination()
    public view
    returns(
        uint256 value           ///< the amount of tokens in this packet   
    ) 
    {
        return faceValue;
    }
    
    
    /**
     * To transfer some tokens to another coin of the same ERC20 type.
     * The owner of the coin can be different. 
     * 
     * The function is for the owner of this account.
     * 
     * Must be carefully designed to ensure proper permission. 
     * 
     * The caller MUST have granted this the permissoin to make the deposit
     * 
     */
    function transfer(
        IDebit coin,     ///< the receiving holder, which must be of the same
                        ///< type. It must be set up to allow this coind to make
                        ///< deposit to 
        uint256 amount          ///< how many units of tokens to transfer
    )
    public
    sameTokenType(another)
    permitted(msg.sender, "transfer", currentOwner)       
    {
        DebitBasic another = DebitBasic(coin);
        require(another.whoCanDeposit() == this, "this is not allowed to put money in the arg");
        require(faceValue >= amount, "out of faceValue");
        // allow receiver to deduct from this
        whoCanDeduct = another;
        deductionLimit = amount;
        uint256 c = another.denomination();
        // faceValue -= amount;
        another.depositAndDeductBack(amount);
        require(another.denomination() == c + amount, "an overflow may have happened");
        delete whoCanDeduct; // double check
        delete deductionLimit;
    }
    
    /**
     * This method realize atomic balance transfer between two accounts.
     * The amount credited to this account is drawn from the caller's account. 
     * 
     * The caller MUST have been granted to the permissoin to make the deposit and
     * the caller MUST grant this account the permissoin to deduct its balance. 
     * 
     * This function is designed to be called between two accounts as part of transfer.
     * It cannot be called directly by owners of of any debbit account. 
     * 
     * XXX the scheme here is delicate and requires extensive tests.
     */
    function depositAndDeductBack(uint256 amount ) 
    public 
    mutuallyAgreed(DebitBasic(msg.sender), this)
    balanced(this, whoCanDeposit) // accounting MUST be balanced
    {
        uint c = faceValue;
        faceValue += amount;
        require(faceValue >= c, "an overflow may have happened");
        
        // noted that the caller == whoCanDeposit
        c = whoCanDeposit.denomination(); // take a snopshot of the balance before deduction.
        whoCanDeposit.deduct(amount);
        require(whoCanDeposit.denomination() == c - amount, "deduction did not happen");
        delete whoCanDeposit;
    }
    
    /**
     * allow another party to reduce the denomination. This amount is permanently
     * gone. It must be used together with another deposit on the counterparty
     * to balance the accounting.
     * 
     * This function is designed to be called between two accounts as part of transfer.
     * It cannot be called directly by owners of of any debbit account. 

     * The message sender must be set up properly to use this method. 
     */
    function deduct(uint256 amount ) 
    
    public
    {
        require(msg.sender == address(whoCanDeduct), "sender is not allowed to deduct");
        require(deductionLimit >= amount, "deduction exceeds the limit");
        require(faceValue >= amount, "out of faceValue");
        
        faceValue -= amount;
        delete whoCanDeduct;
        delete deductionLimit;
    }
    
    
    
    /**
     * To dump the all the coin value from the argument to this coin container.
     * The owners must be the same and the coin types must be the same.
     * 
     * An external API for account owners.
     */
    function merge(
        IDebit coin     ///< the coin must allow this account to deduct all denomination
    )
    public
    sameTokenType(coin)
    sameOwner(coin)
    permitted(msg.sender, "merge", currentOwner) 
    balanced(this, coin)
    returns(
        uint256 updatedfaceValue
    )
    {
        DebitBasic other = DebitBasic(coin);
        uint256 amount = coin.denomination();
        faceValue += amount;
        other.deduct(amount);
        return faceValue;
    }

    function split(
        uint256 amount      ///< the value in the new coin
    )
    public
    returns(
        IDebit          ///< the spawned child account
    ) {
        revert("not implemented");
    }


    //    /**
//     * this creates a new Debit of the same type and allocate some value to it from this
//     * coin.
//     */
//    function split(
//        uint256 amount      ///< the value in the new coin
//    )
//    public
//    permitted(msg.sender, "split", currentOwner)
//    returns(
//        IDebit          ///< the spawned child coin
//    )
//    {
//        require(this.denomination() >= amount, "not enough faceValue");
//        ISingularWallet wal = currentOwner;
//        DebitBasic newCoin = DebitFactory.newDebitBasic("DebitBasic", tokenTypeAddr, wal);
//        newCoin.allowDeposit(this);
//        whoCanDeduct = newCoin;
//        newCoin.depositAndDeductBack(amount);
//        return this;
//    }
//
    modifier sameTokenType(IDebit t) {
        require(ISingular(t).tokenType() == this.tokenType(), "The currency types are different");
        _;
    }

    modifier sameOwner(IDebit t) {
        require(ISingular(t).owner() == this.owner(), "The debit owners are different");
        _;
    }
    
    modifier balanced(IDebit a, IDebit b) {
        uint256 preTxBalance = a.denomination() + b.denomination();
        _;
        uint256 postTxBalance = a.denomination() + b.denomination();
        require(preTxBalance == postTxBalance, "debit accounts not balanced after tx");
    }
    
    modifier mutuallyAgreed(
        DebitBasic from, 
        DebitBasic to 
        )
        {
            require(from.whoCanDeduct() == to, 
            "the receiver has not been granted permission to deduct from sender");
            require(to.whoCanDeposit() == from, 
            "the sender has not been granted permission to deposit to the receiver");
            _;
        }

}