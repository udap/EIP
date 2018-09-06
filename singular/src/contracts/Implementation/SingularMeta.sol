pragma solidity ^0.4.24;

import "../ISingularMeta.sol";

contract SingularMeta is ISingularMeta {
    /// meta
    string theName;
    string theSymbol; /// token type information
    string theDescription;
    string theTokenURI;
    bytes theTokenURIDigest;


    /*constructor(string _name, string _symbol, string _description, string _tokenURI, bytes _tokenURIDigest) public {
        theName = _name;
        theSymbol = _symbol;
        theDescription = _description;
        theTokenURI = _tokenURI;
        theTokenURIDigest = _tokenURIDigest;
    }*/

    constructor () public{

    }

    function init (string _name, string _symbol, string _description, string _tokenURI, bytes _tokenURIDigest) public {
        theName = _name;
        theSymbol = _symbol;
        theDescription = _description;
        theTokenURI = _tokenURI;
        theTokenURIDigest = _tokenURIDigest;
    }

    function name() external view returns (string) {return theName;}
    function symbol() external view returns (string) {return theSymbol;}
    function description() external view returns (string){return theDescription;}
    function tokenURI() external view returns (string){return theTokenURI;}
    function tokenURIDigest() external view returns (bytes){return theTokenURIDigest;}

    /// end of meta

}
