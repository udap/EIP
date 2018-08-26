pragma solidity ^0.4.24;

import "./OwnerOfSingulars.sol";
import "../node_modules/openzeppelin-solidity/contracts/token/ERC721/ERC721.sol";
import "../Singular.sol";

/*
 @title A singular that wraps an ERC721 token


 Two strategies for this to interoperate with 721 contracts:

 1. The 721 token owner must be assigned to an instance of this contract. Ownership actions
 must be delegated to this, The 721 contract must NOT maintain a separate internal state for
 ownerships.

 Or:

 2. This contract is used as a wrapper to the underlying 721 contract. All function invocation
 must be forwarded to the 721. The 721 handles the canonical state changes. The owner of a 721
 token is assigned to an OwnerOfSingulars, which is maintained in the 721.

 The following implementation is specially designed for working the open-zeppelin 721 which is
 a 721 centered model as described in strategy 2.

 */
//How about set the owner of 721 to singular?
contract ERC721Singular is Singular {
    /// from ReentrancyGuard.sol by  @author Remco Bloemen <remco@2π.com>, Eenae <alexey@mixbytes.io>
    /// @dev counter to allow mutex lock with only one SSTORE operation
    uint256 private guardCounter = 1;


    //hmmmmmmmmmm....... seriously?
    /*
     * @dev Prevents a contract from calling itself, directly or indirectly.
     * If you mark a function `nonReentrant`, you should also
     * mark it `external`. Calling one `nonReentrant` function from
     * another is not supported. Instead, you can implement a
     * `private` function doing the actual work, and an `external`
     * wrapper marked as `nonReentrant`.
     */
    modifier nonReentrant() {
        guardCounter += 1;
        uint256 localCounter = guardCounter;
        _;
        require(localCounter == guardCounter);
    }

    ERC721 public contract721;
    uint256 public tokenIndex;
    uint256 public expiry;
    string public reason;
    string public nextOwner;

    struct TransferRec {
        OwnerOfSingulars from;
        OwnerOfSingulars from;
        uint256 at;
        String reason;
    }

    TransferRec[] internal transferHistory;


    constructor(ERC721 _contract, uint256 _index) public {
        contract721 = _contract;
        tokenIndex = _index;
    }
    /*
     * get the current owner as type of OwnerOfSingulars
     */
    function currentOwner() view external returns (OwnerOfSingulars) {
        return OwnerOfSingulars(contract721.ownerOf(tokenIndex));
    }

    /*
     * There can only be one approved receiver at a given time. This receiver cannot
     * be changed before the expiry time.
     * Can only be called by the token owner (in the form of OwnerOfSingulars account or
     * the naked account address associated with the currentowner) or an approved operator.
     * Note: the approved receiver can only accept() or reject() the offer. His power is limited
     * before he becomes the owner. This is in contract to the the transferFrom() of ERC20 or
     * ERC721.
     *
     * @param to address to be approved for the given token ID
     * @param expiry the dealline for the revceiver to the take the ownership
     * @param reason the reason for the transfer
     */
    function approveReceiver(OwnerOfSingulars _to, uint _expiry, bytes32 _reason) nonReentrant external {
        // XXX: expiry not supported by 721. The semantics are different.
        require(expiry == 0 || now > expiry); // not in time lock or the lock has expired

        // permission check
        address oldOwner = contract721.ownerOf(tokenIndex);

        require(oldOwner == msg.sender || OwnerOfSingulars(oldOwner).isAuthorized(msg.sender, this));

        expiry = _expiry;
        reason = _reason;
        nextOwner = _to;
        // 721 approve almost transfers ownership, which is too strong for Singular
        //contract721.approve(_to, tokenIndex);
    }

    /*
     * The approved account takes the ownership of this token. The caller must have
     * been set as the next owner of this token previously in a call by the current
     * owner to the approve() function. The expiry time must be in the future
     * as of now. This function MUST call the sent() method on the original owner.
     */
    function accept() nonReentrant external {
        require(msg.sender == nextOwner);
        // get the previous owner before it's been changed
        OwnerOfSingulars oldOwner = contract721.ownerOf(tokenIndex);

        contract721.transferFrom(this, nextOwner, tokenIndex);
        reset();

        transferHistory.push(TransferRec(oldOwner, msg.sender, now, reason));
        oldOwner.sent(this);
    }

    /*
     * reject an offer. Must be called by the approved next owner(from the address
     * of the OwnerOfSingulars or OwnerOfSingulars.ownerAddress()).
     */
    function reject() external {
        reset();
    }

    function reset() internal {
        delete expiry;
        delete reason;
    }

    /*
     * to send this token synchronously to an AssetOwner. It must call approveReceiver
     * first and invoke the "offer" function on the other AssetOwner. Setting the
     * current owner directly is not allowed.
     */
    function sendTo(OwnerOfSingulars to, bytes32 reason) nonReentrant external {
        approveReceiver(to, 1 minutes, reason);
        to.offer(this, reason);
    }


    /// ownership history enumeration

    /*
     * To get the number of ownership changes of this token.
     * @return the number of ownership records. The first record is the token genesis
     * record.
     */
    function numOfTransfers() view external returns (uint256) {
        return transferHistory.length;
    }
    /*
     * To get a specific transfer record in the format defined by implementation.
     * @param index the inde of the inquired record. It must in the range of
     * [0, numberOfTransfers())
     */
    function getTransferAt(uint256 index) view external returns(string) {
        return transferHistory[index];
    }

    /*
     * get all the transfer records in a serialized form that is defined by
     * implementation.
     */
    function getTransferHistory() view external returns (string) {
        // TODO: serialize the transferHistory
    }
}
