pragma solidity ^0.4.24;

import "./ISingularWallet.sol";
import "./ISingular.sol";
import "./ITradable.sol";
import "./SingularMeta.sol";
import "./ERC20/IDebit.sol";


/**
@title A tradable Singular implementation

A countract of this class can be used in trading.

@author bing ran<bran@udap.io>

*/
contract Tradable is ITradable, SingularMeta {
    function contractName()
    external
    view
    returns(
        string              ///< the name of the contract class
    ) {
        return "Tradable";
    }

    ISingularWallet currentOwner; /// current owner

    ISingularWallet ownerPrevious; /// next owner choice
    address internal theCreator; /// who creates this token
    uint256 whenCreated;

    address tokenTypeAddr;

    constructor(
        string _name,
        string _symbol,
        string _descr,
        string _tokenURI,
        bytes32 _tokenURIHash,
        address _tokenType,         ///< an address that indicate the origin of this instance.
        ISingularWallet _wallet
    )
    public
    SingularMeta(
        _name,
        _symbol,
        _descr,
        _tokenURI,
        _tokenURIHash
    )
    {
        theCreator = msg.sender;
        whenCreated = now;
        currentOwner = _wallet;
        tokenTypeAddr = _tokenType;
    }

    function creator()
    external
    view
    returns (
        address         ///< the owner elected
    ) {
        return theCreator;
    }

    function tokenType()
    external
    view
    returns(
        address                 ///< address that describes the type of the token.
    ){
        return tokenTypeAddr;
    }

    function creationTime() public view returns(uint256) {
        return whenCreated;
    }

    /**
     * get the current owner as type of SingularOwner
     */
    function previousOwner()
    external
    view
    returns (
        ISingularWallet
    ) {
        return ownerPrevious;
    }

    function nextOwner()
    external
    view
    returns (
        ISingularWallet
    ){
        return transferOffer.nextOwner;
    }

    /**
     * get the current owner as type of SingularOwner
     */
    function owner()
    external
    view
    returns (
        ISingularWallet
    ) {
        return currentOwner;
    }


    /**
     * There can only be one approved receiver at a given time. This receiver cannot
     * be changed before the expiry time.
     * Can only be called by the token owner (in the form of SingularOwner account or
     * the naked account address associated with the currentowner) or an approved operator.
     * Note: the approved receiver can only accept() or reject() the offer. His power is limited
     * before he becomes the owner. This is in contract to the the transferFrom() of ERC20 or
     * ERC721.
     *
     */
    function approveReceiver(
        ISingularWallet _to,
        uint256 _validFrom,
        uint256 _validTill,
        string _reason
    )
    external
    permitted(msg.sender, "approveReceiver", currentOwner)
    notInTransition
    {

        require(address(_to) != address(0), "cannot send to null address");
        require(_validTill > now && _validTill > _validFrom, "expiry must be later than now and from");

        transferOffer.validFrom = _validFrom;
        transferOffer.validTill = _validTill;
        transferOffer.senderNote = _reason;
        transferOffer.nextOwner = _to;

        emit  ReceiverApproved(
            address(currentOwner),
            address(transferOffer.nextOwner),
            _validFrom,
            _validTill,
            transferOffer.senderNote);

    }

    /**
     * The approved account takes the ownership of this token. The caller must have
     * been set as the next owner of this token previously in a call by the current
     * owner to the approve() function. The expiry time must be in the future
     * as of now. This function MUST call the sent() method on the original owner.
     TODO: evaluate re-entrance attack
     */
    function acceptTransfer(
        string _reason
    )
    external
    inTransition
    permitted(msg.sender, "accept", transferOffer.nextOwner)
    {
        ownerPrevious = currentOwner;
        currentOwner = transferOffer.nextOwner; // the single most important step!!!
        reset();
        // transferHistory.push(TransferRec(ownerPrevious, owner, now, senderNote, _reason, this));
        uint256 moment = now;
        ownerPrevious.sent(this, _reason);
        currentOwner.received(this, _reason);

        emit Transferred(address(ownerPrevious), address(currentOwner), moment,
            transferOffer.senderNote, _reason);

    }

    /**
     * reject an offer. Must be called by the approved next owner(from the address
     * of the SingularOwner or SingularOwner.ownerAddress()).
     */
    function rejectTransfer(string note)
    external
    permitted(msg.sender, "rejectTransfer", transferOffer.nextOwner)
    {
//        receiverNote = note;
        reset();
    }

    /**
     * to send this token synchronously to a SingularWallet. It must call approveReceiver
     * first and invoke the "offer" function on the other SingularWallet. Setting the
     * current owner directly is not allowed.
     */
    function sendTo(
        ISingularWallet _to,
        string _reason
    )
    external
    {

        uint t = now;
        this.approveReceiver(_to, t, t + 1 minutes, _reason);
        _to.offer(this, _reason);

    }

    function sendToAsync(
        ISingularWallet _to,
        string _reason,
        uint256 _expiry
    )
    external
    {

        this.approveReceiver(_to, now, _expiry, _reason);
        _to.offerNotify(this, _reason);
    }

    /**
       offer to sell this item for some money in some currency type.
       It allows for overriding previous settings.

    */
    function sellFor(
        address erc20,          ///< the currency type
        uint256 price,          ///< price
        uint256 validFrom,      ///< when an offer is valid from
        uint256 validTill,      ///< when the offer expires
        string note             ///< additional note
    )
    external
    notInTransition
    {
        sellOffer.erc20 = erc20;
        sellOffer.price = price;
        sellOffer.validFrom = validFrom;
        sellOffer.validTill = validTill;
        sellOffer.note = note;

        emit SellOfferApproved(
            this, ///< the item for sell
            erc20,  ///< the currency type
            price,          ///< price
            validFrom,      ///< when an offer is valid from
            validTill,      ///< when the offer expires
            note             ///< additional note
        );
    }

    function cancelSellOffer()
    public
    {
        delete sellOffer;
        // should emit an event
    }

    function approveSwap(
        ITradable target,
        uint validFrom,
        uint validTill,
        string note
    )
    public
    notInTx
    {
        swapOffer.target = target;
        swapOffer.validFrom = validFrom;
        swapOffer.validTill = validTill;
        //        swapOffer.note = note;

        emit SwapApproved(this, target, validFrom, validTill, note);
    }


    /**
    owner facing API. Source code must be verified to conduct the swap, due to lots of ownerships transitions.
    */
    function acceptSwap(
        ITradable offered
    )
    public
    inSwap
    {
        //        approveSwap(offered, now, now + 10 seconds, "");
        // simply change ownership?
        //        SwapOffer offer = offered.swapOffer();
        ITradable target;
        uint256 vFrom;
        uint256 vTill;
        string memory note = "swap";

        (target, vFrom, vTill) = offered.swapOffer(); // look how struct is returned

        require(target == this, "the other offer is not targeted to me");
        require(swapValid(SwapOffer(target, vFrom, vTill)), "the other offer is not valid, expired?");

        ownerPrevious = currentOwner;
        currentOwner = offered.owner();

        ownerPrevious.sent(this, note);
        currentOwner.received(this, note);

        // XXX bad casting
        offered.commitOwnerChange();

        require(offered.owner() == currentOwner, "swap() did not change the ownership over to me");
        reset();

        emit Swapped(offered, this, now, note);
    }

    function rejectSwap(
    )
    permitted(msg.sender, "rejectTransfer", swapOffer.target.owner())
    public {
        reset();
    }

    function commitOwnerChange()
    public
    inSwap
    {
        ITradable target = swapOffer.target;
        require(msg.sender == address(target), "swap target mismatch");
        // has the other party changed the owner yet?  Is this really enough an insurance?
        require(target.owner() == currentOwner, "the ownership was not set to my owner");

        //
        ownerPrevious = currentOwner;
        currentOwner = target.previousOwner(); // assuming...

        ownerPrevious.sent(this, "swapOffer");
        currentOwner.received(this, "swap");

        reset();
    }

    /**
        Debit owner calls this function to make a purchase.
        Note: no change is returned if the denomination is > price.
        The debit card MUST been set up with a proper SwapOffer with the denomination and swap target
     */
    function buy(
        IDebit debitcard   ///< the money
    )
    external
    inSell
    {
        require(debitcard.denomination() >= sellOffer.price, "not enough fund to make the purchase");
        require(debitcard.currencyType() == sellOffer.erc20, "currency types mismatch");

        // shall re require the sender be the debit owner?
        require(msg.sender == address(debitcard.owner()), "ownership does not match. ");

        ITradable target;
        uint256 vFrom;
        uint256 vTill;

        // XXX ugly casting to avoid the inter-dependency of ITradable and IDebit
        (target, vFrom, vTill) = ITradable(debitcard).swapOffer();


        require(target == this, "the cash is not targeted to me");
        require(swapValid(SwapOffer(target, vFrom, vTill)), "the other offer is not valid, expired?");

        ownerPrevious = currentOwner;
        currentOwner = debitcard.owner();

        ownerPrevious.sent(this, "bought out");
        currentOwner.received(this, "bought in");

        Tradable(debitcard).commitOwnerChange();

        require(debitcard.owner() == ownerPrevious, "swap() did not change the ownership over to me");
        reset();

        uint p = sellOffer.price;
        string storage orinote = sellOffer.note;
        reset(); // immediately ?


        emit Sold(
            this, ///< the item for sell
            ownerPrevious, ///< seller
            currentOwner,  ///< buyer
            debitcard.currencyType(),  ///< the currency type
            p,          ///< price
            now,           ///< when the tx completes
            orinote             ///< additional note);
        );
    }

    /***************************** modifiers **************************/

    modifier notInTransition() {
        require(now > transferOffer.validTill, "this singular is in ownership transition");
        _;
    }

    modifier inTransition() {
        require(inTransfer(),
            "not in valid ownership transition time window.");
        _;
    }

    function inTransfer() internal view returns (bool) {
        uint256 t = now;
        return t >= transferOffer.validFrom && t <= transferOffer.validTill;
    }

    modifier ownerOnly() {
        require(msg.sender == address(currentOwner), "only owner can do this action");
        _;
    }

    modifier permitted(
        address caller,
        bytes32 action,
        ISingularWallet authenticator
    ) {
        require(
            address(authenticator) == caller ||
        authenticator.isActionAuthorized(caller, action, this),
            "action not authorized");
        _;
    }

    modifier notInTx() {
        require(!isInTx(), "the item is already in a transaction");
        _;
    }

    modifier inSell() {
        require(sellValid(), "this item is not for sell at this moment");
        _;
    }

    function isInTx() internal view returns(bool) {
        if (sellValid())
            return true;
        else if(swapValid(swapOffer))
            return true;
        else
            return inTransfer();
    }

    function reset() internal {
        delete sellOffer;
        delete swapOffer;
        delete transferOffer;
    }

    function sellValid() internal view returns(bool){
        uint t = now;
        return
        sellOffer.erc20 != address(0)
        && sellOffer.validFrom <= t
        && sellOffer.validTill >= t
        && sellOffer.price > 0;
    }

    function swapValid(SwapOffer _offer) internal view returns(bool){
        uint t = now;
        return
        _offer.target != address(0)
        && _offer.validFrom <= t
        && _offer.validTill >= t;
    }


    modifier inSwap() {
        require(swapValid(swapOffer), "this item is not in swap mode");
        _;
    }
}