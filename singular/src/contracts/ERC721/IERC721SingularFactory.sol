pragma solidity ^0.4.24;

import "../ISingularWallet.sol";
import "./ERC721Tradable.sol";
import "./ERC721NonTradable.sol";

/**
@title a basic erc20 interface with newDebit() factory funciton.
*/
contract IERC721SingularFactory {

    function whatERC721() external view returns (address );

    /**
    to create a debit account held by the wallet with some cash in it, from the caller's holdings
    */
    function newTradable721(
        ISingularWallet wallet,      ///< the owner of the new singular
        uint tokenId,               ///< the id of the token that is ownded by the caller.
        bytes32 _tokenURIDigest     ///< the hash of any tokenURI content
    )
    public
    returns(
        ERC721Tradable item
    );

    /**
    to create a debit account held by the wallet with some cash in it, from the caller's holdings
    */
    function newNonTradable721(
        ISingularWallet wallet,      ///< the owner of the new singular
        uint tokenId,               ///< the id of the token that is ownded by the caller.
        bytes32 _tokenURIDigest     ///< the hash of any tokenURI content
    )
    public
    returns(
        ERC721NonTradable item
    );

}
