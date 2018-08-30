pragma solidity ^0.4.24;

/**
@author Bing Ran<bran@udap.io>
*/
interface ISingularMeta {

    function name() external view returns (string);
    function symbol() external view returns (string);
    function description() external view returns (string);
    function tokenURI() external view returns (string);
    /// end of meta
}
