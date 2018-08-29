pragma solidity ^0.4.0;

import "./Proxy.sol";
import "./BaseData.sol";

//not finish yet, will move Storage level common functions to a global smart contract
contract Storage is Proxy, BaseData{
    constructor() payable public{
    }
    /*constructor(address _addr) public{
        setLogicAddressAndActivate(bytes32(0x01),_address);
    }*/

    function setLogicAddress(bytes32 _version, address _delegateTo) public {
        setAddress(_version,_delegateTo);
    }

    /*
    option:
            0x00: won't call _initCalldata on provided _delegateTo, and ignore _initCalldata.
            0x01: will call _initCalldata on provided _delegateTo like addition call(), revert in case of call() fails.
            0x02: _initCalldata will be treat as constructor(). will call _initCalldata on provided _delegateTo only at first time on this Storage like option 0x01. If not first, ignore _initCalldata.
            0x03: _initCalldata will be treat as constructor(). will call _initCalldata on provided _delegateTo only at first time on this Storage like option 0x01. If not first, revert().
    note: this function should be invoked while _delegateTo is constructing cause at that time _delegateTo is dispatched/calculated/generated but runtime bytecode and code hash haven't stored to state trie.
    */
    function setLogicAddressAndActivate(bytes32 _version, address _delegateTo, bytes _initCalldata, uint256 option) payable public {
        setAddress(_version,_delegateTo);
        setCurrent(_version);
        callinit(_delegateTo, _initCalldata, option);
    }


    function deleteLogicAddress(bytes32 _version) public {
        deleteAddress(_version);
    }

    function hasLogicAddress(bytes32 _version) public view returns(bool){
        address logic= getAddress(_version);
        if(logic != address(0x00)){
            return true;
        }
        return false;
    }

    function activateLogic(bytes32 _version, bytes _initCalldata, uint256 option) public {
        address logic = getAddress(_version);
        require(logic != address(0x00),"you must register the version and its logic address first");
        setCurrent(_version);
        callinit(logic, _initCalldata, option);
    }

    function currentLogicAddress() view public returns (address){
        bytes32 version = getCurrent();
        return getAddress(version);
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
                require(address(this).call.value(msg.value)(_initCalldata));
                return;
            }else{
                return;
            }
        }
        else if(option == uint256(0x03)){
            if(getConstructor()==false){
                setConstructor();
                require(address(this).call.value(msg.value)(_initCalldata));
                return;
            }else{
                revert();
            }
        }
        revert();
    }

}
