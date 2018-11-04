package org.web3j.codegen;

import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint160;
import org.web3j.tx.Contract;
import org.web3j.utils.Numeric;

import java.math.BigInteger;

public class MyAddress implements Type<String> {

    public static final String TYPE_NAME = "address";
    public static final int LENGTH = 160; // bits
    public static final int LENGTH_IN_HEX = LENGTH >> 2;
    public static final MyAddress DEFAULT = new MyAddress(BigInteger.ZERO);

    private final Uint160 value;

    public MyAddress(Uint160 value) {
        this.value = value;
    }

    public MyAddress(BigInteger value) {
        this(new Uint160(value));
    }

    public MyAddress(Object o) {
        if (o instanceof Contract)
            this.value = new Uint160(Numeric.toBigInt(((Contract)o).getContractAddress()));
        else if (o instanceof Uint160)
            this.value = (Uint160)o;
        else if (o instanceof BigInteger)
            this.value = new Uint160((BigInteger)o);
        else if (o instanceof String)
            this.value = new Uint160(Numeric.toBigInt((String)o));
        else
            throw new IllegalArgumentException("cannot convert to MyAddress: " + o);
    }

    public MyAddress(String hexValue) {
        this(Numeric.toBigInt(hexValue));
    }

    public Uint160 toUint160() {
        return value;
    }

    @Override
    public String getTypeAsString() {
        return TYPE_NAME;
    }

    @Override
    public String toString() {
        return Numeric.toHexStringWithPrefixZeroPadded(
                value.getValue(), LENGTH_IN_HEX);
    }

    @Override
    public String getValue() {
        return toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MyAddress address = (MyAddress) o;

        return value != null ? value.equals(address.value) : address.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}

