pragma solidity ^0.4.0;

import "./BytesLib.sol";
import "./Storage.sol";

contract Logic {
    /*constructor(bytes _data) public{
        bytes memory temp = BytesLib.slice(_data,0,32);
        address _storage = bytesToAddress(temp);

        Storage(_storage).setLogicAddressAndActivate(bytes32(0x01),this);

        bytes memory _calldata = BytesLib.slice(_data,32,_data.length);
        bytes memory _call = abi.encodeWithSignature("init(uint256)", _calldata) ;
        _storage.call(_call);
    }*/

    function bytesToAddress(bytes bys) private pure returns (address addr) {
        assembly {
            addr := mload(add(bys,20))
        }
    }

}


contract V1 is Logic{
/*    constructor(address _storage,uint256 _state) Logic(msg.data) public{
    }
*/
    uint256 internal mem;
    function init(uint256 _state) public{
        mem = _state;

    }
    function get() public view returns(uint256){
        return mem;
    }
}