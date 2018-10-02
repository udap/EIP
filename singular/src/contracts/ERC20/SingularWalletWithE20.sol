pragma solidity ^0.4.24;

import "./IDebit.sol";
import "../ISingularWallet.sol";
import "./IERC20.sol";
import "./ERC20Debit.sol";
import "../impl/BasicSingularWallet.sol";


/**
   @title A surrogate to an ERC20 account, with additional factory method.
 */
contract SingularWalletWithE20 is BasicSingularWallet{

    constructor(
        string name
    )
    public
    BasicSingularWallet(
        name,
        "wallet",
        "wallet that can also creates erc20 debit",
        "",
        0
    ){

    }

    event DebitInitialized(IDebit indexed debit, ISingularWallet indexed wallet, uint256 value);

    /**
    to create a debit account held by the wallet with some cash in it, from the caller's holdings. The user must
    have transfer some balance to the wallet address before issuing anything.
    */
    function activateDebit(
        string name,
        ERC20Debit debit,                    ///< an uninitialized copy
        IERC20 erc20,                    ///< the erc20 that must be owned by the wallet
        uint256 denomination             ///< how much to put in the debit card
    )
    external
    ownerOnly
    {
        debit.init(name, erc20, this);
        if (denomination > 0)
            erc20.transfer(debit, denomination);
        emit DebitInitialized(debit, this, denomination);
//        return debit;
    }

}
