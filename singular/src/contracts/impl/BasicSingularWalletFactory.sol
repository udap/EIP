pragma solidity ^0.4.24;
import "./BasicSingularWallet.sol";

/**
 @title a very simple way to created new instance of BasicSingularWallet
*/
contract BasicSingularWalletFactory {
    function newWallet(string name) public returns (BasicSingularWallet) {
        return new BasicSingularWallet(name);
    }
}