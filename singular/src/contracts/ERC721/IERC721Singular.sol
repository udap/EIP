pragma solidity ^0.4.24;


/**
 * @title A simplified ERC721 for compatibility
 * @author Bing Ran<bran@udap.io>
 *
 */
interface IERC721Singular {
    function ERC721Address() external view returns (address);
    function tokenID() external view returns(uint);
}
