pragma solidity ^0.4.24;

import "../node_modules/openzeppelin-solidity/contracts/ownership/Ownable.sol";
import "./Upgradeable/Mini/MiniRegistry.sol";
import "./ISingular.sol";
import "./Upgradeable/Mini/MiniProxy.sol";
import "./Singular.sol";

contract SingularFactory{
    constructor() public payable{

    }

    MiniRegistry internal miniRegistry;

    //singular address => true if published by this factory
    mapping(address => bool) registeredSingulars;
/*

    function setMiniRegistry (MiniRegistry _registry) public onlyOwner{
        miniRegistry = _registry;
    }
*/

    function getMiniRegistry () public view returns(MiniRegistry){
        return miniRegistry;
    }

    //please config MiniRegistry  before you create MiniProxy
    function createSingular(string _name, string _symbol, string _description, string _tokenURI, bytes _tokenURIDigest, address _to, address _creator) public returns (ISingular){
        MiniProxy newSingular = new MiniProxy(address(miniRegistry),this);
        Singular(newSingular).init(_name, _symbol, _description, _tokenURI, _tokenURIDigest, _to, _creator);

        registeredSingulars[address(newSingular)]= true;
        return ISingular(newSingular);
    }

    function checkSingular(ISingular _input) public view returns (bool){
        return registeredSingulars[_input];
    }
}
