pragma solidity ^0.4.24;
import "../ISingular.sol";
//import "../ITradable.sol";


/**

packet of some fungible tokens of the same type and of the same owner.

Conceptually it's similar to a mini-wallet containing a single type of cash. The wallet can be used
to buy other tokens; or it can be split to two wallets. Two wallets of the same type and ownership can be
merged together to become a wallet of larger denomination. A wallet can also transfer some of its
denominations to another wallet of the same currency type regardless of the owner.

IDebit makes some fungible tokens a single unit of value that can participate in trading.

@author Bing Ran<bran@udap.io>

*/
contract IDebit is ISingular {


    function currencyType()
    public view
    returns(
        address debitType
    );

    /**
     * the net value of this container.
     */
    function denomination()
    public view
    returns(
        uint256 value           ///< the token value of this packet
    );

    /**
     * To transfer some tokens to another coin of the same ERC20 type.
     * The owner of the coin can be different
     */
    function transfer(
        IDebit another,     ///< the receiving debit account, which must be of the same type
        uint256 amount      ///< how many units of tokens to transfer
    )
    public;

    /**
     * To dump the all the coin value from the argument to this coin container.
     * The owners must be the same and the coin types must be the same.
     */
    function merge(IDebit coin)
    public
    returns(
        uint256 updatedBalance
    );

    /**
     * the create a new ERC20Debit of the same type and allocate some value to it from this
     * coin.
     */
    function split(
        uint256 amount      ///< the value in the new coin
    )
    public
    returns(
        IDebit          ///< the spawned child account
    );

}