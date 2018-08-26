pragma solidity ^0.4.0;
import "./Singular.sol";

interface plural {
    function relationship(bytes32 _label, Singular _singular);
}
