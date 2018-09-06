pragma solidity ^0.4.24;

import "../../../node_modules/openzeppelin-solidity/contracts/ownership/Ownable.sol";

contract MiniRegistry is Ownable{

    constructor() public payable{
    }
    mapping(bytes32 => address) versions;
    bytes32 activatedVersion;

    function setLogicAddress(bytes32 _version, address _delegateTo) onlyOwner public {
        require(_delegateTo != address(0));
        versions[_version] = _delegateTo;
    }

    function getLogicAddress(bytes32 _version) public view returns (address){
        return versions[_version];
    }

    function setLogicAddressAndActivate(bytes32 _version, address _delegateTo) onlyOwner payable public {
        require(_delegateTo != address(0));
        versions[_version] = _delegateTo;
        require(_delegateTo != address(0x00),"you must register the version and its logic address first");
        activatedVersion = _version;

    }


    function activateLogic(bytes32 _version) onlyOwner payable public {
        address logic = versions[_version];
        require(logic != address(0x00),"you must register the version and its logic address first");
        activatedVersion = _version;
    }

    function deleteLogicAddress(bytes32 _version) onlyOwner public {
        require(activatedVersion != _version);
        delete versions[_version];
    }

    function currentLogic() onlyOwner public view returns (address){
        return versions[activatedVersion];
    }

    //forward all internal tx to the _delegateTo,  the  _delegateTo must do author check
    function () payable public {
        address _delegateTo = versions[activatedVersion];
        require(_delegateTo != address(0),"you must set delegate first");

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
