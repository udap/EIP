pragma solidity ^0.4.24;

import "./ISingularWallet.sol";
import "./ISingular.sol";
import "./ITransferrable.sol";
import "./SingularMeta.sol";
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
contract SimpleSingular is ISingular, SingularMeta, ITransferrable {

    ISingularWallet currentOwner; /// current owner
    ISingularWallet nextOwner; /// next owner choice
    ISingularWallet ownerPrevious; /// next owner choice


    address internal theCreator; /// who creates this token
    address tokenTypeAddr;
    
    uint256 whenCreated;
    uint256 validFrom;
    uint256 validTill;
    string senderNote;
    string receiverNote;



    constructor(
        string _name, 
        string _symbol, 
        string _descr, 
        string _tokenURI, 
        bytes32 _tokenURIHash,
        address _tokenType,
        ISingularWallet _wallet)
    SingularMeta(_name, _symbol, _descr, _tokenURI, _tokenURIHash)
    public
    {
        theCreator = msg.sender;
        currentOwner = _wallet;
        whenCreated = now;
        tokenTypeAddr = _tokenType;
    }

    function creator()
    view
    external
    returns (
        address         ///< the owner elected
    ) {
        return theCreator;
    }
    
    function tokenType()
    view
    external
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
    function previousOwner() view external returns (ISingularWallet) {
        return ownerPrevious;
    }

    /**
     * get the current owner as type of SingularOwner
     */
    function owner() view external returns (ISingularWallet) {
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
        permitted(msg.sender, "approveReceiver")
        notInTransition 
    {
        
        require(address(_to) != address(0), "cannot send to null address");
        validFrom = _validFrom;
        validTill = _validTill;
        senderNote = _reason;
        nextOwner = _to;
        emit  ReceiverApproved(address(currentOwner), address(nextOwner), _validFrom, _validTill, senderNote);
    }

    /**
     * The approved account takes the ownership of this token. The caller must have
     * been set as the next owner of this token previously in a call by the current
     * owner to the approve() function. The expiry time must be in the future
     * as of now. This function MUST call the sent() method on the original owner.
     TODO: evaluate re-entrance attack
     */
    function accept(string _reason) 
    external 
    inTransition 
    permitted(msg.sender, "accept")
    {
        ownerPrevious = currentOwner;
        currentOwner = nextOwner; // the single most important step!!!
        reset();
        // transferHistory.push(TransferRec(ownerPrevious, owner, now, senderNote, _reason, this));
        uint256 moment = now;
        ownerPrevious.sent(this, _reason);
        currentOwner.received(this, _reason);
        emit Transferred(address(ownerPrevious), address(currentOwner), moment, senderNote, _reason);

    }

    /**
     * reject an offer. Must be called by the approved next owner(from the address
     * of the SingularOwner or SingularOwner.ownerAddress()).
     */
    function reject(string note) 
    external 
    permitted(msg.sender, "reject")
    {
        receiverNote = note;
        reset();
    }

    function reset() internal {
        delete validFrom;
        delete validTill;
        delete senderNote;
        delete nextOwner;
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
            this.approveReceiver(_to, t, t + 60 seconds, _reason);
            _to.offer(this, _reason);
        }
    

    modifier notInTransition() {
        require(now > validTill, "this singular is in ownership transition");
        _;
    }

    modifier inTransition() {
        uint256 t = now;
        require(t >= validFrom && t <= validTill, 
        "not in valid ownership transition time window.");
        _;
    }

   modifier ownerOnly() {
        require(msg.sender == address(currentOwner), "only owner can do this action");
        _;
    }

    modifier permitted(address caller, bytes32 action) {
        require(currentOwner.isActionAuthorized(caller, action, this), 
        "action not authorized");
        _;
    }
}
