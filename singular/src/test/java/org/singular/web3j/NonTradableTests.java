package org.singular.web3j;

import io.udap.web3j.BasicSingularWallet;
import io.udap.web3j.NonTradable;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;
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

    BasicSingularWallet aliceWallet;
    NonTradable nonTrada;

    @Before
    public void setup() throws Exception {
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
                aliceWallet.getContractAddress(),
                aliceWallet.getContractAddress()
        );
    }

    @Test
    public void testInitialization() throws Exception {
        assertEquals( nonTrada.contractName(), "NonTradable");
        assertEquals( ALICE.getAddress(), nonTrada.creator());
        assertEquals( nonTrada.owner(), aliceWallet.getContractAddress());
        assertEquals( nonTrada.tokenType(), aliceWallet.getContractAddress());
        // the metadata part
        assertEquals( nonTrada.name(), ALICE_TOKEN);
        assertEquals( nonTrada.symbol(), PERSON_TOKEN);
        assertEquals( nonTrada.description(), DESCR);
        assertEquals( nonTrada.tokenURI(), URI);

        // ownership interlocked with the aliceWallet
        assertTrue( aliceWallet.owns(nonTrada.getContractAddress()));

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
                    aliceWallet.getContractAddress(),
                    aliceWallet.getContractAddress()
            );
            fail("Alice should not able to init the bob's instance");
        } catch (Exception e) {

        }
    }
}
