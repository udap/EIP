pragma solidity ^0.4.0;

import "../../../node_modules/openzeppelin-solidity/contracts/ownership/Ownable.sol";
import "../../ISingularWallet.sol";
import "../../Upgradeable/Mini/MiniProxy.sol";
import "../../Upgradeable/Mini/MiniRegistry.sol";
import "../SingularWallet.sol";

// MiniProxy1,2,3,4....n  =>  MiniRegistry => WalletLogic
contract WalletFactory is Ownable{
    constructor(MiniRegistry _registry) public payable{
        miniRegistry = _registry;
    }

    MiniRegistry internal miniRegistry;

/*    //user address => seq => wallet address
    mapping(address => mapping(uint256 => address)) internal registeredWallets;*/
    //wallet address => user address
    mapping(address => address) registeredWallets;

    function setMiniRegistry (MiniRegistry _registry) public onlyOwner{
        miniRegistry = _registry;
    }

    function getMiniRegistry () public view returns(MiniRegistry){
        return miniRegistry;
    }

    //please config MiniRegistry  before you create MiniProxy
    function createWallet(address _to, address _toOperator) onlyOwner public returns (ISingularWallet){
        MiniProxy newWallet = new MiniProxy(miniRegistry,this);
        SingularWalletImpl(newWallet).init(_to, _toOperator);

        registeredWallets[address(newWallet)]= _to;
        return  ISingularWallet(newWallet);
    }

}
