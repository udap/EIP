pragma solidity ^0.4.0;

import "../ISingularMeta.sol";

contract SingularMeta is ISingularMeta {
    /// meta
    string theName;
    string theSymbol; /// token type information
    string theDescription;
    string theTokenURI;
    address theCreator;


    constructor(string _name, string _symbol, string _description, string _tokenURI) public {
        theName = _name;
        theSymbol = _symbol;
        theDescription = _description;
        theTokenURI = _tokenURI;
        theCreator = msg.sender;
    }

    function name() external view returns (string) {return theName;}
    function symbol() external view returns (string) {return theSymbol;}
    function description() external view returns (string){return theDescription;}
    function tokenURI() public view returns (string){return theTokenURI;}
    function creator() public view returns (address){return theCreator;}

    /// end of meta

}