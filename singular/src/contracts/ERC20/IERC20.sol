pragma solidity ^0.4.24;


/**
 @title A basic erc20 interface

 The basic functions are listed for compiler to understand the interface.
*/
interface IERC20 {
    function name() external view returns (string);
    function symbol() external view returns (string);
    function totalSupply() external constant returns (uint);
    function balanceOf(address tokenOwner) external constant returns (uint balance);
    // function allowance(address tokenOwner, address spender) external constant returns (uint remaining);
    function transfer(address to, uint tokens) external returns (bool success);
    // function approve(address spender, uint tokens) external returns (bool success);
    // function transferFrom(address from, address to, uint256 tokens) external returns (bool success);
}
