pragma solidity ^0.4.24;

import "./utils/SafeMath.sol";
import "./utils/RLPEncode.sol";
import "./ITransferHistory.sol";
import "./SingularBase.sol";
import "./utils/Initialized.sol";

contract TransferHistory is ITransferHistory, Initialized {
    function contractName()
    external
    view
    returns(
        string              ///< the name of the contract class
    ) {
        return "TransferHistory";
    }


    /// ownership history enumeration
    struct TransferRec {
        ISingularWallet from;
        ISingularWallet to;
        uint256 when;
        string senderNote;
        string receiverNote;
    }


    TransferRec[] internal transferHistory;

    constructor()public{
    }

    function init() unconstructed public{

    }

    function numOfTransfers() constructed view public returns (uint256) {
        return transferHistory.length;
    }
    /**
     * To get a specific transfer record in the format defined by implementation.
     * @param _index the inde of the inquired record. It must in the range of
     * [0, numberOfTransfers())
     */
    function getTransferAt(uint256 _index) constructed view public returns(bytes) {
        // need to serialize a records
        // return transferHistory[index];
        require(_index < transferHistory.length);
        return structSerialize(transferHistory[_index]);
    }

    /**
     * get all the transfer records in a serialized form that is defined by
     * implementation.
     */
    function getTransferHistory() constructed view public returns (bytes) {
        if(transferHistory.length == uint256(0)){
            return hex'c0';
        }
        return arraySerialize(0,transferHistory.length -1);
    }

    //=================internal functions===================
    function addTransferHistory(ISingularWallet _from, ISingularWallet _to, uint256 _when, string _senderNote, string _receiverNote) internal{
        TransferRec memory newOne = TransferRec(_from, _to, _when, _senderNote, _receiverNote);
        transferHistory.push(newOne);
    }

    //workaround until AbiEncodingV2 is ready
    function structSerialize(TransferRec storage _input) internal view returns(bytes){
        return abi.encode(_input.from,_input.to,_input.when,_input.senderNote,_input.receiverNote);
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
