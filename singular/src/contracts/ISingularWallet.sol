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
 *  XXX alternative name: ISingularOwner
 *
 *
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
    to test if an address is the effective owner, directly or indirectly, of this wallet. Implementations
    can make decision by matching it with the current owner, or querying the owner recursively to determine the effective
    ownership.

    This function can be used for determining if a msg.sender is allow to call functions that require owner privilege.
    */
    function isEffectiveOwner(
        address addr
    )
    external
    view
    returns (
        bool
    );

    /**
    to get the last time when the asset portfolio was changed dur to addition or removal
    */
    function whenAssetsLastUpdated() external view returns (
        uint
    );

    /**
     To receive an incoming transfer offer. The token has been assigned to this receiver as the next owner.
     The receiver SHOULD choose to take a synchronous action by calling `accept()`
     or `reject()` in the same transaction on the token in the method body,
     or take a note and return, followed by an asynchronous call to `accept/reject
     at a later time`.
     todo: consider changing the name to `receiveOwnership`
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
     todo: consider changing the name to `receiveOwnershipNotify`
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
        ISingular token,        ///< the token that has become owned by the wallet
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

    /// to test if an ISingular is owned by this wallet.
    function owns(ISingular token) external view returns (bool);

    /**
     retrieve all the Singular tokens, not in any particular order.
     */
    function getAllTokens()
    view
    external
    returns (
        ISingular[],          ///< all the tokens owned by this account
        uint whenLastUpdated    ///< when the asset portfolio was changed
    );

    /**
     get the number of owned tokens
     */
    function numOfTokens() view external returns (
        uint256 totalNumber,    ///< the total number of tokens
        uint whenLastUpdated    ///< based on the lastly changed time
    );

    /**
     get the token at a specific index. revert if the timestamp is no the latest,
     */
    function getTokenAt(
        uint256 idx,          ///< the index of into the token array, must be in [0, numOfTokens())
        uint whenLastUpdated    ///< version number
    )
    view
    external
    returns (
        ISingular             ///< the n-th element in the token list
    );

}

