pragma solidity ^0.4.24;

import "./ISingular.sol";
import "./ISingularWallet.sol";


/**
 * @title support of ownership transfers
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
 * @author Bing Ran<bran@udap.io>
 * @author Guxiang Tang<gtang@udap.io>
 */
contract ITransferable is ISingular {

    /**
     * When the current owner has approved someone else as the next owner, subject
     * to acceptance or rejection.
     */
    event ReceiverApproved(
        address indexed from,           ///< the from party of transaction
        address indexed to,             ///< the receiver
        uint256 validFrom,      ///< when an offer is valid from
        uint256 validTill,      ///< when the offer expires
        string senderNote       ///< additional note
    );

    /**
     * the ownership has been successfully transferred from A to B.
     */
    event Transferred(
        address indexed from,
        address indexed to,
        uint256 when,
        string senderNote,          ///< offer note
        string receiverNote            ///< acceptance note
    );
    /**
    * the ownership transition fails from A to B
    */
    event TransferFailed(
        address indexed from,
        address indexed to,
        uint256 when,
        string senderNote,          ///< offer note
        string receiverNote            ///< acceptance note
    );


    /**

      There can only be one approved receiver at a given time. This receiver cannot
      be changed before the expiry time.

      approveReceiver must check if the current message sender can is authorized by
      the token owner to invoke this function.

     */
    function approveReceiver(
        ISingularWallet to,     ///< address to be approved as the next owner
        uint256 validFrom,         ///< the time lock start. in seconds since the epoch
        uint256 validTill,         ///< the time lock end. in seconds since the epoch
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
    function acceptTransfer(
        string note
        ) 
        external;

    /**
     To reject an offer. It must be called by the approved next owner to reject a previous
     offer. The implementation MUST notify the token owner of the fact by calling
     ISingularWallet::offerRejected.
     */
    function rejectTransfer(
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
        string note                 ///< additional information
    )
    external;

    /**
    to make an transfer approval and notify the other party
    */
    function sendToAsync(
        ISingularWallet to,
        string note,
        uint256 expiry
    )
    external;
}
