pragma solidity ^0.4.24;

import "./MintableSingularGenerator.sol";

contract LimitedSingularGenerator is MintableSingularGenerator{
    constructor(){

    }

    uint256 limit;

    function _mint(string _name, string _symbol, string _description, string _tokenURI, bytes _tokenURIDigest, address _to) public returns (uint256 singularNo, ISingular created){
        require(limit>= total);
        (singularNo, created) = super._mint(_name,  _symbol,  _description,  _tokenURI,  _tokenURIDigest,  _to);
        return;
    }

    function _burn(uint256 _singularNo) public{
        super._burn(_singularNo);
    }
}
