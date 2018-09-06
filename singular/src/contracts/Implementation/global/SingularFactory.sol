pragma solidity ^0.4.0;

import "../../../node_modules/openzeppelin-solidity/contracts/ownership/Ownable.sol";
import "../../Upgradeable/Mini/MiniRegistry.sol";
import "../../ISingular.sol";
import "../../Upgradeable/Mini/MiniProxy.sol";

contract SingularFactory is Ownable{
    constructor(address _registry) public payable{
        miniRegistry = _registry;
    }

    address internal miniRegistry;

    //singular address => true if published by this factory
    mapping(address => bool) registeredSingulars;

    function setMiniRegistry (address _registry) public onlyOwner{
        miniRegistry = _registry;
    }

    function getMiniRegistry () public returns(MiniRegistry){
        return miniRegistry;
    }

    //please config MiniRegistry  before you create MiniProxy
    function createSingular(address _to) public returns (ISingular){
        MiniProxy newSingular = new MiniProxy(miniRegistry);
        ISingular(newSingular).init(_to);

        registeredWallets[address(newSingular)]= _to;
    }

    function checkSingular(ISingular _input) public view returns (bool){
        return registeredSingulars[_input];
    }
}
