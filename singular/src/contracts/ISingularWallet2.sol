pragma solidity ^0.4.24;

import "./ISingular.sol";
import "./ITradable.sol";

/**
 * @title a wallet with more action functions built in.

 * @author Bing Ran<bran@udap.io>
 * @author Guxiang Tang<gtang@udap.io>
 *
 */
contract ISingularWallet2  is ISingularWallet{

    /**
    a callback to notify the the wallet that the transaction
    has been rejected. The parties may synchronize the local state to reflect the
    ownership change.
    */
    function offerRejected(
        ITradable token,    ///< the token of concern
        string note         ///< the associated note
    )
    external;

    /**
    to send a token in this wallet to a recipient. The recipient SHOULD respond by calling `ISingular::accept()` or
    `ISingular::reject()` in the same transaction.
    */
    function send(
        ISingularWallet toWallet,     ///< the recipient
        ITradable token,             ///< the token to transfer
        string _senderNote
    )
    external;

    /**
    to approve a new owner of a token and notify the recipient. The recipient SHOULD accept or reject the offer in
    a separate transaction. This is of the "offer/accept" two-step pattern.
    */
    function sendNotify(
        ISingularWallet toWallet,     ///< the recipient
        ITradable token,             ///< the token to transfer
        string _senderNote,
        uint256 _expiry
    )
    external;


    /**
    to agree an offer when offerNotify is called
    */
    function agreeTransfer(
        ITradable _token,
        string _reply
    )
    external;

    /**
    to reject an offer when offerNotify is called
    */
    function rejectTransfer(
        ITradable _token,
        string _reply
    )
    external;

}

