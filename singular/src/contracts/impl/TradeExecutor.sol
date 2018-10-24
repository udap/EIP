pragma solidity ^0.4.24;

import "../ITradable.sol";
import "../ITradeExecutor.sol";


/**

@title the middleman in tradable txs

@author bing ran<bran@udap.io>

*/
contract TradeExecutor is ITradeExecutor {

    /**
    To swap the owners of the two items. Both items must have a swap offer properly
    set up before either one invokes this function.
    */
    function swap(
        ITradable a,
        ITradable b
    )
    external
    {

        ISingularWallet aOwner;
        ITradable aTarget;
        uint256 aVFrom;
        uint256 aVTill;
//        string memory aNote;

        (aOwner, aTarget, aVFrom, aVTill) = a.swapOffer();

        //        // check the reciprocal offer
        ISingularWallet bOwner;
        ITradable bTarget;
        uint256 bVFrom;
        uint256 bVTill;
//        string memory bNote;

        (bOwner, bTarget, bVFrom, bVTill) = b.swapOffer();

        require(bTarget == a, "the other offer was not targeted to me");
        require(aTarget == b, "the self's target was not the target");
        uint t = now;
        require(aVFrom <= t && t <= aVTill, "the current time was not in the valid range for party a");
        require(bVFrom <= t && t <= bVTill, "the current time was not in the valid range for party b");

        // all set.do the swap, assuming this executor is trusted by both parties
        a.swapInOwner(bOwner, "swapped by executor");
        b.swapInOwner(aOwner, "swapped by executor");

        emit Swapped(a, b, now, "swapped by executor");
    }

    /**
    Anyone (? is this too lenient?) calls this function to make a purchase.
    Note: no change is returned if the denomination is > price.
    The debit card MUST been set up with a proper SwapOffer with the denomination and swap target
     */
    function buy(
        ITradable token,     ///<
        IDebit debitCard   ///< the money
    )
    external
    {
//        require(token.matchSaleOfferNow(debitCard), "the debit and the item for sale did not match");

        ISingularWallet aOwner;
        address erc20;
        uint256 price;
        uint256 from;
        uint256 till;
//        string memory note;

        uint t = now;

        (aOwner, erc20, price, from, till) = token.saleOffer();

        require(from <= t && t <= till, "the current time was not in the valid range for this token");

        require(debitCard.denomination() >= price, "not enough fund to make the purchase");
        require(debitCard.currencyType() == erc20, "currency types mismatch");

        ITradable debit = debitCard.toITradable();

        ISingularWallet bOwner;
        ITradable bTarget;
//        string memory bNote;

        (bOwner, bTarget, from, till) = debit.swapOffer();

        require(from <= t && t <= till, "the current time was not in the valid range for this token");

        require(bTarget == token, "the debit was not set to buy the required target");

        // all set.do the swap, assuming this executor is trusted by both parties
        token.swapInOwner(bOwner, "from executor.buy()");
        // if sale price less than debit amount
        if (debitCard.denomination() > price) {
            // return tokens back to
            uint256 change = debitCard.denomination()-price;
            debitCard.withdraw(bOwner, change);
        }
        debit.swapInOwner(aOwner, "from executor.buy()");

        emit Sold(
            token,                       ///< the item for sell
            aOwner,              ///< seller
            bOwner,               ///< buyer
            erc20,   ///< the currency type
            price,            ///< price
            t,                        ///< when the tx completes
            "from executor.buy()"              ///< additional note);
        );
    }

}
