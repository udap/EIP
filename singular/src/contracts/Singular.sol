pragma solidity ^0.4.24;

import "./SingularBase.sol";
import "./Comment.sol";
import "./SingularMeta.sol";
import "./ISingularAll.sol";

contract Singular is SingularBase, SingularMeta, Comment{

    function contractName()
    external
    view
    returns(
        string              ///< the name of the contract class
    ) {
        return "Singular";
    }

    constructor() public payable{
    }

    function init (
        string _name,
        string _symbol,
        string _description,
        string _tokenURI,
        bytes32 _tokenURIDigest,
        address _wallet,
        address _singularCreator
    )
    unconstructed
    public
    {
        SingularMeta.init(_name,  _symbol,  _description,  _tokenURI, _tokenURIDigest);
        Comment.init();
        SingularBase.init(_wallet, _singularCreator);
    }
}
