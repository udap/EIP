pragma solidity ^0.4.24;

import './IDebit.sol';
import './SimpleSingular.sol';
import './DebitFactory.sol';


contract DebitBasic is IDebit, SimpleSingular{

    uint256 faceValue;
    DebitBasic public whoCanDeposit;
    DebitBasic public whoCanDeduct;
    
    
    // XXX: fill up parent constructor
    constructor(
        string _symbol,          ///< currency symbol
        address _currencyType,           ///< currency type
        ISingularWallet _wal     ///< owner
    ) 
    SimpleSingular(
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
    
    function allowDeposit(DebitBasic who) ownerOnly external {
        whoCanDeposit = who;
    }
    
    function allowDeduct(DebitBasic who) ownerOnly external {
        whoCanDeduct = who;
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
    permitted(msg.sender, "transfer")       
    {
        DebitBasic another = DebitBasic(coin);
        require(another.whoCanDeposit() == this, "this is not allowed to put money in the arg");
        require(faceValue >= amount, "out of faceValue");
        whoCanDeduct = another;
        uint256 c = another.denomination();
        // faceValue -= amount;
        another.depositAndDeductBack(amount);
        require(another.denomination() == c + amount, "an overflow may have happened");
        delete whoCanDeduct; // double check
    }
    
    /**
     * this method realize atomic balance transfer between two accounts.
     * Theamount credited to this account is drawn from the caller's account. 
     * 
     * The caller MUST have been granted to the permissoin to make the deposit and
     * the caller MUST grant this account the permissoin to deduct its balance. 
     * 
     * XXX the scheme here is delicate and requires extensive tests.
     */
    function depositAndDeductBack(uint256 amount ) 
    public 
    balanced(this, whoCanDeposit)
    {
        require(msg.sender == address(whoCanDeposit), "sender is not allowed to deposit");
        uint c = faceValue;
        faceValue += amount;
        require(faceValue >= c, "an overflow may have happened");
        
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
     * The message sender must be set up properly to use this method. 
     */
    function deduct(uint256 amount ) public {
        require(msg.sender == address(whoCanDeduct), "sender is not allowed to deduct");
        require(faceValue >= amount, "out of faceValue");
        faceValue -= amount;
        delete whoCanDeduct;
    }
    
    
    
    /**
     * To dump the all the coin value from the argument to this coin container.
     * The owners must be the same and the coin types must be the same.
     */
    function merge(
        IDebit coin     ///< the coin must allow this account to deduct all denomination
    )
    public
    sameTokenType(coin)
    sameOwner(coin)  
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
    
    /**
     * this creates a new Debit of the same type and allocate some value to it from this
     * coin.
     */
    function split(
        uint256 amount      ///< the value in the new coin
    ) 
    public
    returns(
        IDebit          ///< the spawned child coin
    )
    {
        require(this.denomination() >= amount, "not enough faceValue");
        ISingularWallet wal = currentOwner;
        require(msg.sender == address(wal), "the message sender was not the owner");
        DebitBasic newCoin = DebitFactory.newDebitBasic("unknown", tokenTypeAddr, wal);
        newCoin.allowDeposit(this); // this won't work due to lack of permission
        whoCanDeduct = newCoin;
        newCoin.depositAndDeductBack(amount);
        return newCoin;
    }
    
    modifier sameTokenType(ISingular t) {
        require(t.tokenType() == this.tokenType(), "The currency types are different");
        _;
    }

    modifier sameOwner(ISingular t) {
        require(t.owner() == this.owner(), "The debit owners are different");
        _;
    }
    
    modifier balanced(IDebit a, IDebit b) {
        uint256 preTxBalance = a.denomination() + b.denomination();
        _;
        uint256 postTxBalance = a.denomination() + b.denomination();
        require(preTxBalance == postTxBalance, "debit accounts not balanced after tx");
    }

}