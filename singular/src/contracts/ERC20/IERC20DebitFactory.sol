pragma solidity ^0.4.24;

import './IDebit.sol';
import '../ISingularWallet.sol';
import "./IERC20.sol";

/**
@title a basic erc20 interface with newDebit() factory funciton.
*/
contract IERC20DebitFactory {

    function whatERC20()
    external
    view
    returns(
        IERC20
    );

    /**
    to create a debit account held by the wallet with some cash in it, from the caller's holdings
    */
    function newDebit(
        ISingularWallet wallet,      ///< the owner of the new debit
        uint256 denomination             ///< how much to put in the debit card
    )
    public
    returns(
        IDebit debit
    );

    /////
    event DebitCreated(IDebit indexed debit, ISingularWallet indexed wallet, uint256 value);
}
