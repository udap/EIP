pragma solidity ^0.4.24;

import "./SingularWalletBase.sol";
import "./ISingularWalletAll.sol";

contract SingularWallet is SingularWalletBase, ISingularWalletAll{

    string theName;
    string theSymbol;
    string theDescription;

    constructor() public payable{

    }

    function init (address _walletOwner, address _walletOperator,string _theName, string _theSymbol, string _theDescription) unconstructed() public {
        SingularWalletBase.init(_walletOwner, _walletOperator);
        theName = _theName;
        theSymbol = _theSymbol;
        theDescription = _theDescription;
    }

    function name() external view returns (string){return theName;}
    function symbol() external view returns (string){return theSymbol;}
    function description() external view returns (string){return theDescription;}

}
