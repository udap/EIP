pragma solidity ^0.4.24;

import "./IDebit.sol";
import "./IERC20DebitFactory.sol";
import "../ISingularWallet.sol";
import "../ISingular.sol";
import "../Tradable.sol";



/**
 * @title A holder of ERC20 token values owned by by some party
 * 
 * The value is the denomination of the coin.
 * 
 */
contract ERC20Debit is IDebit, Tradable {

    function contractName()
    external
    view
    returns(
        string              ///< the name of the contract class
    ) {
        return "ERC20Debit";
    }

    /// the underlying erc20 type
    IERC20DebitFactory private _erc20Factory;

    constructor(
        IERC20DebitFactory addr,
        ISingularWallet wal
    )
    Tradable(
        addr.whatERC20().name(),
        addr.whatERC20().symbol(),
        "ERC20Debit contract",
        "",
        0,
        addr,
        wal
    )
    public
    {
        _erc20Factory = addr;
    }


    function currencyType()
    public view
    returns(
        address
    )
    {
        return _erc20Factory;
    }

    /**
     * the net value of this debit container.
     */
    function denomination()
    public view
    returns(
        uint256 value           ///< the amount of tokens in this packet   
    ) 
    {
        return _erc20Factory.whatERC20().balanceOf(this);
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
    public
    sameTokenType(another)
    ownerOnly
    {
        _erc20Factory.whatERC20().transfer(address(another), amount);
    }

    /**
     * To dump the all the coin value from the argument to this coin container.
     * The owners must be the same and the coin types must be the same.
     */
    function merge(
        IDebit coin
    )
    public
    sameTokenType(coin)
    sameOwner(coin)
    returns(
        uint256 updatedfaceValue
    )
    {
        coin.transfer(this, coin.denomination());
        return denomination();
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
        IDebit newCoin = _erc20Factory.newDebit(wal, amount);
        return newCoin;
    }
    
    modifier sameTokenType(IDebit t) {
        require(ISingular(t).tokenType() == this.tokenType(), "The currency types are different");
        _;
    }

    modifier sameOwner(IDebit t) {
        require(ISingular(t).owner() == currentOwner, "The debit owners are different");
        _;
    }
}