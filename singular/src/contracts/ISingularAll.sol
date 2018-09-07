pragma solidity ^0.4.24;
import "./ISingular.sol";
import "./ICommenting.sol";
import "./ITransferHistory.sol";
import "./ISingularMeta.sol";

contract ISingularAll is ISingular, IComment, ITransferHistory, ISingularMeta{
}
