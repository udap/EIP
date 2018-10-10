pragma solidity ^0.4.24;

import "./IERC721.sol";
import "../ISingularWallet.sol";
import "./IERC721Singular.sol";
import "../impl/NonTradable.sol";


/**


@author bing ran<bran@udap.io>

*/
contract ERC721NonTradable is IERC721Singular, NonTradable {
    function contractName() external pure returns(string) {return "ERC721NonTradable";}

    IERC721 erc721;
    uint tokenNumber;

    /**
    construct a Tradable backed by an ERC721 token.
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
        require(msg.sender == address(_wallet), "ERC721NonTradable can only be initialized by the owning wallet");
        NonTradable.init(
            _name,
            _erc721.symbol(),
            _description,
            _tokenURI,
            _tokenURIDigest,
            _erc721,
            _wallet
        );
        erc721 = _erc721;
        tokenNumber = _tokenId;
    }

    function ERC721Address() external view returns (address) {
        return erc721;
    }

    function tokenID() external view returns(uint) {
        return tokenNumber;
    }

    function unbind() public {
        ISingularWallet wal = theOwner;
        require(msg.sender == address(wal) || msg.sender == wal.ownerAddress(), "msg.sender were not allowed to unbind this ERC721 singular");
        erc721.transferFrom(this, msg.sender, tokenNumber);
        emit ERC721SingularUnbound (
            erc721,
            tokenNumber,
            this
        );
        selfdestruct(msg.sender);

    }

}

