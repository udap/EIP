package org.singular.web3j;

import io.udap.web3j.BasicSingularWallet;
import io.udap.web3j.Tradable;
import io.udap.web3j.TradeExecutor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.abi.datatypes.Address;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by bran on 2018/10/14.
 */
public class TradableTests extends GanacheIT {
    private static final Logger log = LoggerFactory.getLogger(TradableTests.class);
    public static final String ALICE_TOKEN = "alice aliceToken";
    public static final String PERSON_TOKEN = "person_token";
    public static final String DESCR = "descr";
    public static final String URI = "uri";

    static BasicSingularWallet aliceWallet;
    static BasicSingularWallet bobWallet;
    static Tradable aliceToken;
    static Tradable bobToken;

    static TradeExecutor tradeExecutor;
    @BeforeAll
    public static void setup() throws Exception {
        aliceWallet = BasicSingularWallet.deploy(
                web3j,
                ALICE,
                GAS_PROVIDER,
                "aliceWallet"
        );

        bobWallet = BasicSingularWallet.deploy(
                web3j,
                BOB,
                GAS_PROVIDER,
                "bob's Wallet"
        );

        tradeExecutor = TradeExecutor.deploy(web3j, ALICE, GAS_PROVIDER);

        aliceToken = Tradable.deploy(web3j, ALICE, GAS_PROVIDER2);
        aliceToken.init(
                ALICE_TOKEN,
                PERSON_TOKEN,
                DESCR,
                URI,
                new byte[32],
                aliceWallet.asAddress(),
                aliceWallet.asAddress()
        );
        
        aliceToken.setExecutor(tradeExecutor.asAddress());

        bobToken = Tradable.deploy(web3j, BOB, GAS_PROVIDER2);
        bobToken.init(
                ALICE_TOKEN,
                PERSON_TOKEN,
                DESCR,
                URI,
                new byte[32],
                bobWallet.asAddress(),
                bobWallet.asAddress()
        );

        bobToken.setExecutor(tradeExecutor.asAddress());

    }

    @Test
    @DisplayName("should properly set up in the constructor")
    public void testInitialization() throws Exception {
        assertEquals( "Tradable", aliceToken.contractName());
        assertEquals( ALICE.getAddress(), aliceToken.creator().toString());
        assertEquals( aliceWallet.asAddress(), aliceToken.owner());
        assertEquals( aliceWallet.asAddress(), aliceToken.tokenType());
        // the metadata part
        assertEquals( ALICE_TOKEN, aliceToken.name());
        assertEquals( PERSON_TOKEN, aliceToken.symbol());
        assertEquals( DESCR, aliceToken.description());
        assertEquals( URI, aliceToken.tokenURI());
        assertArrayEquals(new byte[32], aliceToken.tokenURIDigest());

        // ownership interlocked with the aliceWallet
        assertTrue( aliceWallet.owns(aliceToken.asAddress()));

        assertEquals(aliceWallet.getContractAddress(), aliceToken.owner().toString());
        assertEquals(new Address(bigInt(0)), aliceToken.previousOwner());
        assertEquals(new Address(bigInt(0)), aliceToken.nextOwner());
        assertTrue(aliceWallet.owns(aliceToken.asAddress()));
    }

    @Test
    @DisplayName("approve from owner")
    public void approve () throws Exception {
        long t = System.currentTimeMillis() / 1000;
        BigInteger validFrom = bigInt(t);
        BigInteger validTill = bigInt(t + 10);
        TransactionReceipt tx = aliceToken.approveReceiver(
                bobWallet.asAddress(),
                validFrom,
                validTill,
                "for fun"
        );

        List<Tradable.ReceiverApprovedEventResponse> receiverApprovedEvents = aliceToken.getReceiverApprovedEvents(tx);
        assertEquals(1, receiverApprovedEvents.size());
        Tradable.ReceiverApprovedEventResponse ev = receiverApprovedEvents.get(0);
        assertEquals(aliceWallet.asAddress(), ev.from);
        assertEquals(bobWallet.asAddress(), ev.to);
        assertEquals(validFrom, ev.validFrom);
        assertEquals(validTill, ev.validTill);
    }

    @Test
    @DisplayName("shoudl not approve from unauthorized")
    public void cannotApprove() throws Exception {
        long t = System.currentTimeMillis() / 1000;
        BigInteger validFrom = bigInt(t);
        BigInteger validTill = bigInt(t + 10);
        assertThrows(Exception.class, () -> {
            aliceToken.from(BOB).approveReceiver(
                    bobWallet.asAddress(),
                    validFrom,
                    validTill,
                    "for fun"
            );
        });

    }
    
    @Test
    @DisplayName("do a swapping")
    public void testSwapping() throws Exception {
        long t = System.currentTimeMillis() / 1000;
        BigInteger validFrom = bigInt(t - 1);
        BigInteger validTill = bigInt(t + 10);
        TransactionReceipt tx = aliceToken.approveSwap(
                bobToken.asAddress(),
                validFrom,
                validTill,
                "cool"
        );

        Tuple4<Address, Address, BigInteger, BigInteger> swapOffer = aliceToken.swapOffer();
        // logit(swapOffer);
        assertEquals(swapOffer.getValue2(), bobToken.asAddress());
        assertEquals(swapOffer.getValue3(), validFrom);
        assertEquals(swapOffer.getValue4(), validTill);
        // assertEquals(swapOffer.note, "cool");
        // let's do the swap
        // 1. propose a reverse swap
        tx = (bobToken.approveSwap(
                aliceToken.asAddress(),
                validFrom,
                validTill,
                "why not"
            ));
        swapOffer = (bobToken.swapOffer());
        // logit(swapOffer, "swp offer on bob");
        assertEquals(swapOffer.getValue2(), aliceToken.asAddress());
        assertEquals(swapOffer.getValue3(), validFrom);
        assertEquals(swapOffer.getValue4(), validTill);
        // assertEquals(swapOffer.note, "why not");
        // 3. let's do it
        tx = ( tradeExecutor.from(ALICE).swap(aliceToken.asAddress(), bobToken.asAddress()));

        assertEquals(bobWallet.asAddress(), (aliceToken.owner()), "aliceToken owner was not swapped to bob");
        assertEquals(aliceWallet.asAddress(), (bobToken.owner()), "bobToken owner was not swapped to alice");

    }

}
