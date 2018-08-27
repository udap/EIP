pragma solidity ^0.4.24;

import "./ISingular.sol";

/**
 * A contract that binds an address (EOA/SC) to a collection of Singular tokens. The
 * owner account may not have the ability to handle the Singular tokens directly,
 * thus they can take advantage of this contract to achieve the effect.
 *
 * All the tokens MUST have this account as the owner of them. It's up to the implementation
 * to ensure the synchronization.
 *
 *
 * @author Bing Ran<bran@udap.io>
 * @author Guxiang Tang<gtang@udap.io>
 *
 */

interface ISingularWallet {

  /**
   get the owner address of this account
   */
  function ownerAddress()
  view
  external
  returns(
    address           ///< the parent owner of this account
  );

  /**
   To find out if an address is an authorized to act on a specific asset. How the authorization
   list is maintained is up to implementations.

   This function is intended for the `Singular` tokens to call, for access control in case
   a transaction is requested on the tokens. This account must agree with the tokens on the action
   names to maintain the authorizations.

   */
  function isActionAuthorized(
    address caller,   ///< the action invoker
    bytes32 action,   ///< the action intended
    ISingular token    ///< of target of the action
  )
  view
  external
  returns(bool);      ///< true of authorized; false otherwise

  /**
   a callback to notify the both parties in a token transfer that the transaction
   has been complemented. The parties MUST synchronize the local state to reflect the
   ownership change.

   The function must `revert` with an error message if an exception has happened

   */
  function transferred(
    ISingular token,     ///< the token of interest
    ISingularWallet from, ///< the originating party of the transfer
    ISingularWallet to,   ///< the receiving party
    uint256 when,       ///< when this happens
    string note         ///< additional note
  )
  external;

  /**
   Offers a token that has been assigned to the receiver as the next owner.
   The receiver can choose to take a synchronous action by calling `accept()`
   or `reject()` in the same transaction on the token in the method body,
   or take a note and return, followed by an asynchronous call to `accept/reject
   at a later time`.

   The function must `revert` with an error message if an exception has happened

   */
  function offer(
    ISingular token,     ///< the offered token
    string note         ///< additional information
  )
  external;


// asset enumeration


  /**
   retrieve all the Singular tokens, not in any particular order.
   */
  function getAllTokens()
  view
  external
  returns(
    ISingular[]          ///< all the tokens owned by this account
  );

  /**
   get the number of owned tokens
   */
  function numOfTokens() view external returns(uint256);

  /**
   get the token at a specific index.
   */
  function getTokenAt(
    uint256 idx          ///< the index of into the token array, must be in [0, numOfTokens())
  )
  view
  external
  returns(
    ISingular             ///< the n-th element in the token list
  );

}

