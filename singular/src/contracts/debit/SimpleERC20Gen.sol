pragma solidity ^0.4.24;

import './IDebit.sol';
import '../ISingularWallet.sol';
import "./ERC20Debit.sol";
import "./ERC20WithFactory.sol";



contract SimpleERC20Gen is ERC20WithFactory {

    mapping(address => uint256) internal balances;

    uint256 internal totalSupply_;

    string  name_;
    string  symbol_;

    constructor(string _name, string _symbol, uint _totalSupply) {
        name_ = _name;
        symbol_ = _symbol;
        totalSupply_ = _totalSupply;
    }

    function name() public view returns (string) {
        return name_;
    }
    function symbol() public view returns (string) {
        return symbol_;
    }


    /**
    * @dev Total number of tokens in existence
    */
    function totalSupply() public view returns (uint256) {
        return totalSupply_;
    }

    /**
    * @dev Transfer token for a specified address
    * @param _to The address to transfer to.
    * @param _value The amount to be transferred.
    */
    function transfer(address _to, uint256 _value) public returns (bool) {
        require(_value <= balances[msg.sender]);
        require(_to != address(0));

        balances[msg.sender] -= _value;
        balances[_to] += _value;
        emit Transfer(msg.sender, _to, _value);
        return true;
    }

    /**
    * @dev Gets the balance of the specified address.
    * @param _owner The address to query the the balance of.
    * @return An uint256 representing the amount owned by the passed address.
    */
    function balanceOf(address _owner) public view returns (uint256) {
        return balances[_owner];
    }



    /**
    to create a debit account held by the wallet
    */
    function newDebit(
        ISingularWallet wallet,      ///< the owner of the new debit
        uint256 denomination
    )
    public
    returns(
        IDebit
    )
    {
        IDebit debit = new ERC20Debit(this, wallet);
        if (denomination > 0)
            transfer(debit, denomination);
        return debit;
    }

//    function split(
//        IDebit acct,
//        uint amount
//    )
//    public
//    returns(
//        IDebit
//    )
//    {
//        require(msg.sender == address(acct) ||
//        msg.sender == address(acct.owner()),
//        "sender is not the owner");
//        IDebit to = new ERC20Debit(this, acct.owner());
//        transfer(to, amount);
//        return to;
//    }

}