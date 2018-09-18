pragma solidity ^0.4.24;

import "../ISingularWallet.sol";
import "../SingularMeta.sol";
import "../ISingular.sol";
/**
 * @title Concrete asset token representing a single piece of asset, with
 * support of ownership transfers and transfer history.
 *
 * The owner of this item must be an instance of `SingularOwner`
 *
 * See the comments in the Singular interface for method documentation.
 * 
 * 
 * @author Bing Ran<bran@udap.io>
 *
 */
contract NonTransferrableSingular is ISingular, SingularMeta{

    ISingularWallet public owner; /// current owner

    address internal theCreator; /// who creates this token

    constructor(
        string _name,
        string _symbol,
        string _descr,
        string _tokenURI,
        bytes32 _tokenURIHash
    )
    SingularMeta(_name, _symbol, _descr, _tokenURI, _tokenURIHash)
    public
    {
        theCreator = msg.sender;
    }

    function creator()
    view
    external
    returns (
        address         ///< the owner elected
    ) {
        return theCreator;
    }

    /**
     * get the current owner as type of SingularOwner
     */
    function currentOwner() view external returns (ISingularWallet) {
        return owner;
    }


}
