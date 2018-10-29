pragma solidity ^0.4.24;

import "./ISingularWallet.sol";
import "./ERC20/IDebit.sol";
import "./ISingular.sol";


/**
 *
 * @title Interface of tradable ISingular
 *
 * It supports:
 * 1. simple transfer
 * 2. swap between singulars
 * 3. buy and sell
 * 4. auction (todo)
 *
 *  XXX: Ideally it should inherit from ISingular, but doing so causes remix IDE compiler to complain
 * about order of definitions. As a compromise, I added the toISingular() to bridge the two interfaces.
 *
 * @author Bing Ran<bran@udap.io>
 */
contract ITradable /*is ISingular*/ {
    struct SaleOffer {
        ISingularWallet owner;  ///< who owns the item
        address erc20;          ///< the currency type
        uint256 price;          ///< price
        uint256 validFrom;      ///< when an offer is valid from
        uint256 validTill;      ///< when the offer expires
//        string note;             ///< additional note
    }

    // ? we might use a predicator to set the swap target, to make it compatible for sell and swap
    struct SwapOffer {
        ISingularWallet who;    ///< who makes the offer
        ITradable target;       ///< what to swap for
        uint256 validFrom;      ///< when an offer is valid from
        uint256 validTill;      ///< when the offer expires
//        string note;            ///< additional note
//        ISwapExecutor executor; ///< who to execute the swapping
    }

    struct TransferOffer {
        ISingularWallet nextOwner; /// next owner choice
        uint256 validFrom;
        uint256 validTill;
        string senderNote;
    }

    TransferOffer public transferOffer;
    SaleOffer public saleOffer;
    SwapOffer public swapOffer;

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
     * to indicate that a ownership transfer has been rejected
     */
    event TransferRejected(
        ITradable indexed from, ///< the item for swap
        ISingularWallet who,     ///< whoever has rejected the offer
        uint when,              ///< when this happened
        string note             ///< additional note
    );

    /**
     * When the current owner has approved someone else as the next owner, subject
     * to acceptance or rejection.
     */
    event SaleOfferApproved(
        ITradable indexed item, ///< the item for sell
        address indexed erc20,  ///< the currency type
        uint256 price,          ///< price
        uint256 validFrom,      ///< when an offer is valid from
        uint256 validTill,      ///< when the offer expires
        string note             ///< additional note
    );

    /**
     * the ownership has been successfully transferred from A to B.
     */
    event Sold(
        ITradable indexed item, ///< the item for sale
        ISingularWallet indexed seller, ///< seller
        ISingularWallet indexed buyer,  ///< buyer
        address erc20,  ///< the currency type
        uint256 price,          ///< price
        uint256 when,           ///< when the tx completes
        string note             ///< additional note
    );


    function previousOwner()
    external
    view
    returns (
        ISingularWallet
    );

    function nextOwner()
    external
    view
    returns (
        ISingularWallet
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

    /**
    * for operator to set the new owner
    */
    function swapInOwner(
        ISingularWallet newOwner,
        string note
    )
    external;

    /**
    */
    event SwapApproved(
        ITradable indexed from, ///< the item for swap
        ITradable indexed to,  ///< the desired item
        uint256 validFrom,      ///< when an offer is valid from
        uint256 validTill,      ///< when the offer expires
        string note             ///< additional note
    );

    /**
 * to indicate that a swap arrangement has been rejected by the target
 */
    event SwapRejected(
        ITradable indexed from, ///< the item for swap
        ITradable indexed to,  ///< the desired item
        uint when,              ///< when this happened
        string note             ///< additional note
    );


//    function matchSaleOfferNow(IDebit debit) external view returns(bool);

    /**
        offer to sell this item for

     */
    function sellFor(
        address erc20,          ///< the currency type
        uint256 price,          ///< price
        uint256 validFrom,      ///< when an offer is valid from
        uint256 validTill,      ///< when the offer expires
        string note             ///< additional note
    )
    external;

    /**
    to cancel the current sale offer, if any
    */
    function cancelSaleOffer() public;


    /*********************** swapping ******************/
    /**
    set up a swap arrangement
    */
    function approveSwap(
        ITradable target,
        uint validFrom,
        uint validTill,
        string note
    )
    public;

    function rejectSwap(
        string note
    ) public;

    /**
     * to cancel all pending trading offers.
     */
    function reset() public;

    /// adapter method to aviod inheritance
    /// to make it ITradable compatible
    function toISingular() public view returns(
        ISingular
    );

//    function owner() external view returns (ISingularWallet);

}
