pragma solidity ^0.4.24;

import "./ITradable.sol";
import "./ISingularWallet.sol";
import "./utils/ReentrancyGuard.sol";
import "./utils/AddressUtils.sol";
import "./TransferHistory.sol";
import "./IBurnable.sol";

/**
*
//singular must transfer by its owner(SingularWallet) and between SingularWallets
*/
contract SingularBase is ITradable, ReentrancyGuard, TransferHistory, IBurnable {
    function contractName()
    external
    view
    returns(
        string              ///< the name of the contract class
    ) {
        return "SingularBase";
    }

    address internal prototype; // token types, a ref to type information

    ISingularWallet internal singularOwner; // the current owner
    ISingularWallet internal singularRecipient; // the owner to be offered in an ownership transition
    ISingularWallet internal singularPreviousOwner;  // the previousOwner in transition
    address internal singularCreator; // the first owner which is also the creator, unchangeable

/*    //is the operator of current singular, only is able to send singular
    address internal operator;
    */

    // ownership transition
    /// seconds since epoch time, absolutely. You can't cancel a transition/expiry and it will auto cancel
    /// when expiry < now or receiver reject/accept
    uint256 internal expiry;
    string internal transferReason;// transfer message

/*    constructor(string _name, string _symbol, string _description, string _tokenURI, bytes _tokenURIDigest, address _to) SingularMeta( _name,  _symbol,  _description,  _tokenURI, _tokenURIDigest)public
    {
        singularCreator = msg.sender;
        singularOwner = ISingularWallet(_to);
        ISingularWallet(_to).received(this,"new singular created");

        emit Transferred(address(0), _to, now, "created", "created");
    }
    */

    constructor()public payable{

    }

    function init (
        address _wallet,
        address _singularCreator
    )
    unconstructed
    public
    {
        TransferHistory.init();
        singularCreator = _singularCreator;
        singularOwner = ISingularWallet(_wallet);
        ISingularWallet(_wallet).received(this,"new singular created");
        // is msg.sender safe? what if called from another contract?
        // should use tx.origin instead?

        //recordTransfer(0x0, msg.sender, now, "created");
        emitTransferredAndAddTransferHistory(ISingularWallet(0), ISingularWallet(_wallet), now, "created", "created");

    }

    //=============================action===============================================

    /**
     * There can only be one approved address per token at a given time.
     * Can only be called by the token owner or an approved operator.
     * @param _to address to be approved for the given token ID
     * @param _expiry the dealline for the revceiver to the take the ownership with the preimage
     * @param _senderNote the reason for the transfer
     */
    function approveReceiver(
        ISingularWallet _to, 
        uint256 _validFrom,
        uint256 _expiry, 
        string _senderNote
    )
    notInTransition
    ownerOnly
    /*nonReentrant*/
    constructed
    external {
        require(_to != singularOwner);
        require(_expiry > now);
        singularRecipient = _to;
        expiry = _expiry;
        transferReason = _senderNote;

        emit ReceiverApproved(singularOwner, _to, _validFrom, _expiry, _senderNote);
    }

    function sendTo(
        ISingularWallet _to,
        string _reason
    )
    external
    {

        uint t = now;
        this.approveReceiver(_to, t, t + 1 minutes, _reason);
        _to.offer(this, _reason);

    }

    function sendToAsync(
        ISingularWallet _to,
        string _reason,
        uint256 _expiry
    )
    external
    {

        this.approveReceiver(_to, now, _expiry, _reason);
        _to.offerNotify(this, _reason);
    }


//function sendTo(
//        ISingularWallet _to,
//        string _senderNote,
//        bool _sync,
//        uint256 _expiry )
//
//        notInTransition
//        ownerOnly
//        /*nonReentrant*/
//        constructed
//        external
//    {
//        // we still use the approve/take two-step pattern
//        // which takes place in one transaction;
//        require(_to != singularOwner);
//        uint256 tempExpiry = _expiry;
//        if(_sync == true){
//            tempExpiry = now + 1 minutes;
//        }else{
//            require(tempExpiry > now);
//        }
//        singularRecipient = _to;
//        expiry = tempExpiry;
//        transferReason = _senderNote;// TODO: duplicated logic
//        emit ReceiverApproved(singularOwner, _to, now, _expiry, _senderNote);
//
//        if(_sync == true){
//            _to.offer(this, _senderNote);
//        }else{
//            _to.offerNotify(this, _senderNote);
//        }
//    }

    function burn(string _reason)
    external
    notInTransition
    ownerOnly
    nonReentrant
    constructed
    {

        singularPreviousOwner = singularOwner;
        singularOwner = ISingularWallet(0);
        emitTransferredAndAddTransferHistory(singularPreviousOwner, singularOwner, now, _reason,_reason);
        singularOwner.sent(this,"singular destructed");

        if(AddressUtils.isContract(singularCreator)){
            //see if the creator supports Generator

        }
    }
    //=============================action===============================================

    //=============================reaction===============================================
    function acceptTransfer(string _receiverNote) external inTransition nonReentrant constructed{
        require(msg.sender == address(singularRecipient), "only approver could accept or reject offer");
        singularPreviousOwner = singularOwner;
        singularOwner = ISingularWallet(msg.sender);
        delete singularRecipient;
        delete expiry;

        //properties are set before invoke callbacks
        singularPreviousOwner.sent(this,_receiverNote);
        singularOwner.received(this,_receiverNote);

        emitTransferredAndAddTransferHistory(singularPreviousOwner, singularOwner, now, transferReason,_receiverNote);
    }

    function rejectTransfer(string _receiverNote) external inTransition nonReentrant constructed{
        require(msg.sender == address(singularRecipient), "only approver could accept or reject offer");
        //Note, reset states after calling offerRejected()
        singularOwner.offerRejected(this,_receiverNote);

        emit TransferFailed(singularOwner, singularRecipient, now, transferReason,_receiverNote);

        delete singularRecipient;
        delete expiry;//time out expiry immediately so that you can transfer it fast-fail
    }
    //=============================reaction===============================================


    /**
     * To get the full token ownership history of this token
     */
/*
    function getHistory() view public returns (TransferRecord[]){
        return transferHistory;
    }*/

    modifier notInTransition() {
        require(!isInTransition());
        _;
    }

    modifier inTransition() {
        require(isInTransition());
        _;
    }

    modifier ownerOnly {
        if(AddressUtils.isContract(msg.sender)){
            require(msg.sender == address(singularOwner));
        }else{
            singularOwner.isActionAuthorized(msg.sender,bytes32(0x00),this);
        }
        _;
    }

    modifier approved() {
        require(msg.sender == address(singularRecipient));
        _;
    }


    function isInTransition() view public returns (bool) {
        return expiry >= now;
    }


    function owner() view external returns (ISingularWallet){
        return singularOwner;
    }
    
    function previousOwner() view external returns (ISingularWallet){
        return singularPreviousOwner;
    }


    function nextOwner() view external returns (ISingularWallet){
        return singularRecipient;
    }


    function creator() view external returns(address){
        return singularCreator;
    }
    
    
    function tokenType() view external returns(address){
        return prototype;
    }

    function emitTransferredAndAddTransferHistory(
        ISingularWallet _from,
        ISingularWallet _to,
        uint256 _when,
        string _senderNote,
        string _receiverNote
    )
    internal
    {
        emit Transferred(_from,_to,_when,_senderNote,_receiverNote);
        addTransferHistory(_from,_to,_when,_senderNote,_receiverNote);
    }
}
