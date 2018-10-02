pragma solidity ^0.4.24;

import "../ERC20/ERC20Debit.sol";

contract ERC20DebitFactory {
    function newInstance() public returns (ERC20Debit){
        return new ERC20Debit(); // just an empty instance.
    }
}