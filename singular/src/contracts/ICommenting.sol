pragma solidity ^0.4.24;

interface IComment {
    event Commented(
        address who,
        uint256 at,
        string comment
    );

    function addComment(address _who, uint256 _when, string _comment) external;

    function numOfComment() external view returns(uint256);

    function commentAt(uint256 _index) external view returns(bytes);

    function allComments() external view returns(bytes);
    /// end of commenting
}