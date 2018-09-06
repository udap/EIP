pragma solidity ^0.4.0;

contract Initialized {

    bool Initialized_inited;
    address Initialized_initializer;

    constructor (address _initializer) public payable{
        Initialized_initializer = _initializer;
    }

    modifier unconstructed(address _initializer){
        require(Initialized_initializer == _initializer);
        require(Initialized_inited == false);
        Initialized_inited = true;
        _;
    }

    modifier constructed(){
        require(Initialized_inited == true);
        _;
    }
}
