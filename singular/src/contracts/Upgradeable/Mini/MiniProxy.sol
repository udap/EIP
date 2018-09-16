pragma solidity ^0.4.24;

import "./MiniRegistry.sol";

contract MiniProxy {
    constructor(address _toRegistry, address _initPermission) public payable{
        setLogicPosition(_toRegistry);
        setInitPermission(_initPermission);
    }

    //delegate to somewhere
    bytes32 private constant currentLogicPosition = keccak256(abi.encodePacked(keccak256(abi.encode("currentLogicPosition"))));
    //who can knock out the init() function
    bytes32 private constant initPermission = keccak256(abi.encodePacked(keccak256(abi.encode("initPermission"))));

    function setLogicPosition(address _toRegistry) internal {
        bytes32 slot = currentLogicPosition;
        assembly {
            sstore(slot, _toRegistry)
        }
    }

    function getLogicPosition() internal view returns(address){
        address ret;
        bytes32 slot = currentLogicPosition;
        assembly {
            ret := sload(slot)
        }
        return ret;
    }

    function setInitPermission(address _initPermission) internal {
        bytes32 slot = initPermission;
        assembly {
            sstore(slot, _initPermission)
        }
    }

    //all other functions goes to here
    //public should be safe cause public function copys calldata into memory 
    //as 'external' and then jump to its logic like 'internal'
    //fallback function can't be invoked like internal so that public would be OK
    function () payable public {
        address registry = getLogicPosition();
        require(registry != address(0),"you must set delegate first");

        address _delegateTo = MiniRegistry(registry).currentLogic();

        assembly {
            let ptr := mload(0x40)
            calldatacopy(ptr, 0, calldatasize)
            let result := delegatecall(gas, _delegateTo, ptr, calldatasize, 0, 0)
            let size := returndatasize
            returndatacopy(ptr, 0, size)

            switch result
            case 0 { revert(ptr, size) }
            default { return(ptr, size) }
        }
    }
}
