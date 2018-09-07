pragma solidity ^0.4.24;

import "./ISingularWallet.sol";

contract ISingularWalletAll is ISingularWallet {
    function name() external view returns (string);
    function symbol() external view returns (string);
    function description() external view returns (string);
}
