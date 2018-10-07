pragma solidity ^0.4.24;

import "./MintableSingularGenerator.sol";

contract LimitedSingularGenerator is MintableSingularGenerator{
/*    constructor(){

    }*/

    uint256 limit;
    function init(
        SingularFactory _singularFactory,
        address _generatorOwner,
        address _generatorOperator,
        uint256 _limit
    )
    unconstructed
    public
    payable{
        MintableSingularGenerator.init(_singularFactory,  _generatorOwner,  _generatorOperator);
        singularFactory = _singularFactory;
        limit = _limit;
    }


    function mint(
        string _name,
        string _symbol,
        string _description,
        string _tokenURI,
        bytes32 _tokenURIDigest,
        address _wallet
    )
    constructed
    public
    returns (
        uint256 singularNo,
        ISingular created
    )
    {
        require(limit>= total);
        (singularNo, created) = super.mint(_name,  _symbol,  _description,  _tokenURI,  _tokenURIDigest, _wallet);
        return;
    }

    function burn(
        uint256
        _singularNo
    )
    constructed
    public
    {
        super.burn(_singularNo);
    }
}
