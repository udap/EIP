pragma solidity ^0.4.24;

import "../ISingularWallet.sol";
import './DebitBasic.sol';

library DebitFactory{
    function newDebitBasic(
        string symbol,
        address _coinType, 
        ISingularWallet wal
        ) 
        internal 
        returns 
        (
            DebitBasic
        )
    {
        DebitBasic p = new DebitBasic(symbol, _coinType, wal);
        return p;
    }

}
