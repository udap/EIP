pragma solidity ^0.4.24;

import "./ISingularWallet.sol";

/**
 * @title Concrete asset token representing a single piece of asset, with
 * support of ownership transfers and transfer history.
 *
 * The owner of this item must be , defined in the ISingularWallet.sol.
 *
 *
 * The sendTo() method basically approves the transition first and notify the other
 * party via an ISingularWallet.offer() call, which may in turn evaluate the
 * ownership offer before accept it or reject it.
 *
 * Transfer in two steps:
 *
 * The approveReceiver method is a timelock on the ownership. The
 * receiver can take the ownership within the valid period of time. Before the expiry
 * time, this token is locked for the receiver exclusively and cannot be locked
 * again for any other receivers. The contract serves as a
 * multi-sig wallet when used in the two-step pattern.
 *
 * @author Bing Ran<bran@udap.io>
 *
 */
interface ISingular {
    /**
     * When the current owner has approved someone else as the next owner, subject
     * to acceptance or rejection.
     */
    event ReceiverApproved(address from, address to, uint expiry, string reason);
    /**
     * the ownership has been successfully transferred from A to B.
     */
    event Transferred(address from, address to, uint when, string reason);


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

    a Singular can be associated with an address that describes the type information.

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
        uint expiry,            ///< the deadline for the receiver to the take the ownership
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
    function accept() external;

    /**
     To reject an offer. Must be called by the approved next owner to reject a previous
     offer. The implementation MUST notify the token owner of the fact by calling
     ISingularWallet::offerRejected.
     */
    function reject() external;

    /**
     * to send this token synchronously to an AssetOwner. It must call approveReceiver
     * first and invoke the "offer" function on the other AssetOwner. Setting the
     * current owner directly is not allowed.
     */
    function sendTo(ISingularWallet to, string reason) external;


    /// ownership history enumeration

    /**
     * To get the number of ownership changes of this token.
     * @return the number of ownership records. The first record is the token genesis
     * record.
     */
    function numOfTransfers() view external returns (uint256);
    /**
     * To get a specific transfer record in the format defined by implementation.
     * @param index the index of the inquired record. It must in the range of
     * [0, numberOfTransfers())
     */
    function getTransferAt(uint256 index) view external returns(string);

    /**
     * get all the transfer records in a serialized form that is defined by
     * implementation.
     */
    function getTransferHistory() view external returns (string);

}
