package io.udap.singular;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.udap.singular.GanacheIT.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
        ).send();

        nonTrada = NonTradable.deploy(web3j, ALICE, GAS_PROVIDER).send();
        nonTrada.init(
                ALICE_TOKEN,
                PERSON_TOKEN,
                DESCR,
                URI,
                new byte[32],
                aliceWallet.getContractAddress(),
                aliceWallet.getContractAddress()
        ).send();
    }

    @Test
    public void testInitialization() throws Exception {
        assertEquals( nonTrada.contractName().send(), "NonTradable");
        assertEquals( ALICE.getAddress(), nonTrada.creator().send());
        assertEquals( nonTrada.owner().send(), aliceWallet.getContractAddress());
        assertEquals( nonTrada.tokenType().send(), aliceWallet.getContractAddress());
        // the metadata part
        assertEquals( nonTrada.name().send(), ALICE_TOKEN);
        assertEquals( nonTrada.symbol().send(), PERSON_TOKEN);
        assertEquals( nonTrada.description().send(), DESCR);
        assertEquals( nonTrada.tokenURI().send(), URI);

        // ownership interlocked with the aliceWallet
        assertTrue( aliceWallet.owns(nonTrada.getContractAddress()).send());

    }

    @Test
    public void testInitPermission() throws Exception {
        NonTradable nt = NonTradable.deploy(web3j, BOB, GAS_PROVIDER).send();
        try {
            nt.init(
                    ALICE_TOKEN,
                    PERSON_TOKEN,
                    DESCR,
                    URI,
                    new byte[32],
                    aliceWallet.getContractAddress(),
                    aliceWallet.getContractAddress()
            ).send();
            fail();
        } catch (Exception e) {

        }
    }
}
