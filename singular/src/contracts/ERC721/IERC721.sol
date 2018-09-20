pragma solidity ^0.4.24;


/**
 * @title A simplified ERC721 for compatibility
 * @author Bing Ran<bran@udap.io>
 *
 */
interface IERC721 {
    function balanceOf(address _owner) external view returns (uint256 _balance);
    function exists(uint256 _tokenId) external view returns (bool _exists);
    function ownerOf(uint256 _tokenId) external view returns (address _owner);
    function approve(address _to, uint256 _tokenId) external;
    function getApproved(uint256 _tokenId) external view returns (address _operator);

    function setApprovalForAll(address _operator, bool _approved) external;
    function isApprovedForAll(address _owner, address _operator) external view returns (bool);

    function transferFrom(address _from, address _to, uint256 _tokenId) external;
//    function safeTransferFrom(address _from, address _to, uint256 _tokenId)external;

    function name() external view returns (string _name);
    function symbol() external view returns (string _symbol);
    function tokenURI(uint256 _tokenId) external view returns (string);

//    function safeTransferFrom(
//        address _from,
//        address _to,
//        uint256 _tokenId,
//        bytes _data
//    )
//    external;
}
