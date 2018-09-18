pragma solidity ^0.4.24;

import "./ISingularWallet.sol";
import "./debit/IDebit.sol";
import "./debit/ERC20Debit.sol";

/**
 * @title
 * support of ownership trading
 *
 *
 *
 * @author Bing Ran<bran@udap.io>
 */
contract ITradable is ITransferrable {
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

    SellOffer public sellOffer;
    SwapOffer public swapOffer;


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
     */
    function buy(
        ERC20Debit debitcard   ///< the money. The denomination MUST be >= offer price.
    )
    external;


    /*********************** swapping ******************/
    /**
    set up a swap arrangement
    */
    function approveSwap(
        ISingular target,
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

    function rejectSwap(
        ITradable offered
    )
    public;



}
