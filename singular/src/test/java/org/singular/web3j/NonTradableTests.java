package org.singular.web3j;

import io.udap.web3j.BasicSingularWallet;
import io.udap.web3j.NonTradable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.singular.web3j.GanacheIT.*;

/**
 * Created by bran on 2018/10/14.
 */
public class NonTradableTests {
    private static final Logger log = LoggerFactory.getLogger(NonTradableTests.class);
    public static final String ALICE_TOKEN = "alice token";
    public static final String PERSON_TOKEN = "person_token";
    public static final String DESCR = "descr";
    public static final String URI = "uri";

    static BasicSingularWallet aliceWallet;
    static NonTradable nonTrada;

    @BeforeAll
    public static void setup() throws Exception {
        aliceWallet = BasicSingularWallet.deploy(
                web3j,
                ALICE,
                GAS_PROVIDER,
                "alice aliceWallet"
        );

        nonTrada = NonTradable.deploy(web3j, ALICE, GAS_PROVIDER);
        nonTrada.init(
                ALICE_TOKEN,
                PERSON_TOKEN,
                DESCR,
                URI,
                new byte[32],
                aliceWallet.asAddress(),
                aliceWallet.asAddress()
        );
    }

    @Test
    public void testInitialization() throws Exception {
        assertEquals( "NonTradable", nonTrada.contractName());
        assertEquals( ALICE.getAddress(), nonTrada.creator().toString());
        assertEquals( aliceWallet.asAddress(), nonTrada.owner());
        assertEquals( aliceWallet.asAddress(), nonTrada.tokenType());
        // the metadata part
        assertEquals( ALICE_TOKEN, nonTrada.name());
        assertEquals( PERSON_TOKEN, nonTrada.symbol());
        assertEquals( DESCR, nonTrada.description());
        assertEquals( URI, nonTrada.tokenURI());

        // ownership interlocked with the aliceWallet
        assertTrue( aliceWallet.owns(nonTrada.asAddress()));

    }

    @Test
    public void testInitPermission() throws Exception {
        NonTradable nt = NonTradable.deploy(web3j, BOB, GAS_PROVIDER);
        try {
            nt.init(
                    ALICE_TOKEN,
                    PERSON_TOKEN,
                    DESCR,
                    URI,
                    new byte[32],
                    aliceWallet.asAddress(),
                    aliceWallet.asAddress()
            );
            fail("Alice should not able to init the bob's instance");
        } catch (Exception e) {

        }
    }
}
