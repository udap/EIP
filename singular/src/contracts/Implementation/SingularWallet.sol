pragma solidity ^0.4.0;

import "../ISingular.sol";
import "../ISingularWallet.sol";
import "../../node_modules/openzeppelin-solidity/contracts/AddressUtils.sol";
import "../../node_modules/openzeppelin-solidity/contracts/math/SafeMath.sol";
import "../../node_modules/openzeppelin-solidity/contracts/ReentrancyGuard.sol";

contract SingularWalletImpl is ISingularWallet, AddressUtils, SafeMath, ReentrancyGuard{
    constructor(address _addr){

    }

    //ownerAddress could be an EOA address or SmartContract address which must implements function isAuthorized(address _person, address _singular) view external returns(bool)
    address internal ownerAddress;

    mapping(address => bool) ownedSingulars;
    uint256 ownedSingularsAmount;





    function isAuthorized(address _person) view external returns(bool){
        if(isContract(ownerAddress)){
            return SmarContractAssetOwner(ownerAddress).authorize(_person);
        }else{
            return ownerAddress == _person;
        }
    }


    function sent(ISingular _singular, string _reply) ownsSingular external returns(bool){
        emit SingularTransferred(this,_singular.currentOwner(),now,_reply);
        singularRemoved(_singular);
    }

    function received(ISingular _singular, string _reply) external returns(bool){
        require(_singular.currentOwner() == this);

        //must find a way to tell where it comes from :(
        emit SingularTransferred(address(0),this,now,_reply);
        singularAdded(_singular);
    }

    function offerRejected(ISingular token, string note) external returns(bool){
        //must find a way to tell where it comes from :(
        emit SingularTransferFailed(address(0),this,now,_reply);
    }

    //============================================================================

    function send(ISingularWallet _to, ISingular _singular, string _reason) external{
        _singular.sendTo(wallet, _reason, true,0);
    }

    function sendNotify(ISingularWallet _to, ISingular _singular, string _reason, uint256 _expiry) external{
        require(_expiry > now);
        _singular.sendTo(wallet, _reason, false, _expiry);
    }

    //manually approve a singular
    function approve(ISingularWallet _to, ISingular _singular, string _reason, uint256 expiry ){
        require(_expiry > now);
        emit ApproveSingular(_to, _singular, now,_reason);
        _singular.approveReceiver(_to, _expiry, _reason);
    }

    // called when get an offer
    function offer(ISingular _singular, string _reason) external returns(bool){
        require(_singular.nextOwner() == this);
        emit SingularOffered(_singular.currentOwner(),_singular, now, _reason);
        //customized later
        string _reply;
        _singular.accept(_reply);
        _singular.reject(_reply);
    }

    //if you agree/refuse maliciously, you will lose your ETH and slow down the main-net if you like :)

    //just forward request to singular.accept
    function agree(ISingular _singular, string _reply) external {
        _singular.accept(_reply);
    }

    //just forward request to singular.reject
    function refuse(ISingular _singular, string _reply) external {
        _singular.reject(_reply);
    }

    //============================================================================

    function ownerAddress() view external returns(address){
        return ownerAddress;
    }


    function getAllTokens() view external returns (ISingular[]);

    /**
     get the number of owned tokens
     */
    function numOfTokens() view external returns (uint256);

    /**
     get the token at a specific index.
     */
    function getTokenAt(uint256 idx) view external returns (ISingular);

    //============================================================================

    function singularAdded(ISingular _added) internal{
        require(!hasSingular(_added));
        ownedSingulars[_added] = true;
        ownedSingularsAmount = add(ownedSingularsAmount,uint256(1));
    }

    function singularRemoved(ISingular _added)internal{
        require(hasSingular(_added));
        ownedSingulars[_added] = false;
        ownedSingularsAmount = sub(ownedSingularsAmount,uint256(1));
    }

    function hasSingular(ISingular _singular) public view returns(bool){
        return ownedSingulars[_singular];
    }
    //============================================================================


    modifier ownsSingular(ISingular _singular){
        require(hasSingular(_singular));
        _;
    }



}
