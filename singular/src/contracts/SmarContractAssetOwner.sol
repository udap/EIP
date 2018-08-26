pragma solidity ^0.4.0;

interface SmarContractAssetOwner {
    function authorize(address _person) view external returns(bool);
}
