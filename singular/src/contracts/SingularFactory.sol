pragma solidity ^0.4.24;

import "./utils/Ownable.sol";
import "./Upgradeable/Mini/MiniRegistry.sol";
import "./ISingular.sol";
import "./Upgradeable/Mini/MiniProxy.sol";
import "./Singular.sol";

/**
 * 
 * 
 * 
 */
contract SingularFactory is Ownable{ // bran: why Ownable? Can we use ISingular instead?
    constructor(MiniRegistry _registry) public payable{
        miniRegistry = _registry;
    }

    MiniRegistry internal miniRegistry;

    //singular address => true if published by this factory
    mapping(address => bool) registeredSingulars;

    function setMiniRegistry (MiniRegistry _registry) public onlyOwner{
        miniRegistry = _registry;
    }

    function getMiniRegistry () public view returns(MiniRegistry){
        return miniRegistry;
    }

    //please config MiniRegistry  before you create MiniProxy
    function createSingular(
        string _name, 
        string _symbol, 
        string _description, 
        string _tokenURI, 
        bytes32 _tokenURIDigest,
        address _wallet,
        address _creator
        ) 
        onlyOwner 
        public 
        returns (ISingular)
    {
        MiniProxy newSingular = new MiniProxy(address(miniRegistry),this);
        Singular(newSingular).init(
            _name,
            _symbol,
            _description,
            _tokenURI,
            _tokenURIDigest,
            _wallet,
            _creator
        );

        registeredSingulars[address(newSingular)]= true;
        return ISingular(newSingular);
    }

    function checkSingular(ISingular _input) public view returns (bool){
        return registeredSingulars[_input];
    }
}
