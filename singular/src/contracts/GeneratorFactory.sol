pragma solidity ^0.4.24;

import "./Upgradeable/Mini/MiniRegistry.sol";
import "./LimitedSingularGenerator.sol";

contract GeneratorFactory is Ownable{

    constructor(MiniRegistry _registry) public payable{
        miniRegistry = _registry;
    }

    MiniRegistry internal miniRegistry;

    //generator address => true if published by this factory
    mapping(address => bool) registeredGenerators;

    function setMiniRegistry (MiniRegistry _registry) public onlyOwner{
        miniRegistry = _registry;
    }

    function getMiniRegistry () public view returns(MiniRegistry){
        return miniRegistry;
    }

    //please config MiniRegistry  before you create MiniProxy
    function createGenerator(SingularFactory _singularFactory, address _generatorOwner, address _generatorOperator, uint256 _limit) onlyOwner public returns (LimitedSingularGenerator){
        MiniProxy newSingular = new MiniProxy(address(miniRegistry),this);
        LimitedSingularGenerator(newSingular).init(_singularFactory, _generatorOwner, _generatorOperator, _limit);

        registeredGenerators[address(newSingular)]= true;
        return LimitedSingularGenerator(newSingular);
    }

    function checkGenerator(LimitedSingularGenerator _input) public view returns (bool){
        return registeredGenerators[_input];
    }
}
