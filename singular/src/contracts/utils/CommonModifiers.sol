pragma solidity ^0.4.24;


/**
 * modifiers that are pure in state dependency
 */
contract CommonModifiers {


    modifier max16Bytes(string s) {
        require(bytes(s).length <= 16, "the string used more than 128 bytes");
        _;
    }

    modifier max32Bytes(string s) {
        require(bytes(s).length <= 32, "the string used more than 128 bytes");
        _;
    }

    modifier max64Bytes(string s) {
        require(bytes(s).length <= 64, "the string used more than 128 bytes");
        _;
    }

    modifier max128Bytes(string s) {
        require(bytes(s).length <= 128, "the string used more than 128 bytes");
        _;
    }

    modifier max256Bytes(string s) {
        require(bytes(s).length <= 256, "the string used more than 128 bytes");
        _;
    }

}
