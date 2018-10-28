pragma solidity ^0.4.24;

import "../ISingular.sol";
import "../SingularMeta.sol";
import "../ITradable.sol";

/**
 *
 * A contract that binds an address (EOA/SC) to a list of Singular tokens.
 * The owner account may not have the ability to handle the Singular tokens directly,
 * thus they can take advantage of this contract to achieve the effect.
 *
 * All the tokens MUST have this account as the owner of them. It's up to the implementation
 * to ensure the synchronization.
 *
 * The majority of token ownership management takes place in the `Singular` token.
 *
 * @author Bing Ran<bran@udap.io>
 *
 */
contract BasicSingularWallet is ISingularWallet, SingularMeta {
    address theCreator;

    /// list as the token set, since mapping does not give the key set
    ISingular[] internal tokens;
    uint assetTimestamp;
    bool internal autoReceive_ = true;        ///< policy in receiving incoming tokens

    address public ownerOfThis;

    constructor(
        string _name
    )
    public
    {
        SingularMeta.init(
            _name,
            "A BasicSingularWallet",
            "",
            "",
            0
        );
        theCreator = msg.sender;
        ownerOfThis = msg.sender;
    }

    /**
     * get the owner address.
     */
    function ownerAddress() view external returns (address) {
        return ownerOfThis;
    }

    function whenAssetsLastUpdated() external view returns (
        uint
    ) {
        return assetTimestamp;
    }

    /**
    this way of ownership transfer is too raw. Consider treadting a wallet as a tradable singular and use the
    sophisticated ownership management thereof.
    */
    function setOwner(address _owner) external {
        require(msg.sender == theCreator);
        ownerOfThis = _owner;
    }


    function isEffectiveOwner(
        address addr
    )
    external
    view
    returns (
        bool
    ) {
        return (addr == address(this) || addr == ownerOfThis);
    }

    /**
     * @dev invoked by Singular.accept() to notify the ownership change has completed.
     * The previous owner should remove the asset for the asset list to synchronize
     * the ownership relation with the token.
     This impl does not enforce the order of the element. There are no holes in the array.
     */
    function sent(
        ITradable token,
        string /*note*/
    )
    external
    {
        // this implementation leaves holes in the token array;
        require(token.previousOwner() == this, "the just sent token was not previously owned by this wallet");
        address t = address(token);
//        bool deleted;
        uint length = tokens.length;
        for (uint i = 0; i < length; i++) {
//            if (deleted) {
//                if (i == tokens.length -1) {
//                    // last element;
//                    tokens.length = totalTokens;
//                }
//                else {
//                    tokens[i] = tokens[i+1];
//                }
//            }
//            else {
                if (t == address(tokens[i])) {
                    if (i < length - 1)
                        tokens[i] = tokens[length - 1]; // use the last one to overwrite the current position
//                    totalTokens--;
                    tokens.length = length - 1;
                    assetTimestamp = now;
                    return;
//                    deleted = true;
                }
//            }
        }
        revert("the sent item was not previously owned by the wallet. State out of synch");

    }


    /**
     * @dev invoked by Singular.accept() to notify the ownership change has completed.
     * The current owner of the token must be this wallet.
     */

    function received(
        ISingular token,
        string /*note*/
    )
    external {
        require(token.owner() == this, "cannot register a token not owned by this wallet");
        require(!owns(token), "the token has been registered before");
        addToTokenSet(token);
    }

//    /**
//     a callback to notify the the wallet that the transaction
//     has been rejected. The parties may synchronize the local state to reflect the
//     ownership change.
//     */
//    function offerRejected(
//        ITradable /*token*/,    ///< the token of concern
//        string /*note*/         ///< the associated note
//    )
//    external {
//        revert("not implementd");
//    }
//

    /**
    To notify this account that a token transfer offer is ready. The function should return
    without doing anything on the token. This account can accept/reject the offer in a
    separate transaction.
    */
    function offerNotify(
        ITradable /*token*/, ///< the offered token
        string /*note*/         ///< additional information
    )
    external {
        revert("not implemented");
    }



    /**
     *  to offer a transfer
     * @dev to receive a token that has been assigned to the receiver as the next owner.
     * The receiver must decide to take it or not. If this account decides to accept
     * the offer, it MUST call the `accept()` on the token and return `true` If this account will not
     * accept the offer, it can ignore the offer by returning `false`;
     */
    function offer(
        ITradable _token,
        string note
        )
        external
        {
            ISingular token = _token.toISingular();
            require(okToAccept(_token));
            require(!owns(token));
            _token.acceptTransfer(note); // call back to accept the offer
            //
            require(token.owner() == this);
        }

    /**
     to send a token in this wallet to a recipient. The recipient SHOULD respond by calling `ISingular::accept()` or
     `ISingular::reject()` in the same transaction.
     */
    function sendTo(
        ISingularWallet wallet,     ///< the recipient
        ITradable token,             ///< the token to transfer
        string _senderNote
    )
    ownerOnly
    external
    {
        require(token.toISingular().owner() == this, "token was not owned by this wallet");
        token.sendTo(wallet, _senderNote);
    }


    /**
    to approve a new owner of a token and notify the recipient. The recipient SHOULD accept or reject the offer in
    a separate transaction. This is of the "offer/accept" two-step pattern.
    */
    function sendToNotify(
        ISingularWallet wallet,     ///< the recipient
        ITradable token,             ///< the token to transfer
        string _senderNote,
        uint expiry
    )
    ownerOnly
    external
    {
        token.sendToAsync(wallet, _senderNote, expiry);
    }


    function okToAccept(ITradable /*token*/) internal view returns (bool) {
        // todo: token specif policies
        return autoReceive_;
    }

    function setAutoReceive(
        bool autoReceive            ///< true for allowing incoming assets automatically, false otherwise
    )
    public
    ownerOnly
    {
        autoReceive_ = autoReceive;
    }

    function owns(ISingular token) public view returns (bool){
        for (uint i = 0; i < tokens.length; i++) {
            if (token == tokens[i]) {
               return true;
            }
        }
        return false;
    }


    /**
    to create a new ISingularWallet and move the ownership of he elements from this wallet to a new
    ISingularWallet. The new container's owner is the the owner of this container
    */
    function slice(
        ISingular[] /*elements*/    ///< the elements to move slice off from this container.
    )
    external
    returns(
        ISingularWallet         ///< the new ISingularWallet instance owning the elements
    )
    {
        revert("not implemented");
    }

    /**
    to dump all the elements of the specified wallet to this container. The incoming container's owner
    must be the same as this container.
    */
    function join(
        ISingularWallet /*container*/   ///< the source container which must be owned by the same owner as this.
    )
    external {
        revert("not implemented");
    }


    /**
     * add a token to the owned token set.
    */
    function addToTokenSet(ISingular token) internal {
        for (uint i = 0; i < tokens.length; i++) {
            if (token == tokens[i]) {
                revert("duplicated item when being inserted to token set in the wallet.");
            }
            else if (address(tokens[i]) == address(0)) {
                revert("there should not be a hole in the asset array");
            }
        }
        assetTimestamp = now;
        tokens.push(token);
    }

    /// enumeration of the owned tokens

    /**
     * retrieve all the Singular tokens. Note: there may be holes in the array. The caller should
     * skip those holes
     */
    function getAllTokens() view external returns (
        ISingular[] all,
        uint whenLastUpdated    ///< when the asset portfolio was changed)
    ){
        // TODO: privacy and permission control
        return (tokens, assetTimestamp);
    }

    /**
     * get the number of owned tokens
     */
    function numOfTokens() view external returns (
        uint256 tokenNum,
        uint timestamp
    ){
        // TODO: privacy and permission control
        return (tokens.length, assetTimestamp);
    }

    /**
     * get the token at a specific index. TODO how to properly implement this on list with holes?
     // TODO: privacy and permission control
     */
    function getTokenAt(uint256 idx, uint whenLastUpdated)
    view
    external
    returns (ISingular) {
        require(assetTimestamp != whenLastUpdated, "timestamp mismatch when getTokenAt()");
        require(idx >= 0 && idx < tokens.length, "idx was out of range");
        return tokens[idx];
    }

    modifier ownerOnly() {
        require(msg.sender == ownerOfThis, "msg.sender was not the ownerOfThis");
        _;
    }

}

