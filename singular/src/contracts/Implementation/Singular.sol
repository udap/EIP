pragma solidity ^0.4.24;

import "../ISingular.sol";
import "../ISingularWallet.sol";
import "../../node_modules/openzeppelin-solidity/contracts/ReentrancyGuard.sol";
import "./Comment.sol";
import "./TransferHistory.sol";
import "./SingularMeta.sol";

//singular must transfer by its owner(SingularWallet) and between SingularWallets
contract SingularImpl is ISingular,SingularMeta, TransferHistory, Comment, ReentrancyGuard {



    address internal prototype; // token types, a ref to type information

    ISingularWallet internal owner; // the current owner
    ISingularWallet internal recipient; // the owner to be offered in an ownership transition
    ISingularWallet internal creator; // the first owner which is also the creator, unchangeable

/*    //is the operator of current singular, only is able to send singular
    address internal operator;*/

    // ownership transition
    uint256 internal expiry; // seconds since epoch time, absolutely. You can't cancel a transition/expiry and it will auto cancel when expiry < now or receiver reject/accept
    string internal transferReason;// transfer message


    event ReceiverApproved(
        address from,           ///< the from party of transaction
        address to,             ///< the receiver
        uint256 expiry,            ///< the time lock. in seconds since the epoch
        string reason           ///< additional note
    );
    /**
     * the ownership has been successfully transferred from A to B.
     */
    event Transferred(
        address from,
        address to,
        uint256 when,
        string reason
    );


    constructor(string _name, string _symbol, string _description, string _tokenURI) SingularMeta( _name,  _symbol,  _description,  _tokenURI)public
    {
        ISingularWallet(msg.sender).received(this,"new singular created");
        // is msg.sender safe? what if called from another contract?
        // should use tx.origin instead?

        //recordTransfer(0x0, msg.sender, now, "created");
        emit Transferred(address(0), msg.sender, now, "created");
    }

/*    function recordTransfer(address _from, address _to, uint256 _when, bytes32 note) internal {
        transferHistory.push(TransferRecord(_from, _to, _when, note));
        emit Transferred(_from, _to, _when, note);
    }*/


    /**
     * There can only be one approved address per token at a given time.
     * Can only be called by the token owner or an approved operator.
     * @param _to address to be approved for the given token ID
     * @param _expiry the dealline for the revceiver to the take the ownership with the preimage
     * @param _reason the reason for the transfer
     */

    function approveReceiver(address _to, uint256 _expiry, string _reason) notInTransition ownerOnly nonReentrant external {
        require(_to != owner);
        require(_expiry > now);
        recipient = _to;
        expiry = _expiry;
        transferReason = _reason;

        emit Approved(owner, _to, _expiry, _reason);

    }


    function sendTo(ISingularWallet _to, string _note, bool _sync, uint256 _expiry ) notInTransition ownerOnly nonReentrant public {
        // we still use the approve/take two-step pattern
        // which takes place in one transaction;
        require(_to != owner);
        if(_sync == true){
            _expiry = now + 1 minutes;
        }else{
            require(_expiry > now);
        }
        recipient = _to;
        transferReason = _note;// TODO: duplicated logic

        this.approveReceiver(_to, _expiry, _reason);

        if(_sync == true){
            _to.offer(this, _note);
        }else{
            _to.offerNotify(this, _note);
        }
    }


    function accept(string _reply) external inTransition{
        require(msg.sender == address(recipient), "only approver could accept or reject offer");
        ISingularWallet prev = owner;
        owner = msg.sender;
        delete recipient;
        delete expiry;

        //properties are set before invoke callbacks
        prev.sent(this,_reply);
        owner.received(this,_reply);

        emit Transferred(prev, owner, now, transferReason,_reply);
    }

    function reject(string _reply) external inTransition{
        require(msg.sender == address(recipient), "only approver could accept or reject offer");
        delete recipient;
        delete expiry;//time out expiry immediately so that you can transfer it fast-fail

        owner.offerRejected(this,_reply);

        emit TransferFailed(owner, recipient, now, transferReason,_reply);
    }



    /**
     * To get the full token ownership history of this token
     */
/*    function getHistory() view public returns (TransferRecord[]){
        return transferHistory;
    }*/

    modifier notInTransition() {
        require(!isInTransition());
        _;
    }

    modifier inTransition() {
        require(isInTransition());
        _;
    }

    modifier ownerOnly {
        require(msg.sender == owner);
        _;
    }

    modifier approved() {
        require(msg.sender == recipient);
        _;
    }


    function isInTransition() view public returns (bool) {
        return expiry >= now;
    }


    function currentOwner() view external returns (ISingularWallet){
        return owner;
    }

    function nextOwner() view external returns (ISingularWallet){
        return recipient;
    }

    function tokenType() view external returns(address){
        return prototype;
    }

    function creator() view external returns(address){
        return prototype;
    }
}
