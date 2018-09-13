pragma solidity ^0.4.0;

import "./ISingularWallet.sol";
import "./Upgradeable/Mini/MiniProxy.sol";
import "./ISingularWalletAll.sol";

contract SingularWalletFactory {
    constructor(MiniRegistry _registry) public payable{
        miniRegistry = _registry;
    }

    MiniRegistry internal miniRegistry;

    //singular address => true if published by this factory
    mapping(address => bool) registeredSingularWallets;

    function setMiniRegistry (MiniRegistry _registry) public onlyOwner{
        miniRegistry = _registry;
    }

    function getMiniRegistry () public view returns(MiniRegistry){
        return miniRegistry;
    }

    //please config MiniRegistry  before you create MiniProxy
    function createSingularWallet(address _walletOwner, address _walletOperator,string _theName, string _theSymbol, string _theDescription) onlyOwner public returns (ISingularWalletAll){
        MiniProxy newSingularWallet = new MiniProxy(address(miniRegistry),this);
        SingularWallet(newSingularWallet).init(_walletOwner, _walletOperator, _theName, _theSymbol);

        registeredSingularWallets[address(newSingularWallet)]= true;
        return ISingularWalletAll(newSingularWallet);
    }

    function checkSingularWallet(ISingularWallet _input) public view returns (bool){
        return registeredSingularWallets[_input];
    }
}
