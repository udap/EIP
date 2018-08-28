pragma solidity ^0.4.0;

import "./BytesLib.sol";
import "./Storage.sol";

contract Logic {
    /*constructor(address _storage,bytes memory _constructorCalldata) payable public{
        Storage(_storage).setLogicAddressAndActivate(bytes32(0x01),address(this),_constructorCalldata,true);
    }*/

}


contract V1 is Logic{
    /*constructor(address _storage,uint256 _state) Logic(_storage,abi.encodeWithSignature("init(uint256)",_state)) public{

    }*/
    uint256 internal mem;
    function init(uint256 _state) payable public{
        mem = _state;

    }
    function get() public view returns(uint256){
        return mem;
    }

 /*   function init(bytes _data) public{

        mem = _state;

    }*/
}