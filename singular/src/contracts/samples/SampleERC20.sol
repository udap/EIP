pragma solidity ^0.4.24;

import "openzeppelin-solidity/contracts/token/ERC20/StandardToken.sol";
import "../ERC20/IERC20.sol";


contract SampleERC20 is StandardToken {
    string public name = "SampleERC20";
    string public symbol = "SampleERC20";
    uint public decimals = 2;
    uint public INITIAL_SUPPLY = 100000000;

    constructor() public {
        totalSupply_ = INITIAL_SUPPLY;
        balances[msg.sender] = INITIAL_SUPPLY;
    }

    function transfer(address _to, uint256 _value) public returns (bool) {
        require(_value <= balances[msg.sender], "sender's balance is too low");
        require(_to != address(0), "receiver was null");

        balances[msg.sender] = balances[msg.sender].sub(_value);
        balances[_to] = balances[_to].add(_value);
        emit Transfer(msg.sender, _to, _value);
        return true;
    }

}
