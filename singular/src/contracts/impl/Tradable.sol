pragma solidity ^0.4.24;

import "../ISingularWallet.sol";
import "../ISingular.sol";
import "../ITradable.sol";
import "../SingularMeta.sol";
import "../ERC20/IDebit.sol";
import "./NonTradable.sol";


/**
@title A tradable Singular implementation

A countract of this class can be used in trading.

@author bing ran<bran@udap.io>

*/
contract Tradable is NonTradable, ITradable {
    function contractName() external view returns(string) {return "Tradable";}

    ISingularWallet theOwner; /// current owner

    ISingularWallet ownerPrevious; /// next owner choice

//    string private receiverNote;
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
    NonTradable(
        _name,
        _symbol,
        _descr,
        _tokenURI,
        _tokenURIHash,
        _tokenType,
        _wallet
    )
    {}


    /**
     * get the current owner as type of SingularOwner
     */
    function previousOwner() external view returns (ISingularWallet) {return ownerPrevious;}

    function nextOwner() external view returns (ISingularWallet){return transferOffer.nextOwner;}


    /**
     * There can only be one approved receiver at a given time. This receiver cannot
     * be changed before the expiry time.
     * Can only be called by the token owner (in the form of SingularOwner account or
     * the naked account address associated with the the Owner or an approved operator.
     * Note: the approved receiver can only accept() or reject() the offer. His power is limited
     * before he becomes the owner. This is in contract to the the transferFrom() of ERC20 or
     * ERC721.
     *
     */
    function approveReceiver(
        ISingularWallet _to,
        uint256 _validFrom,
        uint256 _validTill,
        string _note
    )
    external
    onlyOwnerOrOperator
    notInTx
    max128Bytes(_note)
    {

        require(address(_to) != address(0), "cannot send to null address");
        require(_validTill > now && _validTill > _validFrom, "expiry must be later than now and from");

        transferOffer.validFrom = _validFrom;
        transferOffer.validTill = _validTill;
        transferOffer.senderNote = _note;
        transferOffer.nextOwner = _to;

        emit  ReceiverApproved(
            address(theOwner),
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
        string _note
    )
    external
    inTransition
    permittedSender(transferOffer.nextOwner)
    max128Bytes(_note)
    {
        ownerPrevious = theOwner;
        theOwner = transferOffer.nextOwner; // the single most important step!!!
        reset();
        uint256 moment = now;
        ownerPrevious.sent(this, _note);
        theOwner.received(this, _note);

        emit Transferred(address(ownerPrevious), address(theOwner), moment,
            transferOffer.senderNote, _note);

    }

    /**
     * reject an offer. Must be called by the approved next owner(from the address
     * of the SingularOwner or SingularOwner.ownerAddress()).
     */
    function rejectTransfer(string note)
    external
    inTransition
    permittedSender(transferOffer.nextOwner)
    max128Bytes(note)
    {
        emit TransferRejected(this, transferOffer.nextOwner, now, note);
        reset();
    }

    /**
     * to send this token synchronously to a SingularWallet. It must call approveReceiver
     * first and invoke the "offer" function on the other SingularWallet. Setting the
     * current owner directly is not allowed.
     */
    function sendTo(
        ISingularWallet _to,
        string _note
    )
    external
    onlyOwnerOrOperator
    max128Bytes(_note)
    {
        uint t = now;
        this.approveReceiver(_to, t, t + 1 minutes, _note);
        _to.offer(this, _note);

    }

    function sendToAsync(
        ISingularWallet _to,
        string _note,
        uint256 _expiry
    )
    external
    onlyOwnerOrOperator
    max128Bytes(_note)
    {

        this.approveReceiver(_to, now, _expiry, _note);
        _to.offerNotify(this, _note);
    }

    /**
       offer to sell this item for some money in some currency type.
       It allows for overriding previous settings.

       There is no guarantee of the availability even within the valid sell time period,
       in contrast to other transactions where the counter-party is explicit.

       A sell offer can be overidden by swap offer or transfer offers.

    */
    function sellFor(
        address erc20,          ///< the currency type
        uint256 price,          ///< price
        uint256 validFrom,      ///< when an offer is valid from
        uint256 validTill,      ///< when the offer expires.
        string note             ///< additional note
    )
    external
    notInTx
    onlyOwnerOrOperator
    max128Bytes(note)
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
    onlyOwnerOrOperator
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
    onlyOwnerOrOperator
    notInTx
    max128Bytes(note)
    {
        swapOffer.target = target;
        swapOffer.validFrom = validFrom;
        swapOffer.validTill = validTill;
        //        swapOffer.note = note;

        emit SwapApproved(this, target, validFrom, validTill, note);
    }


    /**
    owner facing API. Source code must be verified to conduct the swap, due to lots of ownerships transitions.
    The caller must have setup a swap offer that goes in the direction opposite of this token's offer.
    todo: must be sure that the other party's contract is trustworthy
    */
    function acceptSwap(
        string note
    )
    public
    inSwap
    permittedSender2(swapOffer.target)
    max128Bytes(note)
    {
        ITradable counterTarget;
        uint256 counterVFrom;
        uint256 counterVTill;

        ITradable target = swapOffer.target;
//        // check the reciprocal offer
        (counterTarget, counterVFrom, counterVTill) = target.swapOffer();
        require(counterTarget == this, "the other offer was not targeted to me");
        // now transition the ownership on this side
        ISingularWallet counterOwner = target.owner();
        if (counterOwner != theOwner) {
            // ownership has not been changed. It's the first part of swapping
            ownerPrevious = theOwner;
            theOwner = counterOwner;
            // notify the owners for accounting
            ownerPrevious.sent(this, note);
            theOwner.received(this, note);
            ///////// I've done my part. Now it's your turn!
            target.acceptSwap(note); // callback
            ///////// make sure the other party has done his duty
            require(target.owner() == ownerPrevious, "swap() did not change the ownership over to me");
            reset();

            emit Swapped(this, target, now, note);
        }
        else {
            ownerPrevious = theOwner;
            theOwner = target.previousOwner();
            // notify the owners for proper accounting
            ownerPrevious.sent(this, note);
            theOwner.received(this, note);
            reset();
        }

    }

    function rejectSwap(
        string note
    )
    permittedSender2(swapOffer.target)
    max128Bytes(note)
    public {
        emit SwapRejected(this, swapOffer.target, now, note);
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

        // shall we require the sender be the debit owner?
        require(msg.sender == address(debitcard.owner()), "ownership does not match. ");

        ITradable coin = debitcard.toITradable();

        // let's set up a swap
        swapOffer.target = coin;
        swapOffer.validTill = now + 30 seconds;

        // swap the coin and this token
        coin.acceptSwap(sellOffer.note);

        require(
            coin.owner() == ownerPrevious
            && coin.previousOwner() == theOwner
            ,
            "swap in a purchase did not work"
        );
        reset();

        emit Sold(
            this,                       ///< the item for sell
            ownerPrevious,              ///< seller
            theOwner,               ///< buyer
            debitcard.currencyType(),   ///< the currency type
            sellOffer.price,            ///< price
            now,                        ///< when the tx completes
            sellOffer.note              ///< additional note);
        );
        reset(); // immediately ?
    }

    /***************************** modifiers **************************/

//    modifier notInTransition() {
//        require(now > transferOffer.validTill, "this singular is in ownership transition");
//        _;
//    }
//
    modifier inTransition() {
        require(inTransfer(),
            "not in valid ownership transition time window.");
        _;
    }

    function inTransfer() internal view returns (bool) {
        uint256 t = now;
        if( t >= transferOffer.validFrom && t <= transferOffer.validTill) {
            return !swapValid(swapOffer);
        }
        return false;
    }

    modifier permittedSender(ISingularWallet target) {
        address caller = msg.sender;
        require(
            caller == address(target)
            || caller == target.ownerAddress()
            ,
            "the sender was not the target wallet or the owner thereof");
        _;
    }

    modifier permittedSender2(ITradable target) {
        address caller = msg.sender;
        require(
            caller == address(target)
            || caller == address(target.owner())
            || caller == target.owner().ownerAddress()
            ,
            "the sender was not the target wallet or the owner thereof");
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

    modifier max128Bytes(string s) {
        require(bytes(s).length <= 128, "the string used more than 128 bytes");
        _;
    }

    modifier max256Bytes(string s) {
        require(bytes(s).length <= 256, "the string used more than 128 bytes");
        _;
    }

    /// only check if this token is involved with any transaction that has dedicated counterparty.
    function isInTx() internal view returns(bool) {
//        if (sellValid())
//            return true;
//        else
        if(swapValid(swapOffer))
            return true;
        else
            return inTransfer();
    }

    function reset() internal {
        delete sellOffer;
        delete swapOffer;
        delete transferOffer;
        delete theOperator;
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
        require(now > transferOffer.validTill, "this singular is in ownership transition");
        _;
    }
}