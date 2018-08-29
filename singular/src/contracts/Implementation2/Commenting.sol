pragma solidity ^0.4.24;

contract Commenting {
    struct Comment {
        address who;
        uint256 at;
        string comment;
    }

    event Commented(
        address who,
        uint256 at,
        string comment
    );

    Comment[] ownerComment; // might be operators;

    function addComment(address who, uint256 when, string _comment) internal {
        ownerComment.push(Comment(who, when, _comment));
        emit Commented(who, when, _comment);
    }

    function allComments() view public returns(string) {
        // TODO: serailize all the comments;
        revert("not implemented");
    }
    /// end of commenting
}