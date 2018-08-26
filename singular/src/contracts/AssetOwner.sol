pragma solidity ^0.4.24;

import "./Singular.sol";

/*
 * A contract that binds an address (EOA/SC) to a list of Singular tokens. The 
 * owner account may not have the ability to handle the Singular tokens directly, 
 * thus they can take advantage of this contract to achieve the effect. 
 * 
 * All the tokens MUST have this account as the owner of them. It's up to the implemntation
 * to ensure the synchronization. 
 * 
 * The majority of token ownership management takes place in the `Singular` token. 
 * 
 * 
 */
interface AssetOwner {

    event Offered(address singular);

    event Receive(address singular);
    /*
     * get the owner address. 
     */
    function ownerAddress() view external returns(address);
    
    /*
     * to find out if an address is an authorized operator for the Singular token's 
     * ownership.
     */
    function isAuthorized(address, Singular) view external returns(bool);
    
    /*
     * @dev invoked by Singular.accept() to notify the ownership change has completed.
     * The previous owner should remove the asset for the asset list to synchronize
     * the ownership relation with the token. 
     * @param token the token that has been sent to a receiver, which MUST be the 
     * current owner of this token.
     */
    function sent(Singular) external returns(bool);

    function received(Singular) external returns(bool);

    /*
     * @dev to receive a token that has been assigned to the receiver as the next owner. 
     * The receiver must decide to take it or not. If this account decides to accept 
     * the offer, it MUST call the `accept()` on the token and return `true` If this account will not 
     * accept the offer, it can ignore the offer by returning `false`;
     */
    function offer(Singular, bytes32) external returns(bool);

    function receive(Singular, bytes32) external returns(bool);

/// enueration of the owned tokens    
    /*
     * retrieve all the Singular tokens
     */
    function getAllTokens() view external returns(Singular[]);
     
    /*
     * get the number of owned tokens
     */
    function numOfTokens() view external returns(uint256);

    /*
     * get the token at a specific index.
     * @param idx, in the range of [0, numOfTokens())
     */
    function getTokenAt(uint256 idx) view external returns(Singular);
 
}

