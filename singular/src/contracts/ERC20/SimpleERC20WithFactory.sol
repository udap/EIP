pragma solidity ^0.4.24;

import './IDebit.sol';
import '../ISingularWallet.sol';
import "./ERC20Debit.sol";
import "./IERC20DebitFactory.sol";


/**
@title A Simple ERC20 contract with debit factory method
*/
contract SimpleERC20WithFactory is IERC20DebitFactory {

    mapping(address => uint256) internal balances;

    uint256 internal totalSupply_;

    string  name_;
    string  symbol_;

    constructor(string _name, string _symbol, uint _totalSupply) public {
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
        emit DebitCreated(debit, wallet, denomination);
        return debit;
    }

}