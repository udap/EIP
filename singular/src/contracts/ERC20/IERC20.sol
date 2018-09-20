pragma solidity ^0.4.24;


/**
 @title A basic erc20 interface

 The basic functions are listed for compiler to understand the interface.
*/
contract IERC20 {
    function name() public view returns (string);
    function symbol() public view returns (string);
    function totalSupply() public constant returns (uint);
    function balanceOf(address tokenOwner) public constant returns (uint balance);
    // function allowance(address tokenOwner, address spender) public constant returns (uint remaining);
    function transfer(address to, uint tokens) public returns (bool success);
    // function approve(address spender, uint tokens) public returns (bool success);
    // function transferFrom(address from, address to, uint256 tokens) public returns (bool success);
}
