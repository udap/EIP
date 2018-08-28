pragma solidity ^0.4.24;

import "./ISingularWallet.sol";
import "./SingularMeta.sol";
import "./TransferHistory.sol";
import "./ISingular.sol";
import "./Commenting.sol";

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
contract Singular is ISingular, SingularMeta, TransferHistory, Commenting {

    ISingularWallet public owner; /// current owner

    ISingularWallet public nextOwner; /// current owner

    address public creator; /// who creates this token

    uint256 expiry;
    string reason;



    constructor(string _name, bytes32 _symbol, string _descr, string _tokenURI)
    SingularMeta(_name, _symbol, _descr, _tokenURI)
    public
    {
        creator = msg.sender;
    }


    /**
     * get the current owner as type of SingularOwner
     */
    function currentOwner() view external returns (ISingularWallet) {
        return owner;
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
        uint256 _expiry, 
        string _reason
        ) 
        external
        NotInTransition 
    {
        
        require(address(_to) != address(0) && owner.isActionAuthorized(msg.sender, this.approveReceiver.selector, this));
        expiry = _expiry;
        reason = _reason;
        nextOwner = _to;
        emit  ReceiverApproved(address(owner), address(nextOwner), expiry, reason);
    }

    /**
     * The approved account takes the ownership of this token. The caller must have
     * been set as the next owner of this token previously in a call by the current
     * owner to the approve() function. The expiry time must be in the future
     * as of now. This function MUST call the sent() method on the original owner.
     TODO: evaluate re-entrance attack
     */
    function accept(string _reason) external InTransition {
        // for unknown reason `this.accept.selector` caused compiling error
        // let's calculate the selector
        bytes4 sel = bytes4(keccak256("accept(string)"));
        require(
            address(nextOwner) != address(0) && 
            // nextOwner.isActionAuthorized(msg.sender, this.accept.selector, this)
            nextOwner.isActionAuthorized(msg.sender, sel, this)
        );
        ISingularWallet oldOwner = owner;
        owner = nextOwner; // the single most important step
        reset();
        transferHistory.push(TransferRec(oldOwner, owner, now, _reason));
        uint256 moment = now;
        oldOwner.transferred(this, oldOwner, owner, moment, _reason);
        owner.transferred(this, oldOwner, owner, moment, _reason);
        emit Transferred(address(oldOwner), address(owner), now, _reason);

    }

    /**
     * reject an offer. Must be called by the approved next owner(from the address
     * of the SingularOwner or SingularOwner.ownerAddress()).
     */
    function reject() external {
        address sender = msg.sender;
        require(
            sender == address(nextOwner) ||
            nextOwner.isActionAuthorized(sender, this.reject.selector, this)
            );
        reset();
    }

    function reset() internal {
        delete expiry;
        delete reason;
        delete nextOwner;
    }

    /**
     * to send this token synchronously to an AssetOwner. It must call approveReceiver
     * first and invoke the "offer" function on the other AssetOwner. Setting the
     * current owner directly is not allowed.
     */
    function sendTo(
        ISingularWallet _to, 
        string _reason
        ) 
        external 
        {
        this.approveReceiver(_to, now + 60, _reason);
        _to.offer(this, _reason);
        }
    


    /// implement Commenting interface
    function makeComment(string _comment) public {
        require(owner.isActionAuthorized(msg.sender, this.makeComment.selector, this));
        addComment(msg.sender, now, _comment); 
    }


    modifier NotInTransition() {
        require(now > expiry);
        _;
    }

    modifier InTransition() {
        require(now <= expiry);
        _;
    }

}
