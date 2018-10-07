pragma solidity ^0.4.24;

import "./Tradable.sol";

/**
@title a transferable collection of singulars, which may or may not belong to the same owner.
*/
contract SingularCollection is Tradable {
    ISingular[] internal tokens;

    function add(
        ISingular item
    )
    public
    returns(
        bool normal
    )
    {
        require(item.isEffectiveOwner(msg.sender), "the msg.sender was not the proper owner of the item");
        // now that the item owner authorize to transfer the ownership to this collection,
        // assume the owner has set this collection as the operator of the item
        for (uint i = 0; i < tokens.length; i++) {
            if (item == tokens[i]) {
                //revert("duplicated when being inserted to token set in the wallet.");
                // already in the collection.
                return false;
            }
        }
//                      assetTimestamp = now;
        tokens.push(item);
        return true;
    }

    function remove(
        ISingular item
    )
    public
    ownerOnly
    returns(
        bool normal
    )
    {
        uint length = tokens.length;
        for (uint i = 0; i < length; i++) {
            if (item == tokens[i]) {
                if (i < length - 1){
                // use the last one to overwrite the current position totalTokens--;
                    tokens[i] = tokens[length - 1];
                }
                tokens.length = length - 1;
                return true;
            }
        }
        return false;
    }

    function size()
    public
    view
    returns (
        uint _size
    ) {
        return tokens.length;
    }

    function tokenAt(
        uint idx
    )
    public
    view
    returns (
        ISingular item
    ) {
        require(idx >= 0 && idx < tokens.length, "index was out of range");
        return tokens[idx];
    }
}