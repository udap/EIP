pragma solidity ^0.4.24;

import "./ISingularWallet.sol";


/**
 * @title A concrete asset token representing a single item of things. It should
 * be used together with ISingularMeta to fully specified the information about 
 * this item.
 *
 * @author Bing Ran<bran@udap.io>
 * @author Guxiang Tang<gtang@udap.io>
 */
interface ISingular {

    /**
    to show the 'class' name of this contract.
    The concrete singulars must return the contract name as the value.
    */
    function contractName()
    external
    pure
    returns(
        string name             ///< the name of the contract class
    );

    /**
     * get the current owner. From asset point of view, an owner owns this token. 
     */
    function owner()
    external
    view
    returns (
        ISingularWallet         ///< owner is an ISingularWallet
    );

    /**
     * get the current operator who can operate on the owner's behalf. Only the owner can set/remove the
     * operator.
     */
    function operator()
    external
    view
    returns (
        address         ///< owner is an ISingularWallet
    );


    /**
    * get the creator
    */
    function creator()
    external
    view
    returns (
        address         ///< who has created this, be it a factory or ERC721 type of contracts, or a wallet.
    );

    /**
    * get the creation time
    */
    function creationTime()
    external
    view
    returns (
        uint256         ///< when this thing was created
    );

    /**

    a Singular that can be associated with an address that describes the type information.

    */
    function tokenType()
    external
    view
    returns(
        address                 ///< address that describes the type of the token.
    );

    /**
    to test if an address is the effective owner, directly or indirectly, of this ISingular. Implementations
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
}
