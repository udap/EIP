pragma solidity ^0.4.24;

import "./IDebit.sol";
import "../ISingularWallet.sol";
import "./IERC20.sol";
import "./ERC20DebitFactory.sol";
import "./ERC20Debit.sol";


/**
   @title A surrogate to an ERC20 account, with additional factory method.
 */
contract ERC20DebitFactory is IERC20DebitFactory {

    IERC20 private erc20;

    constructor(
        IERC20 _erc20
    )
    public
    {
        erc20 = _erc20;
    }

    function whatERC721()
    external
    view
    returns(
        IERC20
    ) {
        return erc20;
    }

    /**
    to create a debit account held by the wallet with some cash in it, from the caller's holdings
    */
    function newDebit(
        ISingularWallet wallet,      ///< the owner of the new debit
        uint256 denomination             ///< how much to put in the debit card
    )
    public
    returns(
        IDebit
    )
    {
        IDebit debit = new ERC20Debit(this, wallet);
        if (denomination > 0)
            erc20.transfer(debit, denomination);
        emit DebitCreated(debit, wallet, denomination);
        return debit;
    }

}
