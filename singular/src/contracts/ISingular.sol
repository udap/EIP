pragma solidity ^0.4.24;

import "./ISingularWallet.sol";

/**
 * @title A concrete asset token representing a single item of things. It should 
 * be used together with ISingularMeta to fully specified the information about 
 * this item.
 *
 *
 * //TODO: evaluating naming options: IItem, etc, for clarity and easiness of reference
 * @author Bing Ran<bran@udap.io>
 * @author Guxiang Tang<gtang@udap.io>
 */
interface ISingular {

    /**
     * get the current owner. From asset point of view, an owner owns this token. 
     */
    function owner()
    view
    external
    returns (
        ISingularWallet         ///< owner is an ISingularWallet
    );


    /**
    * get the creator
    */
    function creator()
    view
    external
    returns (
        address         ///< who has created this, be it a factory or ERC721 type of contracts, or a wallet.
    );

    /**
    * get the creation time
    */
    function creationTime()
    view
    external
    returns (
        uint256         ///< when this thing was created
    );


    /**

    a Singular that can be associated with an address that describes the type information.

    */
    function tokenType()
    view
    external
    returns(
        address                 ///< address that describes the type of the token.
    );

}
