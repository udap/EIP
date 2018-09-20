pragma solidity ^0.4.24;

import "./ICommenting.sol";
import "./utils/RLPEncode.sol";
import "./utils/Initialized.sol";
import "./SingularBase.sol";

contract Comment is IComment, Initialized, SingularBase{
    function contractName()
    external
    view
    returns(
        string              ///< the name of the contract class
    ) {
        return "Comment";
    }

    constructor () public{


    }

    struct CommentRec {
        address who;
        uint256 at;
        string comment;
    }

    event Commented(
        address who,
        uint256 at,
        string comment
    );

    CommentRec[] ownerComment; // might be operators;

    function init() unconstructed public{

    }

    function addComment(address _who, uint256 _when, string _comment) ownerOnly constructed external {
        ownerComment.push(CommentRec(_who, _when, _comment));
        emit Commented(_who, _when, _comment);
    }

    function numOfComment() constructed external view returns(uint256) {
        return ownerComment.length;
    }

    function commentAt(uint256 _index) constructed external view returns(bytes){
        require(_index < ownerComment.length);
        return structSerialize(ownerComment[_index]);
    }

    function allComments() constructed view external returns(bytes) {
        if(ownerComment.length == uint256(0)){
            return hex'c0';
        }
        return arraySerialize(0,ownerComment.length-1);
    }


    //=================internal functions===================
    function structSerialize(CommentRec storage _input) internal view returns(bytes){
        return abi.encode(_input.who,_input.at,_input.comment);
    }

    function arraySerialize(uint256 _start, uint256 _end) internal view returns(bytes){
        require(_start<=_end && _end < ownerComment.length);
        bytes[] memory list = new bytes[](_end-_start+1);
        uint256 k = 0;
        for(uint256 i = _start; i <= _end; i ++){ //won't overflow
            bytes memory serialized =structSerialize(ownerComment[i]);
            bytes memory rlped = RLPEncode.encodeBytes(serialized);
            list[k] = rlped;
        }
        bytes memory ret = RLPEncode.encodeList(list);
        return ret;
    }
}
