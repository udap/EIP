pragma solidity ^0.4.24;

import "../../utils/Ownable.sol";

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
        require(_delegateTo != address(0x00),"you must register the version and its logic address first");
        versions[_version] = _delegateTo;
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

    function currentLogic() public view returns (address){
        return versions[activatedVersion];
    }

}
