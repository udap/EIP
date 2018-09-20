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
        return debit;
    }

    /************** functions of erc20 that are not used for this surrogate ********/
    // function allowance(address tokenOwner, address spender) public constant returns (uint remaining);
    // function approve(address spender, uint tokens) public returns (bool success);
    // function transferFrom(address from, address to, uint256 tokens) public returns (bool success);


    //    event Transfer(address indexed from, address indexed to, uint256 value);
}
