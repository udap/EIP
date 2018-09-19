pragma solidity ^0.4.24;

import "./ISingularWallet.sol";
import "./ISingular.sol";
import "./SingularMeta.sol";
import "./ITransferrable.sol";

/**
 * @title Concrete asset token representing a single piece of asset, with
 * support of ownership transfers and transfer history.
 *
 * The owner of this item must be an instance of `SingularOwner`
 *
 * See the comments in the Singular interface for method documentation.
 *
 *
 * @author Bing Ran<bran@udap.io>
 *
 */
contract Transferable is SingularMeta, ITransferrable {

    ISingularWallet currentOwner; /// current owner
    ISingularWallet ownerPrevious; /// next owner choice


    address internal theCreator; /// who creates this token
    uint256 whenCreated;

    address tokenTypeAddr;

    // should use a struct like `TransferOffer`

    struct TransferOffer {
        ISingularWallet nextOwner; /// next owner choice
        uint256 validFrom;
        uint256 validTill;
        string senderNote;
    }

    TransferOffer public transferOffer;

    string receiverNote;



    constructor(
        string _name,
        string _symbol,
        string _descr,
        string _tokenURI,
        bytes32 _tokenURIHash,
        address _tokenType,
        ISingularWallet _wallet
    )
    SingularMeta(_name, _symbol, _descr, _tokenURI, _tokenURIHash)
    public
    {
        theCreator = msg.sender;
        currentOwner = _wallet;
        whenCreated = now;
        tokenTypeAddr = _tokenType;
    }

    function creator()
    external
    view
    returns (
        address         ///< the owner elected
    ) {
        return theCreator;
    }

    function tokenType()
    external
    view
    returns(
        address                 ///< address that describes the type of the token.
    ){
        return tokenTypeAddr;
    }

    function creationTime() public view returns(uint256) {
        return whenCreated;
    }

    /**
     * get the current owner as type of SingularOwner
     */
    function previousOwner() 
    external 
    view 
    returns (
        ISingularWallet
    ) {
        return ownerPrevious;
    }

    function nextOwner() 
    external
    view
    returns (
        ISingularWallet
    ){
        return transferOffer.nextOwner;
    }

    /**
     * get the current owner as type of SingularOwner
     */
    function owner() 
    external 
    view 
    returns (
        ISingularWallet
    ) {
        return currentOwner;
    }


    /**
     * There can only be one approved receiver at a given time. This receiver cannot
     * be changed before the expiry time.
     * Can only be called by the token owner (in the form of SingularOwner account or
     * the naked account address associated with the currentowner) or an approved operator.
     * Note: the approved receiver can only accept() or reject() the offer. His power is limited
     * before he becomes the owner. This is in contract to the the transferFrom() of ERC20 or
     * ERC721.
     *
     */
    function approveReceiver(
        ISingularWallet _to,
        uint256 _validFrom,
        uint256 _validTill,
        string _reason
    )
    external
    permitted(msg.sender, "approveReceiver", currentOwner)
    notInTransition
    {

        require(address(_to) != address(0), "cannot send to null address");
        require(_validTill > now && _validTill > _validFrom, "expiry must be later than now and from");

        transferOffer.validFrom = _validFrom;
        transferOffer.validTill = _validTill;
        transferOffer.senderNote = _reason;
        transferOffer.nextOwner = _to;

        emit  ReceiverApproved(
            address(currentOwner), 
            address(transferOffer.nextOwner),
            _validFrom, 
            _validTill, 
            transferOffer.senderNote);

    }

    /**
     * The approved account takes the ownership of this token. The caller must have
     * been set as the next owner of this token previously in a call by the current
     * owner to the approve() function. The expiry time must be in the future
     * as of now. This function MUST call the sent() method on the original owner.
     TODO: evaluate re-entrance attack
     */
    function acceptTransfer(
        string _reason
    )
    external
    inTransition
    permitted(msg.sender, "accept", transferOffer.nextOwner)
    {
        ownerPrevious = currentOwner;
        currentOwner = transferOffer.nextOwner; // the single most important step!!!
        reset();
        // transferHistory.push(TransferRec(ownerPrevious, owner, now, senderNote, _reason, this));
        uint256 moment = now;
        ownerPrevious.sent(this, _reason);
        currentOwner.received(this, _reason);

        emit Transferred(address(ownerPrevious), address(currentOwner), moment,
            transferOffer.senderNote, _reason);

    }

    /**
     * reject an offer. Must be called by the approved next owner(from the address
     * of the SingularOwner or SingularOwner.ownerAddress()).
     */
    function rejectTransfer(string note)
    external
    permitted(msg.sender, "reject", transferOffer.nextOwner)
    {
        receiverNote = note;
        reset();
    }

    function reset() internal {
        delete transferOffer;
    }

    /**
     * to send this token synchronously to a SingularWallet. It must call approveReceiver
     * first and invoke the "offer" function on the other SingularWallet. Setting the
     * current owner directly is not allowed.
     */
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


    /***************************** modifiers **************************/

    modifier notInTransition() {
        require(now > transferOffer.validTill, "this singular is in ownership transition");
        _;
    }

    modifier inTransition() {
        require(inTransfer(),
            "not in valid ownership transition time window.");
        _;
    }

    function inTransfer() view public returns (bool) {
        uint256 t = now;
        return t >= transferOffer.validFrom && t <= transferOffer.validTill;
    }

    modifier ownerOnly() {
        require(msg.sender == address(currentOwner), "only owner can do this action");
        _;
    }

    modifier permitted(
        address caller,
        bytes32 action,
        ISingularWallet authenticator
    ) {
        require(
            address(authenticator) == caller ||
        authenticator.isActionAuthorized(caller, action, this),
            "action not authorized");
        _;
    }
}
