pragma solidity ^0.4.24;

import "./ISingular.sol";
import "./ISingularWallet.sol";
import "./utils/AddressUtils.sol";
import "./utils/SafeMath.sol";
import "./utils/ReentrancyGuard.sol";
import "./utils/Initialized.sol";
import "./ITradable.sol";
import "./IBurnable.sol";

/*

*/

contract SingularWalletBase is ISingularWallet, ReentrancyGuard, Initialized{

    //owner could be an EOA address or SmartContract address which must implements
    // function isAuthorized(address _person, address _singular) view external returns(bool)
    address internal walletOwner;
    address internal walletOperator;

    mapping(address => bool) ownedSingulars;
    uint256 ownedSingularsAmount;


/*
    constructor(address _walletOwner, address _walletOperator) public {
    }
*/

    function init (address _walletOwner, address _walletOperator) unconstructed() public {
        walletOwner = _walletOwner;
        walletOperator = _walletOperator;
    }

    function isActionAuthorized(address _caller, bytes32 _action,ISingular singular) view external returns(bool){
        if(AddressUtils.isContract(_caller)){
            // TODO: serialize the transferHistory
            revert("not implemented");
            //return MultisiWallet(owner).authorize(_person);
        }else{
            return (walletOwner == _caller || walletOperator == _caller);
        }
    }

    //=============================callback===============================================
    function sent(ITradable _singular, string _receiverNote)
    ownsSingular(_singular)
    constructed
    external{
        require( _singular.previousOwner() == this);
        emit SingularTransferred(_singular.previousOwner(),_singular.owner(),_singular,now,_receiverNote);
        singularRemoved(_singular);
    }

    function received(ITradable _singular, string _receiverNote) constructed external{
        require(_singular.owner() == this);
        emit SingularTransferred(_singular.previousOwner(),this,_singular,now,_receiverNote);
        singularAdded(_singular);
    }

    // called when get an offer
    function offer(ITradable _singular, string _senderNote) constructed external{
        require(_singular.nextOwner() == this);
        emit SingularOffered(_singular.owner(),_singular, now, _senderNote);
        //customized later
        /*
        string _receiverNote;
        _singular.accept(_receiverNote);
        _singular.reject(_receiverNote);
        */
        _singular.acceptTransfer("accept unconditionally by sendTo(sync)");

    }

    function offerNotify(ITradable _singular, string _senderNote) constructed external{
        require(_singular.nextOwner() == this);
        emit SingularOffered(_singular.owner(),_singular, now, _senderNote);
    }

    function offerRejected(ITradable _singular, string _receiverNote) constructed external{
        require( _singular.owner() == this);
        emit SingularTransferFailed(_singular.owner(),_singular.nextOwner(),_singular,now,_receiverNote);
    }
    //=============================callback===============================================


    //=============================action===============================================

    function send(ISingularWallet _to, ITradable _singular, string _senderNote)
    onlyOwnerOrOperator
    ownsSingular(_singular)
    constructed
    external{
        //send contains approve logic so here emit an approve event
        emit SingularReceiverApproved(_to, _singular, now,_senderNote);
        _singular.sendTo(_to, _senderNote);
    }

    function sendNotify(ISingularWallet _to, ITradable _singular, string _senderNote, uint256 _expiry)
    onlyOwnerOrOperator
    ownsSingular(_singular)
    constructed
    external{
        //send contains approve logic so here emit an approve event
        require(_expiry > now);
        _singular.sendToAsync(_to, _senderNote, _expiry);
    }

    //manually approve a singular
    function approve(
        ISingularWallet _to,
        ITradable _singular,
        string _senderNote,
//        uint256 _validSince,
        uint256 _validTill
    )
    onlyOwnerOrOperator
    ownsSingular(_singular)
    constructed
    external{
        require(_validTill > now /*&& _validSince < _validTill*/);
        emit SingularReceiverApproved(_to, _singular, now,_senderNote);
        _singular.approveReceiver(_to, 1, _validTill, _senderNote);
    }

    function burn(IBurnable _singular, string _burnMsg)
    onlyOwnerOrOperator
    constructed
    external{
        _singular.burn(_burnMsg);
    }
    //=============================action===============================================

    //=============================reaction===============================================
    //if you agree/refuse maliciously, you will lose your ETH if you like :)

    //just forward request to singular.accept
    function agree(ITradable _singular, string _receiverNote)
    onlyOwnerOrOperator
    constructed
    external {
        _singular.acceptTransfer(_receiverNote);
    }

    //just forward request to singular.reject
    function reject(ITradable _singular, string _receiverNote)
    onlyOwnerOrOperator
    constructed
    external {
        _singular.rejectTransfer(_receiverNote);
    }
    //=============================reaction===============================================


    //=============================getter===============================================

    function ownerAddress() view external returns(address){
        return walletOwner;
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

    function singularRemoved(ISingular _added) internal{
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

    modifier onlyOwnerOrOperator(){
        require(msg.sender == walletOwner || msg.sender == walletOperator);
        _;
    }



}
