pragma solidity ^0.4.24;

import "../ISingularWallet.sol";
import "../ISingular.sol";

contract AltTransferHistory {

    struct TransferRec {
        ISingularWallet from;
        ISingularWallet to;
        uint256 at;
        string senderNote;
        string receiverNote;
        ISingular token;
    }


    TransferRec[] internal transferHistory;

    /// ownership history enumeration

    /**
     * To get the number of ownership changes of this token.
     * @return the number of ownership records. The first record is the token genesis
     * record.
     */
    function numOfTransfers() view external returns (uint256) {
        return transferHistory.length;
    }
    /**
     * To get a specific transfer record in the format defined by implementation.
     * @param index the inde of the inquired record. It must in the range of
     * [0, numberOfTransfers())
     */
    function getTransferAt(uint256 index) view external returns(string) {
        // need to serialize a records
        // return transferHistory[index];
        revert("not implemented");
    }

    /**
     * get all the transfer records in a serialized form that is defined by
     * implementation.
     */
    function getTransferHistory() view external returns (string) {
        // TODO: serialize the transferHistory
        revert("not implemented");
    }
}
