pragma solidity ^0.4.24;

import "./SimpleSingular.sol";



contract ERC20Interface {
    function name() public view returns (string);
    function symbol() public view returns (string);
    function totalSupply() public constant returns (uint);
    function balanceOf(address tokenOwner) public constant returns (uint balance);
    function allowance(address tokenOwner, address spender) public constant returns (uint remaining);
    function transfer(address to, uint tokens) public returns (bool success);
    function approve(address spender, uint tokens) public returns (bool success);
    function transferFrom(address from, address to, uint256 tokens) public returns (bool success);
}

library ERC20CoinFactory{
    function newInstance(
        ERC20Interface _coinType, 
        ISingularWallet wal
        ) 
        public returns (ERC20Coin) 
        {
        ERC20Coin p = new ERC20Coin(_coinType, wal);
        return p;
    }
}
/**
 * @title A holder of ERC20 token values owned by by some party
 * 
 * The value is the denomination of the coin.
 * 
 */
contract ERC20Coin is SimpleSingular{
    /// the underlying erc20 type
    ERC20Interface _coinType;
    
    // XXX: fill up parent constructor
    constructor(
        ERC20Interface addr,
        ISingularWallet wal
    ) 
    SimpleSingular(
        addr.name(), 
        addr.symbol(), 
        "ERC20coin", 
        "", 
        0,
        wal)
    public
    {
        _coinType = addr;
    }
    
    function coinType()
    public view
    returns(ERC20Interface){
        return _coinType;
    }

    function tokenType() public view returns(address) {
        return _coinType;
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
        return _coinType.balanceOf(this);
    }
    
    /**
     * To transfer some tokens to another coin of the same ERC20 type.
     * The owner of the coin can be different
     */
    function transfer(
        ERC20Coin another,     ///< the receiving holder, which must be of the same
                                ///< erc20 type
        uint256 amount          ///< how many units of tokens to transfer
    )
    public
    returns(
        bool)
    {
        require(another.coinType() == _coinType, "The ERC20 types are different");
        _coinType.transfer(another, amount);
    }
    
    /**
     * To dump the all the coin value from the argument to this coin container.
     * The owners must be the same and the coin types must be the same.
     */
    function merge(ERC20Coin coin)
    public
    returns(
        uint256 updatedBalance
    )
    {
        require(coin.owner() == currentOwner);
        require(coin.coinType() == _coinType, "The ERC20 types are different");
        coin.transfer(this, coin.denomination());
        return _coinType.balanceOf(this);
    }
    
    /**
     * the create a new ERC20Coin of the same type and allocate some value to it from this
     * coin.
     */
    function split(
        uint256 amount      ///< the value in the new coin
    ) 
    public
    returns(
        ERC20Coin          ///< the spawned child coin
    )
    {
        require(this.denomination() >= amount, "not enough balance");
        ISingularWallet wal = currentOwner;
        require(msg.sender == address(wal), "the message sender was not the owner");
        ERC20Coin newCoin = ERC20CoinFactory.newInstance(_coinType, wal);
        _coinType.transfer(newCoin, amount);
        return newCoin;
    }
    
}