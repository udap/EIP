pragma solidity ^0.4.24;

import "../ISingular.sol";
import "../SingularMeta.sol";
import "../ITradable.sol";

/**
 * A contract that binds an address (EOA/SC) to a list of Singular tokens. The
 * owner account may not have the ability to handle the Singular tokens directly,
 * thus they can take advantage of this contract to achieve the effect.
 *
 * All the tokens MUST have this account as the owner of them. It's up to the implemntation
 * to ensure the synchronization.
 *
 * The majority of token ownership management takes place in the `Singular` token.
 *
 * @author Bing Ran<bran@udap.io>
 *
 */
contract SingularWallet is ISingularWallet, SingularMeta {/// can implement Singular to make a composite pattern


    address theCreator;

    /// list as the token set, since mapping does not give the ket set
    ISingular[] internal tokens;
    uint256 internal totalTokens;

    /// old verions of authorization is kept due to mapping's technical limitation
    /// we use the tokenVersion to track the latest set of authorizations
    mapping(address => uint32) tokenVersion;

    mapping(
        address => mapping(     // singular contract address
            uint32 => mapping(      // version of ownership
                bytes32 => mapping(     // action name
                    address => bool     //  the visitor, can / cannot
                    )))) internal operatorApprovals;

    address public ownerOfThis;

    constructor(string _name, string _symbol, string _descr, string _tokenURI , bytes32 _tokenURIDigest)
    SingularMeta(_name, _symbol, _descr, _tokenURI, _tokenURIDigest)
    public
    {
        theCreator = msg.sender;    
    }

    /**
     * get the owner address.
     */
    function ownerAddress() view external returns (address) {
        return ownerOfThis;
    }

    function setOwner(address _owner) external {
        require(msg.sender == theCreator);
        ownerOfThis = _owner;
    }

    /**
     * to find out if an address is an authorized operator for the Singular token's
     * ownership.
     */
    function isActionAuthorized(address _address, bytes32 _selector, ISingular _singular) view external returns (bool) {
        return operatorApprovals[_singular][tokenVersion[_singular]][_selector][_address];
    }

    /**
    to configure the operator setting.
    */
    function authorizeOperator(
        address _address, 
        bytes4 _selector, 
        ISingular _singular, 
        bool _ok
        ) 
        external
        {
            operatorApprovals[_singular][tokenVersion[_singular]][_selector][_address] = _ok;
        }

    /**
     * @dev invoked by Singular.accept() to notify the ownership change has completed.
     * The previous owner should remove the asset for the asset list to synchronize
     * the ownership relation with the token.
     */
    function sent(ITradable token, string note) external {
        // this implementation leaves holes in the token array;
        require(token.previousOwner() == this);
        // TODO: handle the note in a transaction history
        for (uint i = 0; i < tokens.length; i++) {
            if (token == tokens[i]) {
                delete tokens[i];
                totalTokens--;
                // bump up ownership version in case of later reownning 
                tokenVersion[token]++;
                break;
            }
        }   
    }

    /**
     * @dev invoked by Singular.accept() to notify the ownership change has completed.
     * The current owner of the token must be this wallet.
     */
     
    function received(ITradable token, string note) external {
        require(token.owner() == this);
        require(!alreadyOwn(token));
        addToTokenSet(token);
    }


    /**
       to offer a transfer
     * @dev to receive a token that has been assigned to the receiver as the next owner.
     * The receiver must decide to take it or not. If this account decides to accept
     * the offer, it MUST call the `accept()` on the token and return `true` If this account will not
     * accept the offer, it can ignore the offer by returning `false`;
     */
    function offerTransfer(
        ITradable token,
        string note
        ) 
        external 
        {
            require(okToAccept(token));
            require(!alreadyOwn(token));
            token.acceptTransfer(note); // call back to accept the offer
            //
            require(token.owner() == this);
            addToTokenSet(token);
        }

    /**
     to send a token in this wallet to a recipient. The recipient SHOULD respond by calling `ISingular::accept()` or
     `ISingular::reject()` in the same transaction.
     */
    function send(
        ISingularWallet wallet,     ///< the recipient
        ITradable token             ///< the token to transfer
    )
    ownerOnly
    external
    {
        token.sendTo(wallet, "from wallet");
    }


    /**
    to approve a new owner of a token and notify the recipient. The recipient SHOULD accept or reject the offer in
    a separate transaction. This is of the "offer/accept" two-step pattern.
    */
    function sendNotify(
        ISingularWallet wallet,     ///< the recipient
        ITradable token,             ///< the token to transfer
        uint expiry
    )
    ownerOnly
    external
    {
        token.sendToAsync(wallet, "from wallet", expiry);
    }


    function okToAccept(ITradable token) internal returns (bool) {
    // TODO: anti-spamming procedures
        return true;
    }

    function alreadyOwn(ISingular token) view internal returns (bool){
        for (uint i = 0; i < tokens.length; i++) {
            if (token == tokens[i]) {
               return true;
            }
        }
        return false;
    }

    /**
     * add a token to the owned token set.
    */
    function addToTokenSet(ISingular token) internal {
        totalTokens++; // TODO: should check range
        for (uint i = 0; i < tokens.length; i++) {
            if (token == tokens[i]) {
                revert("duplicated");
            }
            else if (address(tokens[i]) == address(0)) {
                tokens[i] = token;
                return;
            }
        }
        tokens.push(token);
    }
    


    /// enueration of the owned tokens
    /**
     * retrieve all the Singular tokens. Note: there may be holes in the array. The caller should
     * skip those holes
     */
    function getAllTokens() view external returns (ISingular[]) {
        // TODO: privacy and permission control
        return tokens;
    }

    /**
     * get the number of owned tokens
     */
    function numOfTokens() view external returns (uint256){
        // TODO: privacy and permission control
        return totalTokens;
    }

    /**
     * get the token at a specific index. TODO how to properly implement this on list with holes?
     */
    function getTokenAt(uint256 idx) view external returns (ISingular) {
        // TODO: privacy and permission control
        revert("not implemented yet");
    }

    modifier ownerOnly() {
        require(msg.sender == ownerOfThis);
        _;
    }
}

