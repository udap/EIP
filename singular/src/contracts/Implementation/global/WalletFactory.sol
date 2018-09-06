pragma solidity ^0.4.0;

import "../../../node_modules/openzeppelin-solidity/contracts/ownership/Ownable.sol";
import "../../ISingularWallet.sol";
import "../../Upgradeable/Mini/MiniProxy.sol";
import "../../Upgradeable/Mini/MiniRegistry.sol";

// MiniProxy1,2,3,4....n  =>  MiniRegistry => WalletLogic
contract WalletFactory is Ownable{
    constructor(address _registry) public payable{
        miniRegistry = _registry;
    }

    address internal miniRegistry;

/*    //user address => seq => wallet address
    mapping(address => mapping(uint256 => address)) internal registeredWallets;*/
    //wallet address => user address
    mapping(address => address) registeredWallets;

    function setMiniRegistry (address _registry) public onlyOwner{
        miniRegistry = _registry;
    }

    function getMiniRegistry () public returns(MiniRegistry){
        return miniRegistry;
    }

    //please config MiniRegistry  before you create MiniProxy
    function createWallet(address _to) public returns (ISingularWallet){
        MiniProxy newWallet = new MiniProxy(miniRegistry);
        ISingularWallet(newWallet).init(_to);

        registeredWallets[address(newWallet)]= _to;
    }

}
