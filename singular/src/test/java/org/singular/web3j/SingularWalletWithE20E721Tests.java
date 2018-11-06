package org.singular.web3j;

import io.udap.web3j.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.web3j.abi.datatypes.Address;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

public class SingularWalletWithE20E721Tests extends GanacheIT {

    static SampleERC20 erc20;
    static SingularWalletWithE20E721 aliceWal;

    @BeforeAll
    public static void canBeDeployed() throws Exception {
        erc20 = SampleERC20.deploy(web3j, ALICE, GAS_PROVIDER);
        assertEquals(42, erc20.at().length());

        aliceWal = SingularWalletWithE20E721.deploy(web3j, ALICE, GAS_PROVIDER, "Alice aliceWal");
        assertEquals(42, aliceWal.at().length());
//        System.out.println("gas:" + aliceWal.getTransactionReceipt().get().getGasUsed());

        assertEquals(ALICE.getAddress(), aliceWal.ownerAddress().toString());
    }

    @Nested
    class Debits {
        @Test
        public void canActivateDebitCard() throws Exception {
            BigInteger INIT_AMOUNT = bigInt(2000);
            TransactionReceipt re = erc20.transfer(aliceWal.asAddress(), INIT_AMOUNT);
            assertEquals("0x1", re.getStatus());

            // this call requires big gas
            ERC20Debit erc20Debit = ERC20Debit.deploy(web3j, ALICE, bigInt(20_000_000_000L), bigInt(5_000_000));
            assertTrue(erc20Debit.getTransactionReceipt().get().getGasUsed().intValue() < 5_0000_000);

            BigInteger AMOUNT = bigInt(1000);
            TransactionReceipt tx = aliceWal.activateE20Debit(
                    "alice debit",
                    erc20Debit.asAddress(),
                    erc20.asAddress(),
                    AMOUNT
            );

            assertEquals(aliceWal.asAddress(), erc20Debit.owner());
            assertEquals(AMOUNT, erc20Debit.denomination());
            assertEquals(INIT_AMOUNT.subtract(AMOUNT), erc20.balanceOf(aliceWal.asAddress()));

            assertTrue(aliceWal.owns(erc20Debit.asAddress()));

        }

        @Test
        public void testActivateDeactivate721() throws Exception {
            SampleERC721 e721 = SampleERC721.deploy(web3j, ALICE, GAS_PROVIDER);

            // send a new aliceToken to the wallet
            BigInteger TOKENID = bigInt(101);
            Address aliceWalAddr = aliceWal.asAddress();
            e721.mint(aliceWalAddr, TOKENID);
            assertEquals(aliceWalAddr, e721.ownerOf(TOKENID));


            ERC721Tradable e721Tradable = ERC721Tradable.deploy(web3j, ALICE, bigInt(22_000_000_000L), bigInt(5_000_000));

            assertFalse(aliceWal.owns(e721Tradable.asAddress()));

            aliceWal.activateTradable721(
                    e721Tradable.asAddress(),
                    e721.asAddress(),
                    "a car",
                    "alice has it",
                    "",
                    new byte[32],
                    TOKENID
            );
            // console.log(tx);
            assertEquals(aliceWal.asAddress(), e721Tradable.owner());
            assertEquals((e721Tradable.tokenID()), TOKENID);
            assertEquals(e721Tradable.asAddress(), e721.ownerOf(TOKENID));
            assertTrue(aliceWal.owns(e721Tradable.asAddress()));

            // test the the deactivate
            aliceWal.deactivateERC721ISingular(e721Tradable.asAddress(), address(SOMEONE));
            // now the aliceToken should belong to the wallet
            assertEquals(e721.ownerOf(TOKENID), address(SOMEONE));

            // transfer the aliceToken to the alice wallet
            e721.from(SOMEONE).transferFrom(address(SOMEONE), aliceWal.asAddress(), TOKENID);

            // to test unbind, create a new instance
            e721Tradable = ERC721Tradable.deploy(web3j, ALICE, GAS_PROVIDER2);
            aliceWal.activateTradable721(
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


}
