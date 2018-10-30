package org.singular.web3j;

import io.udap.web3j.BasicSingularWallet;
import io.udap.web3j.Tradable;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;

import static org.singular.web3j.GanacheIT.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by bran on 2018/10/14.
 */
public class BasicSingularWalletTests {
    private static final Logger log = LoggerFactory.getLogger(BasicSingularWalletTests.class);

    BasicSingularWallet aliceWallet;
    BasicSingularWallet bobWallet;
    Tradable aliceToken;

    @Before
    public void setup() throws Exception {
        aliceWallet = BasicSingularWallet.deploy(
                web3j,
                ALICE,
                GAS_PROVIDER,
                "alice aliceWallet"
        );

        bobWallet = BasicSingularWallet.deploy(
                web3j,
                BOB,
                GAS_PROVIDER,
                "Bob's aliceWallet!"
        );

    }

    @Test
    public void testDeploy() throws Exception {
        String contractAddress = aliceWallet.getContractAddress();
        log.info("Wallet deployed to address " + contractAddress);
        assertEquals(aliceWallet.ownerAddress(), ALICE.getAddress());
        Tuple2<BigInteger, BigInteger> numOfTokens = aliceWallet.numOfTokens();
        assertEquals(numOfTokens.getValue1().intValue(), 0);
        assertEquals(numOfTokens.getValue2().intValue(), 0);
    }

    @Test
    public void testDeployFromInvalidAcct() {
        BasicSingularWallet wallet = null;
        try {
            wallet = BasicSingularWallet.deploy(
                    web3j,
                    EMPTY,
                    GAS_PROVIDER,
                    "empty aliceWallet!"
            );
            fail("should have thrown an exception due to lack of token in the empty account");
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    @Test
    public void testWalletToken() throws Exception {
        aliceToken = Tradable.deploy(
                web3j,
                ALICE,
                new DefaultGasProvider()
        );

        TransactionReceipt a = aliceToken.init(
                "aliceToken",
                "sym",
                "descr",
                "uri",
                new byte[32],
                "0x00",
                aliceWallet.getContractAddress()
        );

        assertEquals(aliceToken.owner(), aliceWallet.getContractAddress());

        // it should revert
        try {
            a = aliceToken.init(
                    "aliceToken",
                    "sym",
                    "descr",
                    "uri",
                    new byte[32],
                    "0x00",
                    aliceWallet.getContractAddress()
            );
            fail("should have thrown an exception");
        } catch (Exception e) {

        }


    }

    @Test
    public void testWalletTokenInitWithWrongPerson() throws Exception {
        aliceToken = Tradable.deploy(
                web3j,
                ALICE,
                new DefaultGasProvider()
        );


        try {
            // let's load the tradable with bob credentials
            // it should revert here because the sender is Bob who does not have ther permission to init
            TransactionReceipt a = aliceToken.loadFor(BOB).init(
                    "aliceToken",
                    "sym",
                    "descr",
                    "uri",
                    new byte[32],
                    "0x00",
                    aliceWallet.getContractAddress()
            );
            fail("should never be here");
        } catch (Exception e) {

        }

        // let's load the tradable again with alice credentials
        TransactionReceipt a = aliceToken.loadFor(ALICE).init(
                "aliceToken",
                "sym",
                "descr",
                "uri",
                new byte[32],
                "0x00",
                aliceWallet.getContractAddress()
        );

    }
}
