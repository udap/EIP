pragma solidity ^0.4.24;

import "./ISingularWallet.sol";

/**
 * @title A concrete asset token representing a single item of things, with
 * support of ownership transfers and transfer history.
 *
 * The owner of this item must be a ISingularWallet.
 *
 *
 * The sendTo() method basically approves the transition first and notify the other
 * party via an ISingularWallet.offer() call, which may in turn evaluate the
 * ownership offer before accept it or reject it.
 *
 * Transfer in two steps:
 *
 * The approveReceiver method is a timelock(expiry) on the ownership. The
 * receiver can take the ownership within the valid period of time. Before the expiry
 * time, this token is locked for the receiver exclusively and cannot be locked
 * again for any other receivers. The contract serves as a
 * multi-sig wallet when used in the two-step pattern.
 *
 *
 * //TODO: evaluating naming options: IItem, etc, for clarity and easiness of reference
 * @author Bing Ran<bran@udap.io>
 * @author Guxiang Tang<gtang@udap.io>
 */
interface ISingular {
    /**
     * When the current owner has approved someone else as the next owner, subject
     * to acceptance or rejection.
     */
    event ReceiverApproved(
        address from,           ///< the from party of transaction
        address to,             ///< the receiver
        uint256 expiry,            ///< the time lock. in seconds since the epoch
        string reason           ///< additional note
    );
    /**
     * the ownership has been successfully transferred from A to B.
     */
    event Transferred(
        address from,
        address to,
        uint256 when,
        string reason,          ///< offer note
        string reply            ///< acceptance note
    );
    /**
    * the ownership transition fails from A to B
    */
    event TransferFailed(
        address from,
        address to,
        uint256 when,
        string reason,          ///< offer note
        string reply            ///< acceptance note
    );


    /**
     * get the current owner
     */
    function currentOwner()
    view
    external
    returns (
        ISingularWallet         ///< owner is an ISingularWallet
    );

    /**
     * get the current owner
     */
    function previousOwner()
    view
    external
    returns (
        ISingularWallet         ///< the onw who owns this previously
    );


    /**
     * get the approved next owner. This is just an timed offer, to be accepted 
     * or rejected by the intended receiver. 
     * 
     */
    function nextOwner()
    view
    external
    returns (
        ISingularWallet         ///< the owner elected
    );

    /**
    * get the current owner
    */
    function creator()
    view
    external
    returns (
        address         ///< who has created this
    );

    /**

    a Singular that can be associated with an address that describes the type information.

    */
    function tokenType()
    view
    external
    returns(
        address                 ///< address that describes the type of the token.
    );

    /**

      There can only be one approved receiver at a given time. This receiver cannot
      be changed before the expiry time.

      approveReceiver must check if the current message sender can is authorized by
      the token owner to invoke this function.

     */
    function approveReceiver(
        ISingularWallet to,     ///< address to be approved as the next owner
        uint256 expiry,         ///< the time lock. in seconds since the epoch
        string reason           ///< the reason for the transfer
    )
    external;

    /**
     The approved account takes the ownership of this token. The caller must have
     been set as the next owner of this token previously in a call by the current
     owner to the approve() function. The expiry time must be in the future
     This function MUST call the `ISingularWallet::transferred()` on both
     parties of the transaction for them to update the wallet.

     */
    function accept(
        string note
        ) 
        external;

    /**
     To reject an offer. It must be called by the approved next owner to reject a previous
     offer. The implementation MUST notify the token owner of the fact by calling
     ISingularWallet::offerRejected.
     */
    function reject(
        string note
        ) 
        external;

    /**
     * to send this token synchronously to a SingularWallet. It must call approveReceiver
     * first and invoke the "offer" function on the other SingularWallet. Setting the
     * current owner directly is not allowed.
     */
    function sendTo(
        ISingularWallet to,         ///< the recipient
        string note,                ///< additional information
        bool sync,                  ///< true if operate in synch mode, false otherwise.
        uint256 expiry              ///< the time lock. in seconds since the epoch, for sync mode, expiry will be 1 minute forcefully
    )
    external;

    /**
     * to send this token synchronously to a SingularWallet. It must call approveReceiver
     * first and invoke the "offer" function on the other SingularWallet. Setting the
     * current owner directly is not allowed.
     */
    function burn(
        string note                ///< note of burn
    )
    external;

}
