pragma solidity ^0.4.24;

import "../ISingularWallet.sol";
import "../ITradable.sol";
import "../ERC20/IDebit.sol";
import "./NonTradable.sol";
import "./TradeExecutor.sol";
import "../ITradable.sol";


/**
 *
 * @title A tradable Singular implementation
 *
 * A contract of this class is an autonomous trading agent with the help from `TradeExecutor`
 *
 * @author bing ran<bran@udap.io>
 *
 */
contract Tradable is NonTradable, ITradable {
    function contractName() external pure returns(string) {return "Tradable";}

    ISingularWallet internal ownerPrevious; /// previous owner

    // let use the parent init function for the same purpose
    TradeExecutor public executor;

    function init(
        string _name,
        string _symbol,
        string _descr,
        string _tokenURI,
        bytes32 _tokenURIHash,
        address _tokenTypeAddr,
        ISingularWallet _wallet
    )
    public
    {
        NonTradable.init(
            _name,
            _symbol,
            _descr,
            _tokenURI,
            _tokenURIHash,
            _tokenTypeAddr,
            _wallet
        );
    }

    function previousOwner() external view initialized returns (ISingularWallet) {return ownerPrevious;}


    /**
     * To get the approved owner-to-be in a trading. It will be reset if the transfer offer is cancelled.
     */
    function nextOwner() external view initialized returns (ISingularWallet){return transferOffer.nextOwner;}

    /**
     * There can only be one approved receiver at a given time. This receiver cannot
     * be changed before the expiry time.
     * Can only be called by the token owner (in the form of SingularOwner account or
     * the naked account address associated with the the Owner or an approved operator.
     * Note: the approved receiver can only accept() or reject() the offer. His power is limited
     * before he becomes the owner. This is in contract to the the transferFrom() of ERC20 or
     * ERC721.
     */
    function approveReceiver(
        ISingularWallet _to,
        uint256 _validFrom,
        uint256 _validTill,
        string _note
    )
    external
    initialized
    ownerOrOperator("caller not owner for approveReceiver")
    notInTx
    max128Bytes(_note)
    {
        require(address(_to) != address(0), "receiver was set to 0x0 and was not valid");
        require(_validTill >= now && now >= _validFrom, "expiry must be later than now and validFrom");

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
    initialized
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
    initialized
    ownerOrOperator("not owner/operator for sendTo()")
    max128Bytes(_note)
    {
        uint t = now - 15;
        require(address(_to) != address(0), "cannot send to null address");

        transferOffer.validFrom = t;
        transferOffer.validTill = t + 60;
        transferOffer.senderNote = _note;
        transferOffer.nextOwner = _to;

        emit  ReceiverApproved(
            address(theOwner),
            address(_to),
            t,
            t + 60,
            _note);

        _to.offer(this, _note);

    }

    function sendToAsync(
        ISingularWallet _to,
        string _note,
        uint256 _expiry
    )
    external
    initialized
    ownerOrOperator("not owner/operator for sendToAsync()")
    max128Bytes(_note)
    {

        this.approveReceiver(_to, now, _expiry, _note);
        _to.offerNotify(this, _note);
    }

    /**
    called by swap executor to set the new owner, as the last step in swapping
    */
    function swapInOwner(
        ISingularWallet newOwner,
        string note
    )
    external
    initialized
    forTradeExecutor
    max128Bytes(note)
    {
        ownerPrevious = theOwner;
        theOwner = newOwner;
        ownerPrevious.sent(this, note);
        theOwner.received(this, note);
        reset();
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
        string              ///< additional note
    )
    external
    initialized
    notInTx
    ownerOrOperator("not owner/operator for sellFor()")
    {
        saleOffer.owner = theOwner;
        saleOffer.erc20 = erc20;
        saleOffer.price = price;
        saleOffer.validFrom = validFrom;
        saleOffer.validTill = validTill;
//        saleOffer.note = note;

        emit SaleOfferApproved(
            this, ///< the item for sell
            erc20,  ///< the currency type
            price,          ///< price
            validFrom,      ///< when an offer is valid from
            validTill,      ///< when the offer expires
            ""             ///< additional note
        );
    }

    function cancelSaleOffer()
    public
    ownerOrOperator("not owner/operator for cancelSaleOffer()")
    {
        delete saleOffer;
        // should emit an event
    }

    function approveSwap(
        ITradable target,
        uint validFrom,
        uint validTill,
        string
    )
    public
    initialized
    ownerOrOperator("not owner/operator for approveSwap()")
    notInTx
//    max128Bytes(note)
    {
        swapOffer.who = theOwner;
        swapOffer.target = target;
        swapOffer.validFrom = validFrom;
        swapOffer.validTill = validTill;
//        swapOffer.note = note;

        emit SwapApproved(this, target, validFrom, validTill, "");
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


    /***************************** modifiers **************************/

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
            || caller == address(target.toISingular().owner())
            || caller == target.toISingular().owner().ownerAddress()
        ,
            "the sender was not the target ITradable or the owner thereof");
        _;
    }

    modifier permittedSenders(address[4] addrs) {
        address caller = msg.sender;
        bool found = false;
        for (uint i = 0; i < addrs.length; i++) {
            if (caller == addrs[i]) {
                found = true;
                break;
            }
        }
        if(found) _;
    }

    modifier operatorOnly() {
        require(msg.sender == theOperator || msg.sender == address(executor), "sender was not the operator");
        _;
    }

    modifier notInTx() {
        require(!isInTx(), "the item is already in a transaction");
        _;
    }

    modifier forSale() {
        require(isForSale(), "not for sell");
        _;
    }

    function isForSale() public view returns (bool) {
        return saleOfferValid();
    }


    modifier hasExecutor() {
        require(address(executor) != address(0), "the transacton executor was not set on this tradable token");
        _;
    }

    modifier forTradeExecutor(){
        require(
            msg.sender == address(executor),
            "function was not called by the swap executor"
        );
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

    function reset()
    public
    ownerOrOperator("not owner for reset()")
    {
        delete saleOffer;
        delete swapOffer;
        delete transferOffer;
        delete theOperator;
    }

    function saleOfferValid() internal view returns(bool){
        uint t = now;
        return
        saleOffer.erc20 != address(0)
        && saleOffer.validFrom <= t
        && saleOffer.validTill >= t
        && saleOffer.price > 0;
    }

    function swapValid(SwapOffer storage _offer) internal view returns(bool){
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

    function isInSwap() public view returns(bool) {
        return (swapValid(swapOffer));
    }

    modifier max128Bytes(string s) {
        require(bytes(s).length <= 128, "the string used more than 128 bytes");
        _;
    }

    modifier ownerOrOperator(string s) {
        address caller = msg.sender;
        require(
            caller != address(0)
            &&
            (
                address(theOwner) == caller
                || theOwner.ownerAddress() == caller
                || theOperator == caller
                || executor == caller
            ),
//            "the msg.sender was neither owner nor operator"
            s
        );
        _;
    }


    function toISingular() public view returns(ISingular) {return ISingular(this);}
    ///// to find out the current block time.
    function ping() public view returns (uint){ return now;}

    /**
    * todo: need to sort out the executor vs operator relationship.
    */
    function setExecutor(TradeExecutor _exe)
    public
    ownerOnly
    {
        executor = _exe;
        theOperator = _exe;
    }
}