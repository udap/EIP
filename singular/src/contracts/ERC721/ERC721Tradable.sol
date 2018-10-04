pragma solidity ^0.4.24;

import "./IERC721.sol";
import "../ISingularWallet.sol";
import "./IERC721Singular.sol";
import "../impl/Tradable.sol";


/**


@author bing ran<bran@udap.io>

*/
contract ERC721Tradable is IERC721Singular, Tradable {
    function contractName() external pure returns(string) {return "ERC721Tradable";}

    IERC721 erc721;
    uint theTokenId;
    /**
    initializer to construct a Tradable backed by an ERC721 token.
    */
    function init(
        string _name,
        string _description,
        string _tokenURI,
        bytes32 _tokenURIDigest,
        ISingularWallet _wallet,
        IERC721 _erc721,
        uint _tokenId
    )
    public
    {
        require(msg.sender == address(_wallet), "ERC721Tradable can only be initialized by the owning wallet");
        Tradable.init(
            _name,
            _erc721.symbol(),
            _description,
            _tokenURI,
            _tokenURIDigest,
            _erc721,
            _wallet
        );
        erc721 = _erc721;
        theTokenId = _tokenId;
    }

    function ERC721Address() external view returns (address) {
        return erc721;
    }

    function tokenID() external view returns(uint) {
        return theTokenId;
    }
}

