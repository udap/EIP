package io.udap.singular.ethj;

import io.udap.web3j.BasicSingularWallet;
import io.udap.web3j.Tradable;
import org.bouncycastle.util.encoders.Hex;
import org.ethereum.crypto.ECKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bran on 2018/10/14.
 */
public class BasicSingularWalletTests {
    private static final Logger log = LoggerFactory.getLogger(BasicSingularWalletTests.class);

    BasicSingularWallet aliceWallet;
    BasicSingularWallet bobWallet;
    Tradable aliceToken;
    ECKey alice = ECKey.fromPrivate(Hex.decode("4ec771c31cac8c0dba77a69e503765701d3c2bb62435888d4ffa38fed60c445c"));
    ECKey bob = ECKey.fromPrivate(Hex.decode("8ec771c31cac8c0dba77a69e503765701d3c2bb62435888d4ffa38fed60c445c"));

//    @Before
//    public void setup() throws Exception {
////        byte[] address = key.getAddress();
////        System.out.println("address: " + Numeric.toHexString(address));
//
//        Echo.chain.sendEther(alice.getAddress(), new BigInteger("10000000000000000000"));
//        Echo.chain.sendEther(bob.getAddress(), new BigInteger("10000000000000000000"));
//
//        BasicSingularWallet.setSender(alice);
//        aliceWallet = new BasicSingularWallet(
//                "alice aliceWallet"
//        );
//
//        BasicSingularWallet.setSender(bob);
//        bobWallet = new BasicSingularWallet(
//                "Bob's aliceWallet!"
//        );
//
//    }
//
//    @Test
//    public void testDeploy() throws Exception {
//        byte[] contractAddress;
//        log.info("Wallet deployed to address " + aliceWallet.contract.getAddress());
//        assertTrue(Arrays.areEqual(aliceWallet.ownerAddress(), alice.getAddress()));
//        Object[] numOfTokens = aliceWallet.numOfTokens();
//        assertEquals(((BigInteger)numOfTokens[0]).intValue(), 0);
//        assertEquals(((BigInteger)numOfTokens[1]).intValue(), 0);
//        contractAddress = bobWallet.contract.getAddress();
//        log.info("Wallet deployed to address " + bobWallet.contract.getAddress());
//        assertTrue(Arrays.areEqual(aliceWallet.ownerAddress(), alice.getAddress()));
//
//    }
//

//    @Test
//    public void testDeployFromInvalidAcct() {
//        BasicSingularWallet wallet = null;
//        try {
//            wallet = BasicSingularWallet.deploy(
//                    web3j,
//                    EMPTY,
//                    GAS_PROVIDER,
//                    "empty aliceWallet!"
//            ).send();
//            fail("should have thrown an exception");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void testWalletToken() throws Exception {
//        aliceToken = new Tradable();
//
//        SolidityCallResult a = aliceToken.init(
//                "aliceToken",
//                "sym",
//                "descr",
//                "uri",
//                new byte[32],
//                new byte[32],
//                aliceWallet
//        );
//
//        assertEquals(aliceToken.owner(), aliceWallet.getContractAddress());
//
//        // it should revert
//        try {
//            a = aliceToken.init(
//                    "aliceToken",
//                    "sym",
//                    "descr",
//                    "uri",
//                    new byte[32],
//                    "0x00",
//                    aliceWallet.getContractAddress()
//            ).send();
//            fail("should have thrown an exception");
//        } catch (Exception e) {
//
//        }
//
//
//    }

//
//    @Test
//    public void testWalletTokenInitWithWrongPerson() throws Exception {
//        aliceToken = Tradable.deploy(
//                web3j,
//                ALICE,
//                new DefaultGasProvider()
//        ).send();
//
//        // let's load the tradable with bob credentials
//        Tradable t = aliceToken.cloneFor(BOB);
////        Tradable t = Tradable.load(aliceToken.getContractAddress(), web3j, BOB, new DefaultGasProvider());
//
//        try {
//            // it should revert here because the sender is Bob who does not have ther permission to init
//            TransactionReceipt a = t.init(
//                    "aliceToken",
//                    "sym",
//                    "descr",
//                    "uri",
//                    new byte[32],
//                    "0x00",
//                    aliceWallet.getContractAddress()
//            ).send();
//            fail("should never be here");
//        } catch (Exception e) {
//
//        }
//
//        // let's load the tradable again with alice credentials
//        t = Tradable.load(aliceToken.getContractAddress(), web3j, ALICE, new DefaultGasProvider());
//
//        TransactionReceipt a = t.init(
//                "aliceToken",
//                "sym",
//                "descr",
//                "uri",
//                new byte[32],
//                "0x00",
//                aliceWallet.getContractAddress()
//        ).send();
//
//        assertEquals(t.owner().send(), aliceWallet.getContractAddress());
//
//    }
//



}
