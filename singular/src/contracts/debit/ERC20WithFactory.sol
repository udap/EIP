pragma solidity ^0.4.24;

import './IDebit.sol';
import '../ISingularWallet.sol';

contract ERC20WithFactory {
    function name() public view returns (string);
    function symbol() public view returns (string);
    function totalSupply() public constant returns (uint);
    function balanceOf(address tokenOwner) public constant returns (uint balance);
    // function allowance(address tokenOwner, address spender) public constant returns (uint remaining);
    function transfer(address to, uint tokens) public returns (bool success);
    // function approve(address spender, uint tokens) public returns (bool success);
    // function transferFrom(address from, address to, uint256 tokens) public returns (bool success);


    /**
    to create a debit account held by the wallet with some cash in it, from the caller's holdings
    */
    function newDebit(
        ISingularWallet wallet,      ///< the owner of the new debit
        uint256 denomination             ///< how much to put in the debit card
    )
    public
    returns(
        IDebit debit
    );

//    /**
//     * create a debit account from some balance of the message caller
//     */
//    function split(
//        IDebit acct,
//        uint amount
//    )
//    public
//    returns(
//        IDebit
//    );


    /////
    event Transfer(address indexed from, address indexed to, uint256 value);
}
