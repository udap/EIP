pragma solidity ^0.4.24;

import "./ISingularWallet.sol";
import "./ISingular.sol";
//import "./ITradable.sol";
import "./Transferable.sol";
import "./debit/IDebit.sol";
import "./debit/ERC20Debit.sol";


/**

@author bing ran<bran@udap.io>

XXX  should really inherit from ITradable. But it would cause Error: Definition of base has to
precede definition of derived contract.
*/
contract Tradable is Transferable {

    struct SellOffer {
        address erc20;          ///< the currency type
        uint256 price;          ///< price
        uint256 validFrom;      ///< when an offer is valid from
        uint256 validTill;      ///< when the offer expires
        string note;             ///< additional note
    }

    // ? we might use a predicator to set the swap target, to make it compatible for sell and swap
    struct SwapOffer {
        Tradable target;          ///< what to swap
        uint256 validFrom;      ///< when an offer is valid from
        uint256 validTill;      ///< when the offer expires
        //        string note;             ///< additional note
    }

    SellOffer public sellOffer;
    SwapOffer public swapOffer;


    /**
     */
    event SwapApproved(
        Tradable indexed from, ///< the item for swap
        Tradable indexed to,  ///< the desired item
        uint256 validFrom,      ///< when an offer is valid from
        uint256 validTill,      ///< when the offer expires
        string note             ///< additional note
    );

    /**
     */
    event Swapped(
        Tradable indexed from, ///< the item for swap
        Tradable indexed to,  ///< the desired item
        uint when,              ///< when this happened
        string note             ///< additional note
    );

    /**
     * When the current owner has approved someone else as the next owner, subject
     * to acceptance or rejection.
     */
    event SellOfferApproved(
        Tradable indexed item, ///< the item for sell
        address indexed erc20,  ///< the currency type
        uint256 price,          ///< price
        uint256 validFrom,      ///< when an offer is valid from
        uint256 validTill,      ///< when the offer expires
        string note             ///< additional note
    );

    /**
     * the ownership has been successfully transferred from A to B.
     */
    event Sold(
        Tradable indexed item, ///< the item for sell
        ISingularWallet indexed seller, ///< seller
        ISingularWallet indexed buyer,  ///< buyer
        address erc20,  ///< the currency type
        uint256 price,          ///< price
        uint256 when,           ///< when the tx completes
        string note             ///< additional note
    );



    constructor(
        string _name,
        string _symbol,
        string _descr,
        string _tokenURI,
        bytes32 _tokenURIHash,
        address _tokenType,
        ISingularWallet _wallet
    )
    Transferable(_name, _symbol, _descr, _tokenURI, _tokenURIHash, _tokenType, _wallet)
    public
    {
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
    notInTransition
    external
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
        Tradable target,
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
        Tradable offered
    )
    public
    inSwap
    {
//        approveSwap(offered, now, now + 10 seconds, "");
        // simply change ownership?
//        SwapOffer offer = offered.swapOffer();
        Tradable target;
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
        offered.changeOwnerToMyPreviousOwner();

        require(offered.owner() == currentOwner, "swap() did not change the ownership over to me");
        reset();

        emit Swapped(offered, this, now, note);
    }

    function rejectSwap(

    )
    public {
        address sender = msg.sender;
        require(sender == address(swapOffer.target.owner()), "only the target owner can reject the swap offer");
        reset();
    }


    function changeOwnerToMyPreviousOwner()
    public
    inSwap
    {
        Tradable target = swapOffer.target;
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
        The debit card MUST been set up with a proper SwapOffer
     */
    function buy(
        ERC20Debit debitcard   ///< the money
    )
    external
    inSell
    {
        require(debitcard.denomination() >= sellOffer.price, "not enough fund to make the purchase");
        require(debitcard.currencyType() == sellOffer.erc20, "currency types mismatch");

        // shall re require the sender be the debit owner?
        require(msg.sender == address(debitcard.owner()), "ownership does not match. ");

        Tradable target;
        uint256 vFrom;
        uint256 vTill;

        (target, vFrom, vTill) = debitcard.swapOffer();


        require(target == this, "the cash is not targeted to me");
        require(swapValid(SwapOffer(target, vFrom, vTill)), "the other offer is not valid, expired?");

        ownerPrevious = currentOwner;
        currentOwner = debitcard.owner();

        ownerPrevious.sent(this, "bought out");
        currentOwner.received(this, "bought in");

        debitcard.changeOwnerToMyPreviousOwner();

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

    modifier notInTx() {
        require(!isInTx(), "the item is already in a transaction");
        _;
    }

    modifier inSell() {
        require(sellValid(), "this item is not for sell at this moment");
        _;
    }


    function isInTx() view internal returns(bool) {
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
        Transferable.reset();
    }

    function sellValid() view internal returns(bool){
        uint t = now;
        return
        sellOffer.erc20 != address(0)
        && sellOffer.validFrom <= t
        && sellOffer.validTill >= t
        && sellOffer.price > 0;
    }

    function swapValid(SwapOffer _offer) view internal returns(bool){
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