pragma solidity ^0.4.24;

import "./SingularBase.sol";
import "./ISingular.sol";
import "./SingularWalletBase.sol";
import "./SingularFactory.sol";

contract MintableSingularGenerator is SingularWalletBase {
    SingularFactory internal singularFactory;
/*    constructor(SingularFactory _singularFactory) public payable{
        singularFactory = _singularFactory;
    }*/

    function init(
        SingularFactory _singularFactory,
        address _generatorOwner,
        address _generatorOperator
    )
    unconstructed
    public
    payable{
        SingularWalletBase.init( _generatorOwner,  _generatorOperator);
        singularFactory = _singularFactory;
    }

    string symbol;
    mapping(uint256 => ISingular) registry;
    uint256 total;

    function mint(
        string _name,
        string _symbol,
        string _description,
        string _tokenURI,
        bytes32 _tokenURIDigest,   ///< waht's the algorithm:  Keccak256 is 32 bytes
        address _wallet
    )
    constructed
    public
    returns (
        uint256 singularNo,
        ISingular created
    )
    {
        //created = new SingularImpl(_name, symbol, _description, _tokenURI,_tokenURIDigest, _to);
        singularFactory.createSingular(
            _name,
            _symbol,
            _description,
            _tokenURI,
            _tokenURIDigest,
            _wallet,
            this
        );
        singularNo = total;
        registry[singularNo] = created;
        total += 1;
        return;
    }

    function burn(uint256 _singularNo)
    constructed
    public{
        ISingular s = registry[_singularNo];
        require(msg.sender == address(s.owner()), "only owner can burn a token");

        IBurnable(registry[_singularNo]).burn("burn it");
    }
}
