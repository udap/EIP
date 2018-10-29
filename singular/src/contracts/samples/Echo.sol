pragma solidity ^0.4.24;

import "./Logger.sol";

// Modified Greeter contract. Based on example at https://www.ethereum.org/greeter.
// For experiments only...
contract Mortal {
    /* Define variable owner of the type address*/
    address owner;

    /* this function is executed at initialization and sets the owner of the contract */
    constructor() public { owner = msg.sender; }

    /* Function to recover the funds on the contract */
    function kill() public {
        require(msg.sender == owner, "only the owner can kill the contract");
        selfdestruct(owner);
    }
}


contract Echo is Mortal, Logger {

    /* define variable greeting of the type string */
    string greeting;

    /* this runs when the contract is executed */
    constructor(string _greeting) public {
        greeting = _greeting;
    }

    function newGreeting(string _greeting) public {
        emit Modified(greeting, _greeting, greeting, _greeting);
        greeting = _greeting;
    }

    function log() public {
        logInt("an integer", 1001);
        logBytes32("bytes32", 0x420042);
        logAddress("msg.sender", msg.sender);
        logString("hello", "abcd 你好再见");
        logBool("yes/no", true);
    }

    /* main function */
    function greet() public constant returns (string) {
        return greeting;
    }

//    function greet2() constant returns (string) {
//        return greeting;
//    }

    /* we include indexed events to demonstrate the difference that can be
    captured versus non-indexed
    */
    event Modified(
        string indexed oldGreetingIdx,
        string indexed newGreetingIdx,
        string oldGreeting,
        string newGreeting
    );
}
