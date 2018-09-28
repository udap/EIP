pragma solidity ^0.4.24;

import "../ISingularWallet.sol";
import "../SingularMeta.sol";
import "../ISingular.sol";
import "../impl/NonTradable.sol";


/**
 * @title Concrete asset token representing a single piece of asset that's not tradable
 * The owner cannot be changed once it's set.
 *
 * See the comments in the Singular interface for method documentation.
 *
 * @author Bing Ran<bran@udap.io>
 *
 */
contract Diploma is NonTradable{
    string private constant NAME = "Diploma";
    function contractName() external view returns(string) {return NAME;}

    string public degree_;
    string public authority_;
    string public school_;
    bytes32 public whenStarted_;
    bytes32 public whenFinished_;
    string public program_;
    uint public whenIssues_;

    constructor(
        string degree,
        string authority,
        string school,
        bytes32 whenStarted,
        bytes32 whenFinished,
        string program,
        uint whenIssued,
        string scanCopyURL,
        bytes32 scanCopyHash,
        string description,
        ISingularWallet recipient
    )
    public
    NonTradable(degree, NAME, description, scanCopyURL, scanCopyHash, address(0), recipient)
    {
        degree_ = degree;
        authority_ = authority;
        school_ = school;
        whenStarted_ = whenStarted;
        whenFinished_ = whenFinished;
        program_ = program;
        whenIssues_ = whenIssued;
    }

}
