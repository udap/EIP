package org.singular.web3j;

import io.udap.web3j.BasicSingularWallet;
import io.udap.web3j.Tradable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.abi.datatypes.Address;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.singular.web3j.GanacheIT.*;

/**
 * Created by bran on 2018/10/14.
 */
public class BasicSingularWalletTests {
    private static final Logger log = LoggerFactory.getLogger(BasicSingularWalletTests.class);

    static BasicSingularWallet aliceWallet;
    static BasicSingularWallet bobWallet;
    Tradable aliceToken;

    @BeforeAll
    public static void setup() throws Exception {
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
    @DisplayName("verify the creator address")
    public void testDeploy() throws Exception {
        String contractAddress = aliceWallet.getContractAddress();
        log.info("Wallet deployed to address " + contractAddress);
        assertEquals(ALICE.getAddress(), aliceWallet.ownerAddress().toString());
        Tuple2<BigInteger, BigInteger> numOfTokens = aliceWallet.numOfTokens();
//        assertEquals(0, numOfTokens.getValue1().intValue());
//        assertEquals(0, numOfTokens.getValue2().intValue());
    }

    @Test
    @DisplayName("Cannot deploy from empty account")
    public void testDeployFromInvalidAcct() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            BasicSingularWallet wallet = BasicSingularWallet.deploy(
                    web3j,
                    EMPTY,
                    GAS_PROVIDER,
                    "empty aliceWallet!"
            );
        });
        assertTrue(exception.getMessage().contains("sender doesn't have enough funds"));
    }

    @Test
    public void testWalletToken() throws Exception {
        aliceToken = Tradable.deploy(
                web3j,
                ALICE,
                GAS_PROVIDER
        );

        Address aliceAddr = aliceWallet.asAddress();
        TransactionReceipt a = aliceToken.init(
                "aliceToken",
                "sym",
                "descr",
                "uri",
                new byte[32],
                aliceAddr,
                aliceAddr
        );

        assertEquals(aliceAddr, aliceToken.owner());

        // it should revert
        assertThrows(RuntimeException.class, () -> {

            aliceToken.init(
                    "aliceToken",
                    "sym",
                    "descr",
                    "uri",
                    new byte[32],
                    aliceAddr,
                    aliceAddr
            );
        });


    }

    @Test
    public void testWalletTokenInitWithWrongPerson() throws Exception {
        aliceToken = Tradable.deploy(
                web3j,
                ALICE,
                new DefaultGasProvider()
        );

        Address aliceWalletAddr = aliceWallet.asAddress();

        assertThrows(RuntimeException.class, () -> {
            // let's load the tradable with bob credentials
            // it should revert here because the sender is Bob who does not have ther permission to init
            TransactionReceipt a = aliceToken.loadFor(BOB).init(
                    "aliceToken",
                    "sym",
                    "descr",
                    "uri",
                    new byte[32],
                    aliceWalletAddr,
                    aliceWalletAddr
            );
        });

        // let's load the tradable again with alice credentials
        TransactionReceipt a = aliceToken.loadFor(ALICE).init(
                "aliceToken",
                "sym",
                "descr",
                "uri",
                new byte[32],
                aliceWalletAddr,
                aliceWalletAddr
        );
    }
}
