pragma solidity ^0.4.0;

contract BaseData {
    constructor() payable public{
    }
    /*
    it likes (pseudo)
    mapping(bytes32 version => address logicAddress);
    bytes32 currentVersion;
    bool constructor;
    address owner;

    */

    bytes32 private constant logicPosition = keccak256(abi.encodePacked(keccak256(abi.encode("logicPosition"))));//keccak twice to avoid hash collision than normal mapping
    bytes32 private constant currentLogicPosition = keccak256(abi.encodePacked(keccak256(abi.encode("currentLogicPosition"))));
    bytes32 private constant constructorPosition = keccak256(abi.encodePacked(keccak256(abi.encode("constructorPosition"))));
    bytes32 private constant ownerPosition = keccak256(abi.encodePacked(keccak256(abi.encode("ownerPosition"))));

    function setLogic(bytes32 _version, address _input) internal{
        bytes32 slot = calculateVersionSlot(_version);
        assembly {
            sstore(slot, _input)
        }
    }

    function getLogic(bytes32 _version) internal view returns(address){
        address ret;
        bytes32 slot = calculateVersionSlot(_version);
        assembly {
            ret := sload(slot)
        }
        return ret;
    }

    function deleteLogic(bytes32 _version) internal{
        bytes32 slot = calculateVersionSlot(_version);
        assembly {
            sstore(slot, 0x0000000000000000000000000000000000000000000000000000000000000000)
        }
    }


    function setCurrentVersion(bytes32 _version) internal {
        bytes32 slot = currentLogicPosition;
        assembly {
            sstore(slot, _version)
        }
    }

    function getCurrentVersion() internal view returns(bytes32){
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

    function setOwner(address _owner) internal {
        bytes32 slot = ownerPosition;
        assembly {
            sstore(slot, _owner)
        }
    }

    function getOwner() internal view returns(address){
        address ret;
        bytes32 slot = ownerPosition;
        assembly {
            ret := sload(slot)
        }
        return ret;
    }


//================================================================================

    function calculateVersionSlot(bytes32 _version) internal pure returns (bytes32){
        return keccak256(abi.encode(bytes32(_version), logicPosition));
    }



    function callinit(address _delegateTo, bytes _initCalldata, uint256 option) internal{
        if(option == uint256(0x00)){
            return;
        }
        else if(option == uint256(0x01)){
            require(address(this).call.value(msg.value)(_initCalldata));
            return;
        }
        else if(option == uint256(0x02)){
            if(getConstructor()==false){
                setConstructor();
                require(_delegateTo.call.value(msg.value)(_initCalldata));
                return;
            }else{
                return;
            }
        }
        else if(option == uint256(0x03)){
            if(getConstructor()==false){
                setConstructor();
                require(_delegateTo.call.value(msg.value)(_initCalldata));
                return;
            }else{
                revert();
            }
        }
        revert();
    }

    modifier onlyOwner(){
        require(msg.sender == getOwner());
        _;
    }
}
