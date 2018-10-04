pragma solidity ^0.4.24;
import "./BasicSingularWallet.sol";

contract BasicWalletFactory {
    function newInstance(string name) public returns (BasicSingularWallet) {
        return new BasicSingularWallet(name);
    }
}