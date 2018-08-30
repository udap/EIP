pragma solidity ^0.4.24;

import "../ISingular.sol";
import "../ISingularWallet.sol";
import "../../node_modules/openzeppelin-solidity/contracts/AddressUtils.sol";
import "../../node_modules/openzeppelin-solidity/contracts/math/SafeMath.sol";
import "../../node_modules/openzeppelin-solidity/contracts/ReentrancyGuard.sol";

contract SingularWalletImpl is ISingularWallet, ReentrancyGuard{
    constructor(address _owner) public {
        owner = _owner;
    }

    //owner could be an EOA address or SmartContract address which must implements function isAuthorized(address _person, address _singular) view external returns(bool)
    address internal owner;

    mapping(address => bool) ownedSingulars;
    uint256 ownedSingularsAmount;

    function isAuthorized(address _person) view external returns(bool){
        if(AddressUtils.isContract(owner)){
            // TODO: serialize the transferHistory
            revert("not implemented");
            //return MultisiWallet(owner).authorize(_person);
        }else{
            return owner == _person;
        }
    }


    function sent(ISingular _singular, string _receiverNote) ownsSingular(_singular) external{
        emit SingularTransferred(this,_singular.currentOwner(),_singular,now,_receiverNote);
        singularRemoved(_singular);
    }

    function received(ISingular _singular, string _receiverNote) external{
        require(_singular.currentOwner() == this);

        //must find a way to tell where it comes from :(
        emit SingularTransferred(address(0),this,_singular,now,_receiverNote);
        singularAdded(_singular);
    }

    function offerRejected(ISingular _singular, string _receiverNote) external{
        //must find a way to tell where it comes from :(
        emit SingularTransferFailed(address(0),this,_singular,now,_receiverNote);
    }

    //============================================================================

    function send(ISingularWallet _to, ISingular _singular, string _senderNote) external{
        _singular.sendTo(_to, _senderNote, true,0);
    }

    function sendNotify(ISingularWallet _to, ISingular _singular, string _senderNote, uint256 _expiry) external{
        require(_expiry > now);
        _singular.sendTo(_to, _senderNote, false, _expiry);
    }

    //manually approve a singular
    function approve(ISingularWallet _to, ISingular _singular, string _senderNote, uint256 _expiry ) external{
        require(_expiry > now);
        emit SingularReceiverApproved(_to, _singular, now,_senderNote);
        _singular.approveReceiver(_to, _expiry, _senderNote);
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

    //if you agree/refuse maliciously, you will lose your ETH and slow down the main-net if you like :)

    //just forward request to singular.accept
    function agree(ISingular _singular, string _receiverNote) external {
        _singular.accept(_receiverNote);
    }

    //just forward request to singular.reject
    function reject(ISingular _singular, string _receiverNote) external {
        _singular.reject(_receiverNote);
    }

    //============================================================================

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
    function getTokenAt(uint256 idx) view external returns (ISingular);

    //============================================================================

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
    //============================================================================


    modifier ownsSingular(ISingular _singular){
        require(hasSingular(_singular));
        _;
    }



}
