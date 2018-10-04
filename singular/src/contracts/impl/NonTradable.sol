pragma solidity ^0.4.24;

import "../ISingularWallet.sol";
import "../SingularMeta.sol";
import "../ISingular.sol";
import "../utils/CommonModifiers.sol";
import "../utils/MustInitialize.sol";


/**
 * @title Concrete asset token representing a single piece of asset that's not tradable
 * The owner cannot be changed once it's set.
 *
 * See the comments in the Singular interface for method documentation.
 * 
 * 
 * @author Bing Ran<bran@udap.io>
 *
 */
contract NonTradable is ISingular, SingularMeta, CommonModifiers {
    function contractName() external pure returns(string) {return "NonTradable";}

    ISingularWallet internal theOwner; /// current owner

    address internal theCreator; /// who creates this token
    address internal theOperator;

    uint timeCreated;

    address tokenTypeAddr;

    function init(
        string _name,
        string _symbol,
        string _descr,
        string _tokenURI,
        bytes32 _tokenURIHash,
        address _tokenTypeAddr,
        ISingularWallet _wallet
    )
    public
//    uninitialized // not required since the SingularMeta will do the check
    fromWallet(_wallet)
    max128Bytes(_name)
    max64Bytes(_symbol)
    max256Bytes(_descr)
    max128Bytes(_tokenURI)
    {
        SingularMeta.init(_name, _symbol, _descr, _tokenURI, _tokenURIHash);
        theCreator = msg.sender;
        theOwner = _wallet;
        _wallet.received(this, "set in NonTradable.init()");
        timeCreated = now;
        tokenTypeAddr = _tokenTypeAddr;
    }

    function owner() external view initialized returns(ISingularWallet){return theOwner;}
    function creator() external view returns (address) {return theCreator;}
    function operator() external view initialized returns(address) {return theOperator;}

    /**
    to configure the operator
    */
    function setOperator(
        address _address       ///< who to be the operator
    )
    external
    initialized
    ownerOnly
    {
        theOperator = _address;
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

    modifier ownerOnly() {
        require(
            msg.sender == address(theOwner)
            || msg.sender == theOwner.ownerAddress(),
            "only owner can do this action");
        _;
    }

    modifier onlyOwnerOrOperator() {
        address caller = msg.sender;
        require(
            caller != address(0)
            &&
            (
                address(theOwner) == caller
                || theOwner.ownerAddress() == caller
                || theOperator == caller
            ),
            "the msg.sender was not owner or operator"
        );
        _;
    }

    modifier fromWallet(ISingularWallet wallet) {
        address caller = msg.sender;
        require(
            caller != address(0)
        &&
        (
            address(wallet) == caller
            || wallet.ownerAddress() == caller
    //        || theOperator == caller // wallet does not have operator yet, but it should really be a tradable!
        ),
            "the msg.sender was not the wallet or its owner"
        );
        _;

    }

}
