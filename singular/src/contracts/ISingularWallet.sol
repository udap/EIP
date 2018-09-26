pragma solidity ^0.4.24;

import "./ISingular.sol";
import "./ITradable.sol";

/**
 * A contract that binds an address (EOA/SC) to a collection of Singular tokens. The
 * owner account may not have the ability to handle the Singular tokens directly,
 * thus they can take advantage of this contract to achieve the effect.
 *
 * All the tokens MUST have this account as the owner of them. It's up to the implementation
 * to ensure the synchronization.
 *
  XXX alternative name: ISingularOwner


 * @author Bing Ran<bran@udap.io>
 * @author Guxiang Tang<gtang@udap.io>
 *
 */
interface ISingularWallet {
    /**
    * event emitted when approve target Singular to some another wallet
    */
    event SingularReceiverApproved(
        address to,
        address singular,
        uint256 when,
        string senderNote
    );

    /**
    * event emitted when get offered Singular
    */
    event SingularOffered(
        address from,
        address singular,
        uint256 when,
        string senderNote
    );
    /**
    * event emitted when target Singular transfer succeeds;
    */
    event SingularTransferred(
        address from,
        address to,
        address singular,
        uint256 when,
        string receiverNote
    );

    /**
    * event emitted when target Singular transfer fails;
    */
    event SingularTransferFailed(
        address from,
        address to,
        address singular,
        uint256 when,
        string receiverNote
    );

    /**
     get the owner address of this account
     */
    function ownerAddress()
    external
    view
    returns (
        address           ///< the parent owner of this account
    );

    /**
     Offers a token that has been assigned to the receiver as the next owner.
     The receiver SHOULD choose to take a synchronous action by calling `accept()`
     or `reject()` in the same transaction on the token in the method body,
     or take a note and return, followed by an asynchronous call to `accept/reject
     at a later time`.
     */
    function offer(
        ITradable token,    ///< the offered token
        string note         ///< additional information
    )
    external;

    /**
    To notify this account that a token transfer offer is ready. The function should return
    without doing anything on the token. This account can accept/reject the offer in a
    separate transaction.
    */
    function offerNotify(
        ITradable token,    ///< the offered token
        string note         ///< additional information
    )
    external;

    /**
    to notify the *sender* that the intended token transfer has completed and the token has been sent
    */
    function sent(
        ITradable token,        ///< the token that has been sent
        string note             ///< additional info
    )
    external;

    /**
    to notify the recipient that the intended token transfer has completed and the token is now owned by
    the recipient.
    */
    function received(
        ITradable token,        ///< the token that has been sent
        string note             ///< additional info
    )
    external;

    //------------- interactions between containers

    /**
    to create a new ISingularWallet and move the ownership of he elements from this wallet to a new
    ISingularWallet. The new container's owner is the the owner of this container
    */
    function slice(
        ISingular[] elements    ///< the elements to move slice off from this container.
    )
    external
    returns(
        ISingularWallet         ///< the new ISingularWallet instance owning the elements
    );

    /**
    to dump all the elements of the specified wallet to this container. The incoming container's owner
    must be the same as this container.
    */
    function join(
        ISingularWallet container   ///< the source container which must be owned by the same owner as this.
    )
    external;

    //-------------- asset enumeration

    /**
     To find out if an address is an authorized to act on a specific asset. How the authorization
     list is maintained is up to implementations.

     This function is intended for the `Singular` tokens to call, in an Inversion-of-Control manner,
     for access control in case that a transaction is requested on the tokens. This account
     must agree with the tokens on the action names to maintain the authorizations.

     */
    function isActionAuthorized(
        address caller,     ///< the action invoker
        bytes32 action,      ///< the action intended
        ISingular token     ///< of target of the action
    )
    external
    view
    returns (bool);      ///< true of authorized; false otherwise

    /**
     retrieve all the Singular tokens, not in any particular order.
     */
    function getAllTokens()
    view
    external
    returns (
        ISingular[]          ///< all the tokens owned by this account
    );

    /**
     get the number of owned tokens
     */
    function numOfTokens() view external returns (uint256);

    /**
     get the token at a specific index.
     */
    function getTokenAt(
        uint256 idx          ///< the index of into the token array, must be in [0, numOfTokens())
    )
    view
    external
    returns (
        ISingular             ///< the n-th element in the token list
    );

}

