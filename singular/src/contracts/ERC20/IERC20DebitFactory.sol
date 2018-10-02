pragma solidity ^0.4.24;

import './IDebit.sol';
import '../ISingularWallet.sol';
import "./IERC20.sol";

/**
@title a basic erc20 interface with newDebit() factory function.
*/
interface IERC20DebitFactory {
//    function register(
//        IERC20                      ///< register erc20 that must be owned by this factory
//    )
//    external;

    /**
    to create a debit account held by the wallet with some cash in it, from the caller's holdings
    */
    function newDebit(
        IERC20 tokenType,                   ///< the erc20 that must be owned by the wallet
        uint256 denomination             ///< how much to put in the debit card
    )
    external
    returns(
        IDebit debit
    );

    /////
    event DebitCreated(IDebit indexed debit, ISingularWallet indexed wallet, uint256 value);
}
