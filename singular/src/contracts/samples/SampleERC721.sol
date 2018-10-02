pragma solidity ^0.4.24;

import "openzeppelin-solidity/contracts/token/ERC721/ERC721BasicToken.sol";


/**
 * @title ERC721BasicTokenMock
 * This mock just provides a public mint and burn functions for testing purposes
 */
contract SampleERC721 is ERC721BasicToken {
    // todo: access control
    function mint(address _to, uint256 _tokenId) public {
        super._mint(_to, _tokenId);
    }

    // access control
    function burn(uint256 _tokenId) public {
        super._burn(ownerOf(_tokenId), _tokenId);
    }
}
