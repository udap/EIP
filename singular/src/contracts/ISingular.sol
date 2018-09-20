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
    to show the 'class' name of this contract. Similar idea to ERC165.
    The concrete singulars must return the contract name as the value.
    */
    function contractName()
    external
    view
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

}
