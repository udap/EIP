pragma solidity ^0.4.24;

import "./ITradable.sol";


/**

@title the middleman in trades

@author bing ran<bran@udap.io>

*/
contract ITradeExecutor {

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
     * to indicate that a swap arrangement has completed
     */
    event Swapped(
        ITradable indexed from, ///< the item for swap
        ITradable indexed to,  ///< the desired item
        uint when,              ///< when this happened
        string note             ///< additional note
    );

    /**
    owner facing API to swap the owners of the two items.

    The two parties must have been set up to swap to each other. The executor
    must be set up as the executor by both parties.
    */
    function swap(
        ITradable a,
        ITradable b
    )
    external;

    /**
    Debit owner calls this function to make a purchase.

    Note: no change is returned if the denomination is > price.
    The debit card MUST been set up with a proper SwapOffer with the denomination and swap target
     */
    function buy(
        ITradable self,     ///<
        IDebit debitCard   ///< the money
    )
    external;

}