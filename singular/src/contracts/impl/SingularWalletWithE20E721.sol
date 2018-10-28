pragma solidity ^0.4.24;

import "./BasicSingularWallet.sol";
import "../ISingularWallet.sol";
import "../ERC20/IDebit.sol";
import "../ERC20/IERC20.sol";
import "../ERC20/ERC20Debit.sol";
import "../ERC721/ERC721Tradable.sol";
import "../ERC721/ERC721NonTradable.sol";


/**
 *  @title A surrogate to an ERC20 account, with additional factory method.
 */
contract SingularWalletWithE20E721 is BasicSingularWallet{

    constructor(
        string name
    )
    public
    BasicSingularWallet(name)
    {

    }

    event DebitInitialized(IDebit indexed debit, ISingularWallet indexed wallet, uint256 value);

    /**
    to create a debit account held by the wallet with some cash in it, from the caller's holdings. The user must
    have transferred some balance to the wallet address before issuing anything.
    */
    function activateE20Debit(
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
    }

    function activateTradable721(
        ERC721Tradable instance,    ///< an uninitialized contract
        IERC721 erc721,
        string _name,
        string _description,
        string _tokenURI,
        bytes32 _tokenURIDigest,    ///< the hash of any tokenURI content
        uint256 tokenId             ///< the id of the token that is ownded by the caller.
    )
    public
    ownerOnly
    {
        instance.init(
            _name,
            _description,
            _tokenURI,
            _tokenURIDigest,
            this,
            erc721,
            tokenId
        );
        // transfer the ownership of the tokenID from the wallet to the singular
        require(erc721.ownerOf(tokenId) == address(this), "the token id was not owned by this wallet");
        erc721.transferFrom(this, address(instance), tokenId);
    }

    function activateNonTradable721(
        ERC721NonTradable instance,    ///< an uninitialized contract
        IERC721 erc721,
        string _name,
        string _description,
        string _tokenURI,
        bytes32 _tokenURIDigest,     ///< the hash of any tokenURI content
        uint256 tokenId                 ///< the id of the token that is ownded by the caller.
    )
    public
    ownerOnly
    {
        instance.init(
            _name,
            _description,
            _tokenURI,
            _tokenURIDigest,
            this,
            erc721,
            tokenId
        );
        erc721.transferFrom(this, address(instance), tokenId);
    }

    function deactivateERC721ISingular(
        IERC721Singular instance,    ///< an initialized contract
        address receiver            ///< who to transfer the underlying token to
    )
    public
    ownerOnly
    {
        instance.unbind(receiver);
    }
}
