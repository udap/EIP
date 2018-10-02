pragma solidity ^0.4.24;

contract Initialized {

    bool Initialized_inited;

    // make a random enough slot number to store who can call the unconstructed modifier
    bytes32 private constant initPermission = keccak256(abi.encodePacked(keccak256(abi.encode("initPermission"))));


    function getInitPermission() internal view returns(address){
        address ret;
        bytes32 slot = initPermission;
        assembly {
            ret := sload(slot)
        }
        return ret;
    }

    modifier unconstructed(){
        require(msg.sender == getInitPermission(), "the msg.sender was not permitted to call unconstructed() modifier");
        Initialized_inited = true;
        _;
    }

    modifier constructed(){
        require(Initialized_inited == true, "object not initialized");
        _;
    }
}

