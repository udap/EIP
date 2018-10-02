pragma solidity ^0.4.24;

contract MustInitialize {
    bool private _initialized;

    /// can be called once in a contract life cycle
    modifier uninitialized(){
        require(!_initialized, "cannot re-initialize the contract");
        _initialized = true;
        _;
    }

    /// to guard a function from being invoked before the contract is initialized
    modifier initialized{
        require(_initialized, "contract not initialized");
        _;
    }
}
