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
}
