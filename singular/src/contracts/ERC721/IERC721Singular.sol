pragma solidity ^0.4.24;

import "./IERC721.sol";


/**
 * @title A simplified ERC721 for compatibility
 * @author Bing Ran<bran@udap.io>
 *
 */
interface IERC721Singular {
    event ERC721SingularUnbound (
        IERC721 erc721,
        uint theTokenId,
        IERC721Singular singular
    );

    /**
    the erc 721 contract address
    */
    function ERC721Address() external view returns (
        address         ///< the erc 721 contract address
    );

    /**
    the underlying token id
    */
    function tokenID() external view returns(
        uint            ///< the underlying token id associated with ERC721
    );

    /**
    transfer the ownership to the receiver. The caller must be the the holding wallet or
    the effective owner of the wallet.
    */
    function unbind(address receiver) external;
}
