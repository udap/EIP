pragma solidity ^0.4.24;

import "./ISingularWallet.sol";
import "./ISingular.sol";
import "./ISingularMeta.sol";
import "./ISingularTokenFactory.sol";



/**
 * A contract that serves as the façade to the entire suite of contracts to support an UDAP-based application
 *

 An UDAP app uses this interface to manage all the assets: users, financial ledgers, state channels, asset as tokens, ownership transfers, important events, audit logs.

 An instance of Udapp is deployed when an application is registered with UDAP for a specific supported
 public chain, such as the Ethereum main chain.

 Major modules:

 1. finance: security bond, accounting with UDAP, payable/receivable
 2. user management: user/app state channels
 3. user asset management: asset tokenization, asset trading.

 This interface is inteded to be used by UDAP node via client API.

 * @author Bing Ran<bran@udap.io>
 *
 */

/**

A contract to mint tokens of the same type. This can be an ERC721 contract or something similar to
ERC721.

*/

interface IUdapp {


    ////// meta info

    /**
    to get the app's name
    */
    function name()
    view
    external
    returns (
        string theName
    );

    /**
    to get the app's name
    */
    function description()
    view
    external
    returns (
        string descr
    );


    function uri()
    view
    external
    returns (
        string theUri
    );


    function logoUri()
    view
    external
    returns (
        string theLogoUri
    );

    function developer()
    view
    external
    returns(
        address
    );



    /////// finance
    /**
    to deposit some money to the app's settlement account with UDAP
    */
    function deposit(
        uint256 amount,         ///< amount of UDAP coin to deposit to app acounnt
        bytes signatures        ///< the required signatures
    )
    external;

    /**
    address is probably a multi-sig wallet
    */
    function bindMoneyAccount(
        address eoa,            ///< the app owner's money account
        bytes signatures        ///< the required signatures
    )
    external;
    /**
    withdraw some deposit to the money account
    */
    function withDraw(
        uint256 amount,         ///< the amount of udap coin
        bytes signatures        ///< the required signatures
    )
    external;

    function balance()
    view
    external
    returns(
        uint256 theBalance         ///< the balance in udap coins
    );

    ////////////// user management

    /**
    to register a user with this application
    */
    function addUser(
        address ethAddress,
        ISingularMeta info
    )
    external
    returns
    (
        ISingularWallet wallet,  ///< the new wallet of this person assiciated with the app
        bytes signatures        ///< the required signatures
    );

    /////////// tokenizations

    /**
    to define an asset token generator to ming tokens of the same type.

    */
    function defineAssetTokenFactory(
        ISingularMeta meta,
        bytes signatures               ///< the required signatures
    )
    external
    returns(
        ISingularTokenFactory factory  ///< an contract that generate tokens
    );

    /**
    defines a label that can be associated with a token
    */
    function defineAssetType(
        ISingularMeta meta,
        bytes signatures               ///< the required signatures

    )
    external
    returns(
        address tokenType   ///< an contract that contains type formation that can be
                            ///< associated with a token.
    );

    /**
        to mint a Singular token and assign the ownership to a wallet
    */
    function mintTokenFor(
        address tokenType,
        ISingularMeta meta,     ///< the token info
        ISingularWallet owner,   ///< who should own the newly minted token
        bytes signatures        ///< the required signatures
    )
    external
    returns(
        ISingular token         ///< the token ninted
    );

    /**
     to transfer a token's ownership
    */
    function transferTokenOwnership(
        ISingular token,     ///< the token info
        ISingularWallet from,   ///< the current owner
        ISingularWallet to,     ///< the new owner
        bytes signatures        ///< the required signatures from poth parties
    )
    external;

}

