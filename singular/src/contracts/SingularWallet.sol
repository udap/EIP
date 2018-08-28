pragma solidity ^0.4.24;

import "./ISingular.sol";
import "./SingularMeta.sol";

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

    constructor(string _name, string _symbol, string _descr, string _tokenURI)
    SingularMeta(_name, _symbol, _descr, _tokenURI)
    public
    {

    }



    /// list as the token set, since mapping does not give the ket set
    ISingular[] internal tokens;
    uint256 internal totalTokens;

    address public ownerOfThis;

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

    mapping(address => mapping(bytes4 => mapping(address => bool))) internal operatorApprovals;

    /**
     * to find out if an address is an authorized operator for the Singular token's
     * ownership.
     */
    function isActionAuthorized(address _address, bytes4 _selector, ISingular _singular) view external returns (bool) {
        return operatorApprovals[_singular][_selector][_address];
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
        view 
        external
        {
            operatorApprovals[_singular][_selector][_address] = _ok;
        }

    /**
     * @dev invoked by Singular.accept() to notify the ownership change has completed.
     * The previous owner should remove the asset for the asset list to synchronize
     * the ownership relation with the token.
     */
     
    function sent(ISingular token, string note) external returns (bool) {
        // this imple leaves holes in the token array;
        require(token.previousOwner() == this);
        for (uint i = 0; i < tokens.length; i++) {
            if (token == tokens[i]) {
                delete tokens[i];
                totalTokens--;
                operatorApprovals[token] = ;
                break;
            }
        }   
    }

    /**
     * @dev invoked by Singular.accept() to notify the ownership change has completed.
     * The current owner of the token must be this wallet.
     */
     
    function received(ISingular token, string note) external returns (bool) {
        require(token.currentOwner() == this);
        require(!alreadyOwn(token));
        addToTokenSet(token);
    }


    /**
     * @dev to receive a token that has been assigned to the receiver as the next owner.
     * The receiver must decide to take it or not. If this account decides to accept
     * the offer, it MUST call the `accept()` on the token and return `true` If this account will not
     * accept the offer, it can ignore the offer by returning `false`;
     */
    function offer(
        ISingular token, 
        string note
        ) 
        external 
        returns (bool) 
        {
            require(okToAccept(token));
            require(!alreadyOwn(token));
            token.accept(note); // call back to accept the offer
            //
            require(token.currentOwner() == this);
            addToTokenSet(token);
        }
    

    function okToAccept(ISingular token) internal returns (bool) {
    // TODO: anti-spamming procedures
        return true;
    }

    function alreadyOwn(ISingular token) internal returns (bool){
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
        for (int i = 0; i < tokens.lengthl; i++) {
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


    /**
    returns the number of tokens for a specific type. The type information is extracted from `Singular::tokenType()`
    
    The value of this function is not doubtful ! 
    */
    function numOfTokensOfType(string tokenType) view external returns (uint256) {
        for (var i = 0; i < tokens.lengthl; i++) {
            uint256 c = 0;
            ISingular s = tokens[i];
            if (address(s) != address(0)) {
                var symbol = s.symbol();
                if (symbol == tokenType)
                c++;
            }
        }
        return c;
    }
}

