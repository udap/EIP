pragma solidity ^0.4.0;

import "../AssetOwner.sol";
import "../../node_modules/openzeppelin-solidity/contracts/AddressUtils.sol";
import "../SmarContractAssetOwner.sol";
import "../../node_modules/openzeppelin-solidity/contracts/math/SafeMath.sol";
import "../../node_modules/openzeppelin-solidity/contracts/ReentrancyGuard.sol";

contract AssetOwnerImpl is AssetOwner, AddressUtils, SafeMath, ReentrancyGuard{
    constructor(address _addr){

    }

    //ownerAddress could be an EOA address or SmartContract address which must implements function isAuthorized(address _person, address _singular) view external returns(bool)
    address internal ownerAddress;

    mapping(address => bool) ownedSingulars;
    uint256 ownedSingularsAmount;

    function singularAdded(Singular _added) internal{
        require(!hasSingular(_added));
        ownedSingulars[_added] = true;
        ownedSingularsAmount = add(ownedSingularsAmount,uint256(1));
    }

    function singularRemoved(Singular _added)internal{
        require(hasSingular(_added));
        ownedSingulars[_added] = false;
        ownedSingularsAmount = sub(ownedSingularsAmount,uint256(1));
    }

    function hasSingular(Singular _singular) public view returns(bool){
        return ownedSingulars[_singular];
    }

    function ownerAddress() view external returns(address){
        return ownerAddress;
    }

    function isAuthorized(address _person) view external returns(bool){
        if(isContract(ownerAddress)){
            return SmarContractAssetOwner(ownerAddress).authorize(_person);
        }else{
            return ownerAddress == _person;
        }
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>>send-async
    function sendAsync(Singular _singular, AssetOwner _to, uint256 _expiry, bytes32 _reason) ownsSingular nonReentrant external{
        _singular.approve();
    }


    function offer(Singular _token, AssetOwner _from, uint256 _expiry, bytes32 _reason) external returns(bool){
        emit Offered(msg.sender);
        //customized later
    }

    //I still not agree with you Ran Bing. Since I think EOA/Multisig is behind AssetOwner, those address should not send any request to singular directly
    //The only way is send request to your AssetOwner and your 'wallet' bypass/redirect your request to that singular

    //if you agree/refuse maliciously, you will lose your ETH and slow down the main-net if you like :)
    function agree(Singular _token) external {
        _token.accept();
    }

    function refuse(Singular _token) external {
        _token.reject();
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<<send-async

    //>>>>>>>>>>>>>>>>>>>>>>>>>>send-sync

    function send(Singular _singular) ownsSingular nonReentrant external{
        _singular.sendTo;
    }



    //customize your own logic, like white-black-list
    function receive(Singular _token,bytes32 _reason) nonReentrant external returns(bool){
        emit Receive(msg.sender);
        //customized later
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<<send-sync




    function sent(Singular _singular) nonReentrant external returns(bool){
        singularRemoved(_singular);
    }

    function received(Singular _singular) nonReentrant external returns(bool){
        singularAdded(_singular);
    }

    modifier ownsSingular(Singular _singular){
        require(hasSingular(_singular));
        _;
    }
}
