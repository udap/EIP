pragma solidity ^0.4.24;

import "./IDebit.sol";
import "./SimpleSingular.sol";
import "./ERC20Interface.sol";
import "./ERC20DebitFactory.sol";
import "./ISingularWallet.sol";
import "./ISingular.sol";



/**
 * @title A holder of ERC20 token values owned by by some party
 * 
 * The value is the denomination of the coin.
 * 
 */
contract ERC20Debit is IDebit, SimpleSingular {
    /// the underlying erc20 type
    ERC20Interface _erc20;
    
    uint256 faceValue;
    ERC20Debit public whoCanWithdraw;
    ERC20Debit public whoCanDeposit;

    
    // XXX: fill up parent constructor
    constructor(
        ERC20Interface addr,
        ISingularWallet wal
    ) 
    SimpleSingular(
        addr.name(),
        addr.symbol(),
        "ERC20Debit contract",
        "",
        0,
        addr,
        wal)
    public
    {
        _erc20 = addr;
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
        return _erc20.balanceOf(this);
    }
    
    /**
     * To transfer some tokens to another coin of the same ERC20 type.
     * The owner of the coin can be different
     */
    function transfer(
        IDebit another,     ///< the receiving holder, which must be of the same
                                ///< erc20 type
        uint256 amount          ///< how many units of tokens to transfer
    )
    sameTokenType(another)
    public
    {
        _erc20.transfer(address(another), amount);
    }
    
    
    /**
     * To dump the all the coin value from the argument to this coin container.
     * The owners must be the same and the coin types must be the same.
     */
    function merge(IDebit coin)
    public
    sameTokenType(coin)
    sameOwner(coin)
    returns(
        uint256 updatedfaceValue
    )
    {
        this.deposit(ERC20Debit(coin), coin.denomination());
        return denomination();
    }
    
       // will this work?
    function deposit(ERC20Debit from, uint256 amount ) public {
        require(msg.sender == address(whoCanDeposit), "sender is not allowed to deposit");
        require(msg.sender == address(from), "sender must be the source account");
        uint d = from.denomination();
        from.deduct(this, amount);
        require(from.denomination() == d - amount, "number does not add up in withdraw");
        uint c = faceValue;
        faceValue += amount;
        require(faceValue >= c, "an overflow may have happened");
    }
    
    function deduct(ERC20Debit from, uint256 amount ) public {
        require(msg.sender == address(whoCanWithdraw), "sender is not allowed to withdraw");
        require(msg.sender == address(from), "sender must be the source account");
        require(faceValue >= amount, "out of faceValue");
        faceValue -= amount;
    }
    
    
    /**
     * the create a new ERC20Debit of the same type and allocate some value to it from this
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
        require(this.denomination() >= amount, "not enough balance");
        ISingularWallet wal = currentOwner;
        require(msg.sender == address(wal), "the message sender was not the owner");
        ERC20Debit newCoin = ERC20DebitFactory.newERC20Debit(_erc20, wal);
        _erc20.transfer(newCoin, amount);
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
}