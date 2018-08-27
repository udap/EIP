pragma solidity ^0.4.24;

/**
@author Bing Ran<bran@udap.io>
*/
contract SingularMeta {
    /// meta
    string theName;
    bytes32 theSymbol; /// token type information
    string theDescription;
    string theTokenURI;
    address theCreator;

    constructor(string _name, bytes32 _symbol, string _description, string _tokenURI) public {
        theName = _name;
        theSymbol = _symbol;
        theDescription = _description;
        theTokenURI = _tokenURI;
        theCreator = msg.sender;
    }

    function name() external view returns (string) {return theName;}
    function symbol() external view returns (bytes32) {return theSymbol;}
    function description() external view returns (string){return theDescription;}
    function tokenURI() public view returns (string){return theTokenURI;}
    function creator() public view returns (address){return theCreator;}

    /// end of meta
}
