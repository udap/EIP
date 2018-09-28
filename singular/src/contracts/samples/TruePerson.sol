pragma solidity ^0.4.24;

import "../ISingularWallet.sol";
import "../SingularMeta.sol";
import "../ISingular.sol";
import "../impl/NonTradable.sol";


/**
 * @title aA true social person
 *
 * todo: Need privacy control, version control and also content operators
 * todo: reputation
 * todo: attribute update and events
 * todo: pictures and multimedia
 * XXX how to make sure the uniqueness? We want to make this a singleton.
 * @author Bing Ran<bran@udap.io>
 *
 */
contract TruePerson is NonTradable{
    string private constant NAME = "TruePerson";
    function contractName() external view returns(string) {return NAME;}

    enum Gender {Male, Female, Unknown}

    struct PersonEvent {
        int256 when;
        string what;
        string descrition;
    }

    string public birthDate_;
    string public deathDate_;
    string public titles_;
    Gender public gender_;
    string public nationality_;
    string public birthPlace_;
    string private nationalID_;
    string private phones_;
    string public emails_;
    string public socialNetworkAccounts;

    TruePerson public biologicalFather;
    TruePerson public biologicalMather;

    PersonEvent[] public events;

    // todo: consider using a map for all the attributes

    constructor(
        string name,
        string description,
        ISingularWallet recipient   ///
    )
    public
    NonTradable(name, NAME, description, "", 0, address(0), recipient)
    {
    }

}
