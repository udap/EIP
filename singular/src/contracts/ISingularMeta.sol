pragma solidity ^0.4.24;

/**
*   This contract is a supplemental part of Singular.
*   ISingularMeta provides meta info for Singular
*
* @author Lycrus Hamster<bran@udap.io>
* @author Guxiang Tang<gtang@udap.io>
*/
interface ISingularMeta {

    function name() external view returns (string);
    function symbol() external view returns (string);
    function description() external view returns (string);
    function tokenURI() external view returns (string);
    function tokenURIDigest() external view returns (bytes);
    /// end of meta
}
