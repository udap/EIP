package io.udap.singular;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;

import static io.udap.singular.GanacheIT.*;
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
                GAS_PRICE,
                GAS_LIMIT,
                "alice aliceWallet"
        ).send();

        bobWallet = BasicSingularWallet.deploy(
                web3j,
                BOB,
                GAS_PRICE,
                GAS_LIMIT,
                "Bob's aliceWallet!"
        ).send();

    }

    @Test
    public void testDeploy() throws Exception {
        String contractAddress = aliceWallet.getContractAddress();
        log.info("Wallet deployed to address " + contractAddress);
        assertEquals(aliceWallet.ownerAddress().send(), ALICE.getAddress());
        Tuple2<BigInteger, BigInteger> numOfTokens = aliceWallet.numOfTokens().send();
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
                    new DefaultGasProvider(),
                    "empty aliceWallet!"
            ).send();
            fail("should have thrown an exception");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWalletToken() throws Exception {
        aliceToken = Tradable.deploy(
                web3j,
                ALICE,
                new DefaultGasProvider()
        ).send();

        TransactionReceipt a = aliceToken.init(
                "aliceToken",
                "sym",
                "descr",
                "uri",
                new byte[32],
                "0x00",
                aliceWallet.getContractAddress()
        ).send();

        assertEquals(aliceToken.owner().send(), aliceWallet.getContractAddress());

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
            ).send();
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
        ).send();

        // let's load the tradable with bob credentials
        Tradable t = Tradable.load(aliceToken.getContractAddress(), web3j, BOB, new DefaultGasProvider());

        try {
            // it should revert here because the sender is Bob who does not have ther permission to init
            TransactionReceipt a = t.init(
                    "aliceToken",
                    "sym",
                    "descr",
                    "uri",
                    new byte[32],
                    "0x00",
                    aliceWallet.getContractAddress()
            ).send();
            fail("should never be here");
        } catch (Exception e) {

        }

        // let's load the tradable again with alice credentials
        t = Tradable.load(aliceToken.getContractAddress(), web3j, ALICE, new DefaultGasProvider());

        TransactionReceipt a = t.init(
                "aliceToken",
                "sym",
                "descr",
                "uri",
                new byte[32],
                "0x00",
                aliceWallet.getContractAddress()
        ).send();

        assertEquals(t.owner().send(), aliceWallet.getContractAddress());

    }




}
