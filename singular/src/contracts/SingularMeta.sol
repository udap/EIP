pragma solidity ^0.4.24;

import "./ISingularMeta.sol";
import "./utils/Initialized.sol";
import "./SingularBase.sol";

contract SingularMeta is ISingularMeta, Initialized {
    /// meta
    string theName;
    string theSymbol; /// token type information
    string theDescription;
    string theTokenURI;
    bytes32 theTokenURIDigest;


    constructor(string _name, string _symbol, string _description, string _tokenURI, bytes32 _tokenURIDigest) public {
        theName = _name;
        theSymbol = _symbol;
        theDescription = _description;
        theTokenURI = _tokenURI;
        theTokenURIDigest = _tokenURIDigest;
    }

    // for compatibility with proxy, which requires initialization out of constructor
    function init (string _name, string _symbol, string _description, string _tokenURI, bytes32 _tokenURIDigest)
    unconstructed
    public
    {
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
    function tokenURIDigest() external view returns (bytes32){return theTokenURIDigest;}

    /// end of meta

}
