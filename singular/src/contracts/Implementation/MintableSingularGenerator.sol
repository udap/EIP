pragma solidity ^0.4.24;

import "./Singular.sol";
import "../ISingular.sol";
import "../../node_modules/openzeppelin-solidity/contracts/math/SafeMath.sol";
import "../Implementation/SingularWallet.sol";

contract MintableSingularGenerator is SingularWalletImpl {
    constructor(){
    }

    string symbol;
    mapping(uint256 => ISingular) registry;
    uint256 total;

    function _mint(string _name, string _symbol, string _description, string _tokenURI, bytes _tokenURIDigest, address _to) public returns (uint256 singularNo, ISingular created){
        created = new SingularImpl(_name, symbol, _description, _tokenURI,_tokenURIDigest, _to);
        singularNo = total;
        registry[singularNo] = created;
        total = SafeMath.add(total,1);
        return;
    }

    function _burn(uint256 _singularNo) public{
        registry[_singularNo].burn("burn it");
    }
}
