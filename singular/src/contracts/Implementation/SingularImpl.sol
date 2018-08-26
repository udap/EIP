pragma solidity ^0.4.24;

import "../Singular.sol";
import "../AssetOwner.sol";
import "../../node_modules/openzeppelin-solidity/contracts/ReentrancyGuard.sol";

//singular must transfer by its owner(AssetOwner) and between AssetOwners
contract SingularImpl is Singular, ReentrancyGuard {

/*    struct TransferRecord {
        address from;
        address to;
        uint when;
        bytes32 note;
    }*/

    string internal name;
    string internal description;
    bytes32 internal symbol;

    address internal prototype; // token types, a ref to type information
    AssetOwner internal owner; // the current owner
    AssetOwner internal recipient; // the owner to be in an ownership transition
    AssetOwner internal creator; // the first owner

/*    //is the operator of current singular, only is able to send singular
    address internal operator;*/

    // ownership transition
    uint256 internal expiry; // seconds since epoch time, absolutely. You can't cancel a transition/expiry and it will auto cancel when expiry < now or receiver reject/accept
    bytes32 internal transferReason;

    //TransferRecord[] internal transferHistory;

    event Approved(address from, address to, uint256 expiry);
    event SendTo(address from, address to, uint256 expiry);
    event Transferred(address from, address to, uint256 when, bytes32 note);

    constructor(string _name, bytes32 _symbol, string _description) public
    {
        name = _name;
        symbol = _symbol;
        description = _description;
        AssetOwner(msg.sender).receive(this);
        // is msg.sender safe? what if called from another contract?
        // should use tx.origin instead?

        //recordTransfer(0x0, msg.sender, now, "created");
        emit Transferred(0x0, msg.sender, now, "created");
    }

/*    function recordTransfer(address _from, address _to, uint256 _when, bytes32 note) internal {
        transferHistory.push(TransferRecord(_from, _to, _when, note));
        emit Transferred(_from, _to, _when, note);
    }*/

    //>>>>>>>>>>>>>>>>>>>>>>>>>>send-async
    /**
     * There can only be one approved address per token at a given time.
     * Can only be called by the token owner or an approved operator.
     * @param _to address to be approved for the given token ID
     * @param _expiry the dealline for the revceiver to the take the ownership with the preimage
     * @param _reason the reason for the transfer
     */
    //Async transfer
    function approve(address _to, uint256 _expiry, bytes32 _reason) notInTransition ownerOnly nonReentrant external {
        require(_to != owner);

        recipient = _to;
        expiry = _expiry;
        transferReason = _reason;

        emit Approved(owner, _to, _expiry);
        address(_to).offer(this, owner, _expiry, _reason);

    }

    function accept() external inTransition{
        require(msg.sender == address(recipient), "only approver could accept or reject offer");
        takeOwnership();
    }

    function reject() external inTransition{
        require(msg.sender == address(recipient), "only approver could accept or reject offer");
        resetApprove();
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<<send-async



    //>>>>>>>>>>>>>>>>>>>>>>>>>>send-sync
    //Sync transfer
    function sendTo(AssetOwner _to, bytes32 _reason) notInTransition ownerOnly nonReentrant public {
        // we still use the approve/take two-step pattern
        // which takes place in one transaction;
        //
        require(_to != owner);

        recipient = _to;
        expiry = now + 1 minutes;
        transferReason = _reason;

        emit SendTo(owner, recipient, expiry);

        bool result = _to.receive(this, _reason);
        if(result == false){
            resetApprove();
        }
        else{
            takeOwnership();
        }
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<<send-sync


    function resetApprove() internal{
        expiry  = 0;
    }

    /**
    * The approved account takes the ownership of this token. The caller must have been set as the next owner of this
    * token previously in a call by the current owner to the approve() function. The expiry time must be in the future
    * as of now.
    */
    //it should add and 'function-level reentrant lock
    function takeOwnership() internal {
        AssetOwner prev = owner;
        owner = msg.sender;
        /*delete operator;*/
        delete recipient;
        delete expiry;

        prev.sent(this);
        owner.received(this);

        emit Transferred(prev, owner, now, transferReason);
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


    /*function setOperator(address…) {}
    function revokeOperator() {}*/
}
