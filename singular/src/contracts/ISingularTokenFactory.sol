pragma solidity ^0.4.20;
import "./ISingularMeta.sol";
import "./ISingular.sol";

interface ISingularTokenFactory {
    function mint(
        ISingularMeta meta      ///< the information about the new token
    )
    external
    returns(
        ISingular token         ///< the new token contract
    );
}
