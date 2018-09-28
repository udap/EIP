pragma solidity ^0.4.24;

import "../ITradable.sol";


/**
@title A tradable Singular implementation

A countract of this class can be used in trading.

@author bing ran<bran@udap.io>

*/
library TradableLib {

    /**
     * the ownership has been successfully transferred from A to B.
     */
    event Sold(
        ITradable indexed item, ///< the item for sell
        ISingularWallet indexed seller, ///< seller
        ISingularWallet indexed buyer,  ///< buyer
        address erc20,  ///< the currency type
        uint256 price,          ///< price
        uint256 when,           ///< when the tx completes
        string note             ///< additional note
    );


    /**
     * to indicate that a swap arrangement has completed
     */
    event Swapped(
        ITradable indexed from, ///< the item for swap
        ITradable indexed to,  ///< the desired item
        uint when,              ///< when this happened
        string note             ///< additional note
    );


//    /**
//       offer to sell this item for some money in some currency type.
//       It allows for overriding previous settings.
//
//       There is no guarantee of the availability even within the valid sell time period,
//       in contrast to other transactions where the counter-party is explicit.
//
//       A sell offer can be overidden by swap offer or transfer offers.
//
//    */
//    function sellFor(
//        address erc20,          ///< the currency type
//        uint256 price,          ///< price
//        uint256 validFrom,      ///< when an offer is valid from
//        uint256 validTill,      ///< when the offer expires.
//        string note             ///< additional note
//    )
//    external
//    notInTx
//    onlyOwnerOrOperator
//    max128Bytes(note)
//    {
//        sellOffer.erc20 = erc20;
//        sellOffer.price = price;
//        sellOffer.validFrom = validFrom;
//        sellOffer.validTill = validTill;
//        sellOffer.note = note;
//
//        emit SellOfferApproved(
//            this, ///< the item for sell
//            erc20,  ///< the currency type
//            price,          ///< price
//            validFrom,      ///< when an offer is valid from
//            validTill,      ///< when the offer expires
//            note             ///< additional note
//        );
//    }
//
//    function cancelSellOffer()
//    public
//    onlyOwnerOrOperator
//    {
//        delete sellOffer;
//        // should emit an event
//    }
//
//    function approveSwap(
//        ITradable target,
//        uint validFrom,
//        uint validTill,
//        string note
//    )
//    public
//    {
//        this.swapOffer.target = target;
//        this.swapOffer.validFrom = validFrom;
//        this.swapOffer.validTill = validTill;
//        this.swapOffer.note = note;
//
//        emit SwapApproved(this, target, validFrom, validTill, note);
//    }


    /**
    owner facing API. Source code must be verified to conduct the swap, due to lots of ownerships transitions.
    The caller must have setup a swap offer that goes in the direction opposite of this token's offer.
    todo: must be sure that the other party's contract is trustworthy
    */
    function acceptSwap(
        ITradable self,
        string note
    )
//    internal
    selfCaller(address(self))
//    inSwap
//    permittedSender2(swapOffer.target)
//    max128Bytes(note)
    {
        ITradable target;
        uint256 vFrom;
        uint256 vTill;
        string memory thisNote;
        (target, vFrom, vTill, thisNote) = self.swapOffer();

        //        // check the reciprocal offer
        ITradable counterTarget;
        uint256 counterVFrom;
        uint256 counterVTill;
        string memory counterNote;

        (counterTarget, counterVFrom, counterVTill, counterNote) = target.swapOffer();
        require(counterTarget == self, "the other offer was not targeted to me");
        // now transition the ownership on this side
        ISingularWallet counterOwner = target.owner();
        if (counterOwner != self.owner()) {
            // ownership has not been changed. It's the first part of swapping
            self.hardSetOwner(counterOwner, note);
//            this.ownerPrevious = this.theOwner;
//            this.theOwner = counterOwner;
            // notify the owners for accounting
//            this.ownerPrevious.sent(this, note);
//            this.theOwner.received(this, note);
            ///////// I've done my part. Now it's your turn!
            target.acceptSwap(note); // callback
            ///////// make sure the other party has done his duty
            require(target.owner() == self.previousOwner(), "swap() did not change the ownership over to me");
            self.reset();

            emit Swapped(self, target, now, note);
        }
        else {
            self.hardSetOwner(target.previousOwner(), note);
//            this.ownerPrevious = this.theOwner;
//            this.theOwner = target.previousOwner();
//            // notify the owners for proper accounting
//            this.ownerPrevious.sent(this, note);
//            this.theOwner.received(this, note);
            self.reset();
        }

    }
//
//    function rejectSwap(
//        string note
//    )
//    permittedSender2(swapOffer.target)
//    max128Bytes(note)
//    public {
//        emit SwapRejected(this, swapOffer.target, now, note);
//        reset();
//    }

    /**
        Debit owner calls this function to make a purchase.
        Note: no change is returned if the denomination is > price.
        The debit card MUST been set up with a proper SwapOffer with the denomination and swap target
     */
    function buy(
        ITradable self,     ///<
        IDebit debitCard   ///< the money
    )
    external
    view
    selfCaller(address(self))
    {
        address erc20;
        uint256 price;
        uint256 validFrom;
        uint256 validTill;
        string memory note;

        (erc20, price, validFrom, validTill, note) = self.sellOffer();

        require(debitCard.denomination() >= price, "not enough fund to make the purchase");
        require(debitCard.currencyType() == erc20, "currency types mismatch");

        // shall we require the sender be the debit owner?
        require(msg.sender == address(debitCard.owner()), "ownership does not match. ");

        ITradable coin = debitCard.toITradable();

        // let's set up a swap
        self.approveSwap(
            coin,
            0,
            now + 30,
            "for a debit"
        );

        // swap the coin and this token
        coin.acceptSwap(note);

        require(
            coin.owner() == self.previousOwner()
            && coin.previousOwner() == self.owner()
            ,
            "swap in a purchase did not work"
        );

        emit Sold(
            self,                       ///< the item for sell
                self.previousOwner(),              ///< seller
                self.owner(),               ///< buyer
                debitCard.currencyType(),   ///< the currency type
                price,            ///< price
                now,                        ///< when the tx completes
                note              ///< additional note);
        );
        self.reset();
    }

    /***************************** modifiers **************************/

    modifier selfCaller(address addr) {
        require(msg.sender == addr,
            "the caller was not the first argument of the function.");
        _;
    }
//
//    modifier inTransition() {
//        require(inTransfer(),
//            "not in valid ownership transition time window.");
//        _;
//    }
//
//    function inTransfer() internal view returns (bool) {
//        uint256 t = now;
//        if( t >= transferOffer.validFrom && t <= transferOffer.validTill) {
//            return !swapValid(swapOffer);
//        }
//        return false;
//    }
//
//    modifier permittedSender(ISingularWallet target) {
//        address caller = msg.sender;
//        require(
//            caller == address(target)
//            || caller == target.ownerAddress()
//            ,
//            "the sender was not the target wallet or the owner thereof");
//        _;
//    }
//
//    modifier permittedSender2(ITradable target) {
//        address caller = msg.sender;
//        require(
//            caller == address(target)
//            || caller == address(target.owner())
//            || caller == target.owner().ownerAddress()
//            ,
//            "the sender was not the target wallet or the owner thereof");
//        _;
//    }
//
//    modifier notInTx() {
//        require(!isInTx(), "the item is already in a transaction");
//        _;
//    }
//
//    modifier inSell() {
//        require(sellValid(), "this item is not for sell at this moment");
//        _;
//    }
//
//
//    /// only check if this token is involved with any transaction that has dedicated counterparty.
//    function isInTx() internal view returns(bool) {
////        if (sellValid())
////            return true;
////        else
//        if(swapValid(swapOffer))
//            return true;
//        else
//            return inTransfer();
//    }
//
//    function reset() internal {
//        delete sellOffer;
//        delete swapOffer;
//        delete transferOffer;
//        delete theOperator;
//    }
//
//    function sellValid() internal view returns(bool){
//        uint t = now;
//        return
//        sellOffer.erc20 != address(0)
//        && sellOffer.validFrom <= t
//        && sellOffer.validTill >= t
//        && sellOffer.price > 0;
//    }
//
//    function swapValid(SwapOffer _offer) internal view returns(bool){
//        uint t = now;
//        return
//        _offer.target != address(0)
//        && _offer.validFrom <= t
//        && _offer.validTill >= t;
//    }
//
//    modifier inSwap() {
//        require(swapValid(swapOffer), "this item is not in swap mode");
//        require(now > transferOffer.validTill, "this singular is in ownership transition");
//        _;
//    }
//
//    modifier max128Bytes(string s) {
//        require(bytes(s).length <= 128, "the string used more than 128 bytes");
//        _;
//    }

    ////
    function ping() internal {}
}