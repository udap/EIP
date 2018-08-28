pragma solidity ^0.4.0;

contract BaseData {
    constructor() payable public{
    }

    bytes32 private constant logicPosition = keccak256(abi.encodePacked(keccak256(abi.encode("logicPosition"))));//keccak twice to avoid hash collision than normal mapping
    bytes32 private constant currentLogicPosition = keccak256(abi.encodePacked(keccak256(abi.encode("currentLogicPosition"))));
    bytes32 private constant constructorPosition = keccak256(abi.encodePacked(keccak256(abi.encode("constructorPosition"))));

    function setAddress(bytes32 _version, address _input) internal{
        bytes32 slot = calculateSlot(_version);
        assembly {
            sstore(slot, _input)
        }
    }

    function getAddress(bytes32 _version) internal view returns(address){
        address ret;
        bytes32 slot = calculateSlot(_version);
        assembly {
            ret := sload(slot)
        }
        return ret;
    }

    function deleteAddress(bytes32 _version) internal{
        bytes32 slot = calculateSlot(_version);
        assembly {
            sstore(slot, 0x0000000000000000000000000000000000000000000000000000000000000000)
        }
    }


    function calculateSlot(bytes32 _version) internal pure returns (bytes32){
        return keccak256(abi.encode(bytes32(_version), logicPosition));
    }

    function setCurrent(bytes32 _version) internal {
        bytes32 slot = currentLogicPosition;
        assembly {
            sstore(slot, _version)
        }
    }

    function getCurrent() internal view returns(bytes32){
        bytes32 ret;
        bytes32 slot = currentLogicPosition;
        assembly {
            ret := sload(slot)
        }
        return ret;
    }

    function setConstructor() internal {
        bytes32 slot = constructorPosition;
        bool b = true;
        assembly {
            sstore(slot, b)
        }
    }

    function getConstructor() internal view returns(bool){
        bool ret;
        bytes32 slot = constructorPosition;
        assembly {
            ret := sload(slot)
        }
        return ret;
    }
}
