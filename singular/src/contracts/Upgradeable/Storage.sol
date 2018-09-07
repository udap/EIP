pragma solidity ^0.4.24;

import "./Proxy.sol";
import "./BaseData.sol";

//not finish yet, will move Storage level common functions to a global smart contract
contract Storage is Proxy, BaseData{
    constructor() payable public{
    }
    /*constructor(address _addr) public{
        setLogicAddressAndActivate(bytes32(0x01),_address);
    }*/

    //you can update specific {_version, _delegateTo} by using 'set'
    function setLogicAddress(bytes32 _version, address _delegateTo) onlyOwner public {
        require(_delegateTo != address(0));
        setLogic(_version,_delegateTo);
    }

    function getLogicAddress(bytes32 _version) onlyOwner public view returns (address){
        return getLogic(_version);
    }

    /*
    option:
            0x00: won't call _initCalldata on provided _delegateTo, and ignore _initCalldata.
            0x01: will call _initCalldata on provided _delegateTo like addition call(), revert in case of call() fails.
            0x02: _initCalldata will be treat as constructor(). will call _initCalldata on provided _delegateTo only at first time on this Storage like option 0x01. If not first, ignore _initCalldata.
            0x03: _initCalldata will be treat as constructor(). will call _initCalldata on provided _delegateTo only at first time on this Storage like option 0x01. If not first, revert().
    note: this function should be invoked while _delegateTo is constructing cause at that time _delegateTo is dispatched/calculated/generated but runtime bytecode and code hash haven't stored to state trie.
    */
    function setLogicAddressAndActivate(bytes32 _version, address _delegateTo, bytes _initCalldata, uint256 option) onlyOwner payable public {
        require(_delegateTo != address(0));
        setLogic(_version,_delegateTo);
        setCurrentVersion(_version);
        callinit(_delegateTo, _initCalldata, option);
    }


    function activateLogic(bytes32 _version, bytes _initCalldata, uint256 option) onlyOwner payable public {
        address logic = getLogic(_version);
        require(logic != address(0x00),"you must register the version and its logic address first");
        setCurrentVersion(_version);
        callinit(logic, _initCalldata, option);
    }

    function deleteLogicAddress(bytes32 _version) onlyOwner public {
        require(getCurrentVersion() != _version);
        deleteLogic(_version);
    }

    function currentLogic() onlyOwner public view returns (address){
        bytes32 version = getCurrentVersion();
        return getLogic(version);
    }


}
