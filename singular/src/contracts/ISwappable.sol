pragma solidity ^0.4.24;

import "./ISingularWallet.sol";

/**
   @title support of ownership swap between two singulars


   XXX consider the ITransferable a special case of ISwappable, with the target of address(0)

   @author Bing Ran<bran@udap.io>
 */
interface ISwappable {
    /**
     * To indicate that a party has proposed to make a swap proposal
     */
    event SwapProposed(
        ISingular from,           ///< the from party of transaction
        ISingular to,             ///< the receiver
        uint256 validFrom,
        uint256 validTill,          
        string senderNote           ///< additional note
    );

    /**
     * The swap has been successfully completed.
     */
    event SwapCompleted(
        ISingular from,           ///< the initially proposed swap item
        ISingular to,             ///< the target
        uint256 when,
        string senderNote           ///< additional note
    );


    /**
    to set up a swap arrangement. The from 
     */
    function approveSwap(
        ISingular to,             ///< the receiver
        uint256 validFrom,
        uint256 validTill,          
        string senderNote           ///< additional note
    )
    external;

    /**
     trigger the swap with the target asset. One of the two conditions must be met 
     to proceed with the swap:
     
     1. the msg.sender == item.currentOwner(), or,
     2. the item has been setup such that it intends to wap with this item(the item
     that is being invoked upon) via an owner's call on `approveSwap`
     
     */
    function acceptSwap(
        ISwappable item,        ///< the required item to complete a swap
        string note             ///< the additional information
    )
    external;

    /**
     To reject an offer. It must be called by the owner of the target item.
     */
    function rejectSwap(
        string note
    )
    external;

}
