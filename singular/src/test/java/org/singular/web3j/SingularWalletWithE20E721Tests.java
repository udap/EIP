package org.singular.web3j;

import io.udap.web3j.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.web3j.abi.datatypes.Address;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tuples.generated.Tuple5;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class SingularWalletWithE20E721Tests extends GanacheIT {

    static SampleERC20 erc20;
    static SingularWalletWithE20E721 aliceWallet;
    static SingularWalletWithE20E721 bobWallet;

    @BeforeAll
    public static void canBeDeployed() throws Exception {
        erc20 = SampleERC20.deploy(web3j, ALICE, GAS_PROVIDER);
        assertEquals(42, erc20.at().length());

        aliceWallet= SingularWalletWithE20E721.deploy(web3j, ALICE, GAS_PROVIDER, "Alice aliceWallet");
        assertEquals(42, aliceWallet.at().length());
//        System.out.println("gas:" + aliceWallet.getTransactionReceipt().get().getGasUsed());

        assertEquals(ALICE.getAddress(), aliceWallet.ownerAddress().toString());

        bobWallet= SingularWalletWithE20E721.deploy(web3j, BOB, GAS_PROVIDER, "bobWallet");

    }

    @Nested
    class Debits {
        @Test
        public void canActivateDebitCard() throws Exception {
            BigInteger INIT_AMOUNT = bigInt(2000);
            TransactionReceipt re = erc20.transfer(aliceWallet.asAddress(), INIT_AMOUNT);
            assertEquals("0x1", re.getStatus());

            // this call requires big gas
            ERC20Debit erc20Debit = ERC20Debit.deploy(web3j, ALICE, bigInt(20_000_000_000L), bigInt(5_000_000));
            assertTrue(erc20Debit.getTransactionReceipt().get().getGasUsed().intValue() < 5_0000_000);

            BigInteger AMOUNT = bigInt(1000);
            TransactionReceipt tx = aliceWallet.activateE20Debit(
                    "alice debit",
                    erc20Debit.asAddress(),
                    erc20.asAddress(),
                    AMOUNT
            );

            assertEquals(aliceWallet.asAddress(), erc20Debit.owner());
            assertEquals(AMOUNT, erc20Debit.denomination());
            assertEquals(INIT_AMOUNT.subtract(AMOUNT), erc20.balanceOf(aliceWallet.asAddress()));

            assertTrue(aliceWallet.owns(erc20Debit.asAddress()));

        }

        @Test
        public void testActivateDeactivate721() throws Exception {
            SampleERC721 e721 = SampleERC721.deploy(web3j, ALICE, GAS_PROVIDER);

            // send a new aliceToken to the wallet
            BigInteger TOKENID = bigInt(101);
            Address aliceWalAddr = aliceWallet.asAddress();
            e721.mint(aliceWalAddr, TOKENID);
            assertEquals(aliceWalAddr, e721.ownerOf(TOKENID));


            ERC721Tradable e721Tradable = ERC721Tradable.deploy(web3j, ALICE, bigInt(22_000_000_000L), bigInt(5_000_000));

            assertFalse(aliceWallet.owns(e721Tradable.asAddress()));

            aliceWallet.activateTradable721(
                    e721Tradable.asAddress(),
                    e721.asAddress(),
                    "a car",
                    "alice has it",
                    "",
                    new byte[32],
                    TOKENID
            );
            // console.log(tx);
            assertEquals(aliceWallet.asAddress(), e721Tradable.owner());
            assertEquals((e721Tradable.tokenID()), TOKENID);
            assertEquals(e721Tradable.asAddress(), e721.ownerOf(TOKENID));
            assertTrue(aliceWallet.owns(e721Tradable.asAddress()));

            // test the the deactivate
            aliceWallet.deactivateERC721ISingular(e721Tradable.asAddress(), address(SOMEONE));
            // now the aliceToken should belong to the wallet
            assertEquals(e721.ownerOf(TOKENID), address(SOMEONE));

            // transfer the aliceToken to the alice wallet
            e721.from(SOMEONE).transferFrom(address(SOMEONE), aliceWallet.asAddress(), TOKENID);

            // to test unbind, create a new instance
            e721Tradable = ERC721Tradable.deploy(web3j, ALICE, GAS_PROVIDER2);
            aliceWallet.activateTradable721(
                    e721Tradable.asAddress(),
                    e721.asAddress(),
                    "a car",
                    "alice has it",
                    "",
                    new byte[32],
                    TOKENID
            );
            //
            // test the the unbind on the aliceToken directly, another way to deactivate the aliceToken
            e721Tradable.unbind(address(SOMEONE)); // return the aliceToken to aliceEOA

            // now the aliceToken should belong to the wallet
            assertEquals(e721.ownerOf(TOKENID), address(SOMEONE));

        }
    }

    @Nested
    class TradableTradings {
        
        Tradable aliceToken;
        
        @BeforeEach
        public void setupCash() throws Exception {
            BigInteger ERC20_AMOUNT = BigInteger.valueOf(2000);
            BigInteger DEBIT_AMOUNT = BigInteger.valueOf(1000);
            TransactionReceipt tx = erc20.transfer(bobWallet.asAddress(), ERC20_AMOUNT);
            ERC20Debit debit = ERC20Debit.deploy(web3j, BOB, GAS_PROVIDER2);
            // let's activate it by a walwhich will transfer some fund to the debit card

            tx = bobWallet.activateE20Debit(
                    "bob's debit card",
                    debit.asAddress(),
                    erc20.asAddress(),
                    DEBIT_AMOUNT
            );
            // now bob's debit card is ready to buy something

            aliceToken = Tradable.deploy(web3j, ALICE, GAS_PROVIDER2);
            aliceToken.init(
                    "alice token",
                    "personal asset",
                    "",
                    "",
                    new byte[32],
                    aliceWallet.asAddress(),
                    aliceWallet.asAddress()
            );

        }
        
        @Test
        public void makeASell() throws Exception {
            long t = System.currentTimeMillis() / 1000;
            BigInteger validFrom = bigInt(t);
            BigInteger validTill = bigInt(t + 30);
            
            BigInteger PRICE = BigInteger.valueOf(900);
            TransactionReceipt tx = aliceToken.sellFor(
                    erc20.asAddress(),
                    PRICE,
                    validFrom,
                    validTill,
                    "buy it now!"
            );

            Tuple5<Address, Address, BigInteger, BigInteger, BigInteger> saleOffer = aliceToken.saleOffer();
            assertEquals(saleOffer.getValue1(), aliceWallet.asAddress());
            assertEquals(saleOffer.getValue2(), erc20.asAddress());
            assertEquals(saleOffer.getValue3(), PRICE);
            assertEquals(saleOffer.getValue4(), validFrom);
            assertEquals(saleOffer.getValue5(), validTill);
            // assertEquals(saleOffer.note, "buy it now!");

            List<Tradable.SaleOfferApprovedEventResponse> evts = aliceToken.getSaleOfferApprovedEvents(tx);
            assertEquals(1, evts.size());


            // let's make a purchase
            // 1. bob propose a  swap

            // this call requires big gas
            ERC20Debit debit = ERC20Debit.deploy(web3j, BOB, bigInt(20_000_000_000L), bigInt(5_000_000));

            BigInteger AMOUNT = bigInt(1000);

            // give bob wallet some money
            erc20.transfer(bobWallet.asAddress(), AMOUNT);
            // transfer the money to the debit card
            tx = bobWallet.activateE20Debit(
                    "bob debit",
                    debit.asAddress(),
                    erc20.asAddress(),
                    AMOUNT
            );

            TradeExecutor tradeExecutor = TradeExecutor.deploy(web3j, ALICE, GAS_PROVIDER);

            // both must trust the executor
            tx = debit.setExecutor(tradeExecutor.asAddress());
            aliceToken.setExecutor(tradeExecutor.asAddress());

            tx = debit.approveSwap(
                    aliceToken.asAddress(),
                    validFrom,
                    validTill,
                    "why not"
            );

            // verify the offer
            Tuple4<Address, Address, BigInteger, BigInteger> swapOffer = debit.swapOffer();
            assertEquals(aliceToken.asAddress(), swapOffer.getValue2());
            assertEquals(validFrom, swapOffer.getValue3());
            assertEquals(validTill, swapOffer.getValue4());
            // assertEquals(swapOffer.note, "why not");
            // 3. let's do it
            tx =  tradeExecutor.buy(aliceToken.asAddress(), debit.asAddress());

            assertEquals(bobWallet.asAddress(), aliceToken.owner(), "aliceToken owner was not transferred to bob");
            assertEquals(aliceWallet.asAddress(), debit.owner(), "bob's debit was not transferred to alice");
        }
    }
}
