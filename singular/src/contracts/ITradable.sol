pragma solidity ^0.4.24;

import "./ISingularWallet.sol";
import "./ERC20/IDebit.sol";
//import "./debit/ERC20Debit.sol";


/**
 * @title Interface of tradable ISingular
 *
 * It supports:
 * 1. simple transfer
 * 2. swap between singulars
 * 3. buy and sell
 * 4. auction (todo)
 *
 * @author Bing Ran<bran@udap.io>
 */
contract ITradable is ISingular {
    struct SellOffer {
        address erc20;          ///< the currency type
        uint256 price;          ///< price
        uint256 validFrom;      ///< when an offer is valid from
        uint256 validTill;      ///< when the offer expires
        string note;             ///< additional note
    }

    // ? we might use a predicator to set the swap target, to make it compatible for sell and swap
    struct SwapOffer {
        ITradable target;          ///< what to swap
        uint256 validFrom;      ///< when an offer is valid from
        uint256 validTill;      ///< when the offer expires
        //        string note;             ///< additional note
    }

    struct TransferOffer {
        ISingularWallet nextOwner; /// next owner choice
        uint256 validFrom;
        uint256 validTill;
        string senderNote;
    }

    TransferOffer public transferOffer;
    SellOffer public sellOffer;
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
  */
    event SwapApproved(
        ITradable indexed from, ///< the item for swap
        ITradable indexed to,  ///< the desired item
        uint256 validFrom,      ///< when an offer is valid from
        uint256 validTill,      ///< when the offer expires
        string note             ///< additional note
    );

    /**
     */
    event Swapped(
        ITradable indexed from, ///< the item for swap
        ITradable indexed to,  ///< the desired item
        uint when,              ///< when this happened
        string note             ///< additional note
    );

    /**
     * When the current owner has approved someone else as the next owner, subject
     * to acceptance or rejection.
     */
    event SellOfferApproved(
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
        ITradable indexed item, ///< the item for sell
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
    to cancel the current sell offer, if any
    */
    function cancelSellOffer()
    public;

    /**
    to buy on an sell offer with some money. The caller MUST
    1. allow this item to take the ownership
    2. ensure the denomination is >= offer price.

    Note: no change is returned! For the buyer's best interest, he/she will create a debitcard with
    the exact amount before making the purchase.

    Note: the debit has to be ERC20Debit, which is not ideal. Cannot use IDebit, or will see Error: Definition of base
    has to precede definition of derived contract

     */
    function buy(
        IDebit debitcard   ///< the money. The denomination MUST be >= offer price.
    )
    external;


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

    /**
 the target calls this from within the takeSwap().
 */


    /**
    The owner of the desired item to accept the swap offer.
    Again, source code must be verified to conduct the swap, due to lots of ownerships transitions.
    */
    function acceptSwap(
        ITradable offered
    )
    public;

    function commitOwnerChange()
    public;

    function rejectSwap(
    )
    public;



}
