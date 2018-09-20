pragma solidity ^0.4.24;

import "../Tradable.sol";
import "./IERC721.sol";
import "../ISingularWallet.sol";
import "../AlternativeImplementation/NonTransferrableSingular.sol";
import "./IERC721Singular.sol";


/**


@author bing ran<bran@udap.io>

*/
contract ERC721NonTradable is IERC721Singular, NonTradableSingular {

    function contractName()
    external
    view
    returns(
        string              ///< the name of the contract class
    ) {
        return "ERC721NonTradable";
    }

    IERC721 erc721;
    uint tokenNumber;
    /**
    construct a Tradable backed by an ERC721 token.
    */
    constructor(
        string _name,
        string _description,
        string _tokenURI,
        bytes32 _tokenURIDigest,
        ISingularWallet _wallet,
        IERC721 _erc721,
        uint _tokenId
    )
    NonTradableSingular(
        _name,
        _erc721.symbol(),
        _description,
        _tokenURI,
        _tokenURIDigest,
        _erc721,
        _wallet
    )
    public
    {
        erc721 = _erc721;
        tokenNumber = _tokenId;
    }

    function ERC721Address() external view returns (address) {
        return erc721;
    }

    function tokenID() external view returns(uint) {
        return tokenNumber;
    }
}

