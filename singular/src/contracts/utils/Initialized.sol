pragma solidity ^0.4.24;

contract Initialized {

    bool Initialized_inited;

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
        require(msg.sender == getInitPermission());
        Initialized_inited = true;
        _;
    }

    modifier constructed(){
        require(Initialized_inited == true);
        _;
    }
}

