pragma solidity ^0.4.24;

import "../ISingularWallet.sol";
import "../SingularMeta.sol";
import "../ISingular.sol";


/**
 * @title Concrete asset token representing a single piece of asset that's not tradable
 *
 *
 * See the comments in the Singular interface for method documentation.
 * 
 * 
 * @author Bing Ran<bran@udap.io>
 *
 */
contract NonTradableSingular is ISingular, SingularMeta{
    function contractName()
    external
    view
    returns(
        string              ///< the name of the contract class
    ) {
        return "NonTradableSingular";
    }

    ISingularWallet theOwner; /// current owner

    address internal theCreator; /// who creates this token

    uint timeCreated;

    address tokenTypeAddr;

    constructor(
        string _name,
        string _symbol,
        string _descr,
        string _tokenURI,
        bytes32 _tokenURIHash,
        address _tokenTypeAddr,
        ISingularWallet _wallet
    )
    public
    SingularMeta(_name, _symbol, _descr, _tokenURI, _tokenURIHash)
    {
        theCreator = msg.sender;
        theOwner = _wallet;
        timeCreated = now;
        tokenTypeAddr = _tokenTypeAddr;
    }

    function creator()
    view
    external
    returns (
        address         ///< the owner elected
    ) {
        return theCreator;
    }

    function owner()
    public
    returns(
        ISingularWallet
    )
    {
        return theOwner;
    }

    /**
    * get the creation time
    */
    function creationTime()
    external
    view
    returns (
        uint256         ///< when this thing was created
    ) {
        return timeCreated;
    }

    /**
    a Singular that can be associated with an address that describes the type information.
    */
    function tokenType()
    external
    view
    returns(
        address                 ///< address that describes the type of the token.
    ) {
        return tokenTypeAddr;
    }

}
