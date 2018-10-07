pragma solidity ^0.4.24;
import "../impl/Tradable.sol";
import "../ITradable.sol";

/**
 @title a very simple way to created new instance of BasicSingularWallet
*/
contract TradableFactory {
    function quick(string name, ISingularWallet wal) public returns (ITradable) {
        Tradable  instance = new Tradable();
        instance.init(
            name,
            "",
            "",
            "",
            0x0,
            address(0),
            wal
        );
        return instance;
    }
}