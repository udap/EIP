pragma solidity ^0.4.24;

import "./SingularBase.sol";
import "./ISingular.sol";
import "../node_modules/openzeppelin-solidity/contracts/math/SafeMath.sol";
import "./SingularWalletBase.sol";
import "./SingularFactory.sol";

contract MintableSingularGenerator is SingularWalletBase {
    SingularFactory internal singularFactory;
/*    constructor(SingularFactory _singularFactory) public payable{
        singularFactory = _singularFactory;
    }*/

    function init(SingularFactory _singularFactory, address _generatorOwner, address _generatorOperator) unconstructed public payable{
        SingularWalletBase.init( _generatorOwner,  _generatorOperator);
        singularFactory = _singularFactory;
    }
    string symbol;
    mapping(uint256 => ISingular) registry;
    uint256 total;

    function mint(string _name, string _symbol, string _description, string _tokenURI, bytes _tokenURIDigest, address _to) constructed onlyOwnerOrOperator public returns (uint256 singularNo, ISingular created){
        //created = new SingularImpl(_name, symbol, _description, _tokenURI,_tokenURIDigest, _to);
        singularFactory.createSingular( _name,  _symbol,  _description,  _tokenURI,  _tokenURIDigest,  _to, this);
        singularNo = total;
        registry[singularNo] = created;
        total = SafeMath.add(total,1);
        return;
    }

    function burn(uint256 _singularNo) constructed onlyOwnerOrOperator public{
        registry[_singularNo].burn("burn it");
    }
}
