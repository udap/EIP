pragma solidity ^0.4.24;

import "../ISingularWallet.sol";
import "./IERC721SingularFactory.sol";
import "./IERC721.sol";
import "./ERC721NonTradable.sol";


/**
   @title A surrogate to an ERC721 account, with additional factory method.

   @author bing ran<bran@udap.io>
 */
contract ERC721SingularFactory is IERC721SingularFactory {

    IERC721 private erc721;

    constructor(
        IERC721 _erc721
    )
    public
    {
        erc721 = _erc721;
    }

    function whatERC721()
    external
    view
    returns(
        address
    ) {
        return erc721;
    }

    function newTradable721(
        string _name,
        string _description,
        string _tokenURI,
        bytes32 _tokenURIDigest,     ///< the hash of any tokenURI content
        ISingularWallet wallet,      ///< the owner of the new singular
        uint256 tokenId                 ///< the id of the token that is ownded by the caller.
    )
    public
    returns(
        ERC721Tradable
    ) {
        require(msg.sender == erc721.ownerOf(tokenId), "the message sender is not the owner of this 721 token");
        ERC721Tradable s721 = new ERC721Tradable(
            _name,
            _description,
            _tokenURI,
            _tokenURIDigest,
            wallet,
            erc721,
            tokenId
        );
        erc721.transferFrom(msg.sender, address(s721), tokenId);
        return s721;
    }

    /**
    To create a new instance of nontradable singular from a naked 721 token id.
    Note: although being named NonTransferable, it only means this ERC721NonTradable's immediate owner cannot
    be changed. But we don't have control of immutability the owner's owner. ERC721 has no concept of transferability,
    so any token can be transferred to a non-tradable, which also means a previously aprroved operator can
     transfer the 721 token without let the ERC721NonTradable owner know.
    */
    function newNonTradable721(
        string _name,
        string _description,
        string _tokenURI,
        bytes32 _tokenURIDigest,     ///< the hash of any tokenURI content
        ISingularWallet wallet,      ///< the owner of the new singular
        uint tokenId                 ///< the id of the token that is ownded by the caller.
    )
    public
    returns(
        ERC721NonTradable
    ) {
        require(msg.sender == erc721.ownerOf(tokenId), "the message sender is not the owner of this 721 token");
        ERC721NonTradable s721 = new ERC721NonTradable(
            _name,
            _description,
            _tokenURI,
            _tokenURIDigest,
            wallet,
            erc721,
            tokenId
        );
        erc721.transferFrom(msg.sender, address(s721), tokenId);
        return s721;
    }

}
