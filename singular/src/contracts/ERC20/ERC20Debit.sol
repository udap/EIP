pragma solidity ^0.4.24;

import "./IDebit.sol";
import "./IERC20DebitFactory.sol";
import "../ISingularWallet.sol";
import "../ISingular.sol";
import "../impl/Tradable.sol";


/**
 * @title A holder of ERC20 token values owned by by some party
 * 
 */
contract ERC20Debit is IDebit, Tradable {
    function contractName() external pure returns(string) {return "ERC20Debit";}

    IERC20 erc20_;

    function init(
        string name,
        IERC20 erc20,
        ISingularWallet wal
    )
    external
    {
        NonTradable.init(
            name,
            erc20.symbol(),
            "ERC20Debit contract",
            "",
            0,
            erc20, // tokenTypeAddress
            wal
        );
        erc20_ = erc20;
//        _erc20Factory = factory;
    }


    function currencyType()
    public view
    returns(
        address
    )
    {
        return erc20_;
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
        return erc20_.balanceOf(this);
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
        erc20_.transfer(address(another), amount);
        // todo
    }

    /**
  called by swap executor to set the new owner, as the last step in swapping
  */
    function swapInOwner(
        ISingularWallet newOwner,
        string note
    )
    external
    initialized
    forTradeExecutor
    max128Bytes(note)
    {
    require(address(newOwner) != address(0), "the newOwner was null");
        theOwner = newOwner;
        ownerPrevious.sent(this, note);
        ownerPrevious = theOwner;
        theOwner.received(this, note);
        reset();
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
//        require(this.denomination() >= amount, "not enough balance");
//        ISingularWallet wal = theOwner;
//        require(msg.sender == address(wal), "the message sender was not the owner");
//        IDebit newCoin = _erc20Factory.newDebit(erc20_, amount);
//        return newCoin;
    }
    
    modifier sameTokenType(IDebit t) {
        require(ISingular(t).tokenType() == this.tokenType(), "The currency types are different");
        _;
    }

    modifier sameOwner(IDebit t) {
        require(ISingular(t).owner() == theOwner, "The debit owners are different");
        _;
    }

    function toITradable() public returns(ITradable) {return this;}

}