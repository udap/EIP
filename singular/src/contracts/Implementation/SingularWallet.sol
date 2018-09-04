pragma solidity ^0.4.24;

import "../ISingular.sol";
import "../ISingularWallet.sol";
import "../../node_modules/openzeppelin-solidity/contracts/AddressUtils.sol";
import "../../node_modules/openzeppelin-solidity/contracts/math/SafeMath.sol";
import "../../node_modules/openzeppelin-solidity/contracts/ReentrancyGuard.sol";

/*

*/

contract SingularWalletImpl is ISingularWallet, ReentrancyGuard{
    constructor(address _owner) public {
        owner = _owner;
    }

    //owner could be an EOA address or SmartContract address which must implements function isAuthorized(address _person, address _singular) view external returns(bool)
    address internal owner;

    mapping(address => bool) ownedSingulars;
    uint256 ownedSingularsAmount;

    function isActionAuthorized(address _caller, bytes32 _action,ISingular singular) view external returns(bool){
        if(AddressUtils.isContract(owner)){
            // TODO: serialize the transferHistory
            revert("not implemented");
            //return MultisiWallet(owner).authorize(_person);
        }else{
            return owner == _caller;
        }
    }

    //=============================callback===============================================
    function sent(ISingular _singular, string _receiverNote) ownsSingular(_singular) external{
        emit SingularTransferred(_singular.previousOwner(),_singular.currentOwner(),_singular,now,_receiverNote);
        singularRemoved(_singular);
    }

    function received(ISingular _singular, string _receiverNote) external{
        require(_singular.currentOwner() == this);
        emit SingularTransferred(_singular.previousOwner(),this,_singular,now,_receiverNote);
        singularAdded(_singular);
    }

    // called when get an offer
    function offer(ISingular _singular, string _senderNote) external{
        require(_singular.nextOwner() == this);
        emit SingularOffered(_singular.currentOwner(),_singular, now, _senderNote);
        //customized later
        /*
        string _receiverNote;
        _singular.accept(_receiverNote);
        _singular.reject(_receiverNote);
        */
    }

    function offerNotify(ISingular _singular, string _senderNote) external{
        require(_singular.nextOwner() == this);
        emit SingularOffered(_singular.currentOwner(),_singular, now, _senderNote);
    }

    function offerRejected(ISingular _singular, string _receiverNote) external{
        emit SingularTransferFailed(_singular.currentOwner(),_singular.nextOwner(),_singular,now,_receiverNote);
    }
    //=============================callback===============================================


    //=============================action===============================================

    function send(ISingularWallet _to, ISingular _singular, string _senderNote) ownsSingular(_singular) external{
        //send contains approve logic so here emit an approve event
        emit SingularReceiverApproved(_to, _singular, now,_senderNote);
        _singular.sendTo(_to, _senderNote, true,0);
    }

    function sendNotify(ISingularWallet _to, ISingular _singular, string _senderNote, uint256 _expiry) ownsSingular(_singular) external{
        //send contains approve logic so here emit an approve event
        require(_expiry > now);
        _singular.sendTo(_to, _senderNote, false, _expiry);
    }

    //manually approve a singular
    function approve(ISingularWallet _to, ISingular _singular, string _senderNote, uint256 _expiry ) ownsSingular(_singular) external{
        require(_expiry > now);
        emit SingularReceiverApproved(_to, _singular, now,_senderNote);
        _singular.approveReceiver(_to, _expiry, _senderNote);
    }

    function burn(ISingular _singular, string _burnMsg) external{
        _singular.burn(_burnMsg);
    }
    //=============================action===============================================

    //=============================reaction===============================================
    //if you agree/refuse maliciously, you will lose your ETH if you like :)

    //just forward request to singular.accept
    function agree(ISingular _singular, string _receiverNote) external {
        _singular.accept(_receiverNote);
    }

    //just forward request to singular.reject
    function reject(ISingular _singular, string _receiverNote) external {
        _singular.reject(_receiverNote);
    }
    //=============================reaction===============================================


    //=============================getter===============================================

    function ownerAddress() view external returns(address){
        return owner;
    }


    function getAllTokens() view external returns (ISingular[]){
        revert("discussing implement this function heavily or not");
    }

    /**
     get the number of owned tokens
     */
    function numOfTokens() view external returns (uint256){
        return ownedSingularsAmount;
    }

    /**
     get the token at a specific index.
     */
    function getTokenAt(uint256 _idx) view external returns (ISingular){
        revert("implement later");
    }



    //==============================internal==============================================

    function singularAdded(ISingular _added) internal{
        require(!hasSingular(_added));
        ownedSingulars[_added] = true;
        ownedSingularsAmount = SafeMath.add(ownedSingularsAmount,uint256(1));
    }

    function singularRemoved(ISingular _added)internal{
        require(hasSingular(_added));
        ownedSingulars[_added] = false;
        ownedSingularsAmount = SafeMath.sub(ownedSingularsAmount,uint256(1));
    }

    function hasSingular(ISingular _singular) public view returns(bool){
        return ownedSingulars[_singular];
    }
    //==============================internal==============================================


    modifier ownsSingular(ISingular _singular){
        require(hasSingular(_singular));
        _;
    }



}
