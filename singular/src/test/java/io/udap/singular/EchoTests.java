package io.udap.singular;

import io.udap.web3j.Echo;
import org.bouncycastle.util.encoders.Hex;
import org.ethereum.crypto.ECKey;
import org.ethereum.util.blockchain.SolidityCallResult;
import org.junit.Test;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.utils.Numeric;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EchoTests {

    public static final String STARTER_FROM_HERE = "starter from here";
    public static final String OOPS = "oops";
    Echo echo = new Echo(STARTER_FROM_HERE);


    @Test
    public void testGreeter(){
        assertEquals(STARTER_FROM_HERE, echo.greet());
        echo.newGreeting(OOPS);
        assertEquals(OOPS, echo.greet());
    }

    @Test
    public void testLogging() throws Exception {
        TransactionReceipt log = echo.log().send();
        assertNotNull(log);
        System.out.println(log.toString());
    }

    @Test
    public void testChangeSender(){
        SolidityCallResult log = echo.log();
        ECKey key = ECKey.fromPrivate(Hex.decode("4ec771c31cac8c0dba77a69e503765701d3c2bb62435888d4ffa38fed60c445c"));
//        ((StandaloneBlockchain)Echo.getChain()).withAccountBalance(
//                key.getAddress(),
//                new BigInteger("100000000000000000000000000")
//        );

        byte[] address = key.getAddress();
        System.out.println("address: " + Numeric.toHexString(address));
        // the new account does not have money yet
        Echo.chain.sendEther(address, new BigInteger("10000000000000000000"));

        SolidityCallResult rec = echo.from(key).log();
        System.out.println(rec.getReceipt().toString());
    }

}
