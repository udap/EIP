pragma solidity ^0.4.24;


contract Proxy {

    constructor() payable public{

    }

    function currentLogicAddress() view public returns(address);

    //all other functions goes to here
    //public should be safe cause public function copys calldata into memory as 'external' and then jump to its logic like 'internal'
    //fallback function can't be invoked like internal so that public would be OK
    function () payable public {
        address _delegateTo = currentLogicAddress();
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
