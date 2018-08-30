pragma solidity ^0.4.24;

import "../../node_modules/openzeppelin-solidity/contracts/math/SafeMath.sol";
import "../utils/RLPEncode.sol";
import "../ITransferHistory.sol";

contract TransferHistory is ITransferHistory {


    /// ownership history enumeration
    struct TransferRec {
        ISingularWallet from;
        ISingularWallet to;
        uint256 at;
        string senderNote;
        string receiverNote;
        ISingular singular;
    }


    TransferRec[] internal transferHistory;

    constructor()public{
    }

    function numOfTransfers() view public returns (uint256) {
        return transferHistory.length;
    }
    /**
     * To get a specific transfer record in the format defined by implementation.
     * @param _index the inde of the inquired record. It must in the range of
     * [0, numberOfTransfers())
     */
    function getTransferAt(uint256 _index) view public returns(bytes) {
        // need to serialize a records
        // return transferHistory[index];
        require(_index < transferHistory.length);
        return structSerialize(transferHistory[_index]);
    }

    /**
     * get all the transfer records in a serialized form that is defined by
     * implementation.
     */
    function getTransferHistory() view public returns (bytes) {
        if(transferHistory.length == uint256(0)){
            return hex'c0';
        }
        return arraySerialize(0,transferHistory.length -1);
    }

    function addTransferHistory(ISingular _singular, ISingularWallet _from, ISingularWallet _to, uint256 _at, string _senderNote, string _receiverNote) internal{
        TransferRec memory newOne = TransferRec(_from, _to, _at, _senderNote, _receiverNote, _singular);
        transferHistory.push(newOne);
    }

    function structSerialize(TransferRec storage _input) internal view returns(bytes){
        return abi.encode(_input.from,_input.to,_input.at,_input.senderNote,_input.receiverNote,_input.singular);
    }

    function arraySerialize(uint256 _start, uint256 _end) internal view returns(bytes){
        require(_start<=_end && _end < transferHistory.length);
        bytes[] memory list = new bytes[](_end-_start+1);
        uint256 k = 0;
        for(uint256 i = _start; i <= _end; i ++){ //won't overflow
            bytes memory serialized =structSerialize(transferHistory[i]);
            bytes memory rlped = RLPEncode.encodeBytes(serialized);
            list[k] = rlped;
        }
        bytes memory ret = RLPEncode.encodeList(list);
        return ret;
    }
}
