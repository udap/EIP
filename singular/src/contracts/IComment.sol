pragma solidity ^0.4.24;

interface IComment {
    event Commented(
        address who,
        uint256 at,
        string comment
    );

    function addComment(address _who, uint256 _when, string _comment) public;

    function numOfComment() public view returns(uint256);

    function commentAt(uint256 _index) public view returns(bytes);

    function allComments() public view returns(bytes);
    /// end of commenting
}