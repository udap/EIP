pragma solidity ^0.4.24;

contract Logger {
    bool private enabled = true;
    event LogInt(string marker, uint value);
    event LogAddress(string marker, address value);
    event LogBytes32(string marker, bytes32 value);
    event LogString(string marker, string value);
    event LogBool(string marker, bool value);

    function setEnable(bool en) public {enabled = en;}

    function logInt(string marker, uint value) public
    {
        if (enabled)
            emit LogInt(marker,value);
    }

    function logAddress(string marker, address value) public
    {
        if (enabled)
            emit LogAddress(marker,value);
    }

    function logBytes32(string marker, bytes32 value) public
    {
        if (enabled)
            emit LogBytes32(marker,value);
    }

    function logString(string marker, string value) public
    {
        if (enabled)
            emit LogString(marker,value);
    }

    function logBool(string marker, bool value) public
    {
        if (enabled)
            emit LogBool(marker,value);
    }
}