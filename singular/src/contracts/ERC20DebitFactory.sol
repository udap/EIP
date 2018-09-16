pragma solidity ^0.4.24;

import "./ERC20Interface.sol";
import "./ISingularWallet.sol";
import './ERC20Debit.sol';

library ERC20DebitFactory{
    function newERC20Debit(
        ERC20Interface _coinType, 
        ISingularWallet wal
        ) 
        internal
        returns 
        (
            ERC20Debit
        )
    {
        ERC20Debit p = new ERC20Debit(_coinType, wal);
        return p;
    }
}
