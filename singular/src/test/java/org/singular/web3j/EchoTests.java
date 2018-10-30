package org.singular.web3j;


import io.udap.web3j.Echo;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.singular.web3j.GanacheIT.*;

/**
 * see {@link GanacheIT} for setting up Ganache-cli
 */
public class EchoTests {
    private static final Logger log = LoggerFactory.getLogger(EchoTests.class);
    public static final String GREETING = "ABC";
    public static final String HI_AGAIN = "hi again";

    io.udap.web3j.Echo Echo;
    String contractAddress;

    @Before
    public void setup() throws Exception {
        Echo = Echo.deploy(
                web3j,
                ALICE,
                GAS_PROVIDER,
                GREETING
        );
        contractAddress = Echo.getContractAddress();
    }



    public EchoTests() throws Exception {
    }


    @Test
    public void testEcho() throws Exception {
        // use a ganache-cli personal account

        // FIXME: Request some Ether for the Rinkeby test network at https://www.rinkeby.io/#faucet
        log.info("Sending 1 Wei ("
                + Convert.fromWei("1", Convert.Unit.ETHER).toPlainString() + " Ether)");
        TransactionReceipt transferReceipt = Transfer.sendFunds(
                web3j,
                ALICE,
                "0x19e03255f667bdfd50a32722df860b1eeaf4d635",  // you can put any address here
                BigDecimal.ONE,
                Convert.Unit.WEI)  // 1 wei = 10^-18 Ether
                .send();

        // Now lets deploy a smart contract
        log.info("Deploying smart contract");

        String contractAddress = Echo.getContractAddress();
        log.info("Smart contract deployed to address " + contractAddress);

        log.info("Value stored in remote smart contract: " + Echo.greet());

        // Lets modify the value in our smart contract
        TransactionReceipt transactionReceipt = Echo.newGreeting("Well hello again");

        log.info("New value stored in remote smart contract: " + Echo.greet());

        // Events enable us to log specific events happening during the execution of our smart
        // contract to the blockchain. Index events cannot be logged in their entirety.
        // For Strings and arrays, the hash of values is provided, not the original value.
        // For further information, refer to https://docs.web3j.io/filters.html#filters-and-events
        for (Echo.ModifiedEventResponse event : Echo.getModifiedEvents(transactionReceipt)) {
            log.info("Modify event fired, previous value: " + event.oldGreeting
                    + ", new value: " + event.newGreeting);
            log.info("Indexed event previous value: " + Numeric.toHexString(event.oldGreetingIdx)
                    + ", new value: " + Numeric.toHexString(event.newGreetingIdx));
        }
    }

    @Test
    public void testLowLevelTx() throws Exception {
        final Function function = new Function(
                "newGreeting",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String("hi again")),
                Collections.<TypeReference<?>>emptyList()
        );

        // for transactions

        EthSendTransaction transactionResponse = web3j.ethSendTransaction(
                Transaction.createFunctionCallTransaction(
                    ALICE.getAddress(),
                    getNonce(ALICE),
                    GAS_PRICE,
                    GAS_LIMIT,
                    contractAddress,
                    FunctionEncoder.encode(function)
                )
        ).send();

        assertTrue(!transactionResponse.hasError());
        String transactionHash = transactionResponse.getTransactionHash();
    }
   @Test

    public void testLowLevelTxSignedRaw() throws Exception {
        final Function function = new Function(
                "newGreeting",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(HI_AGAIN)),
                Collections.<TypeReference<?>>emptyList());

        RawTransaction rawTransaction = RawTransaction.createTransaction(
            getNonce(BOB),
            GAS_PRICE,
            GAS_LIMIT,
            contractAddress,
            BigInteger.ZERO,
                FunctionEncoder.encode(function)
        );

        EthSendTransaction tx = web3j.ethSendRawTransaction(
               Numeric.toHexString(
                       TransactionEncoder.signMessage(
                               rawTransaction,
                               BOB
                       )
               )
        ).send();

        assertFalse(tx.hasError());
        assertEquals(HI_AGAIN, Echo.greet());

   }

    @Test
    public void testLowLevelCall() throws Exception {
        final Function function = new Function(
                "greet",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(
                        new TypeReference<Utf8String>() {}
                        )
        );

        // for calls
        EthCall response = web3j.ethCall(
                Transaction.createEthCallTransaction(
                        ALICE.getAddress(),
                        contractAddress,
                        FunctionEncoder.encode(function)
                ),
                DefaultBlockParameterName.LATEST
        ).sendAsync().get();

        List<Type> someTypes = FunctionReturnDecoder.decode(response.getValue(), function.getOutputParameters());
        assertTrue(someTypes.size() == 1);
        Type t = someTypes.get(0);
        assertTrue(t instanceof Utf8String);
        assertTrue(GREETING.equals(((Utf8String)t).getValue()));
    }

    private BigInteger getNonce(Credentials who) throws java.io.IOException {
        return web3j.ethGetTransactionCount(
                who.getAddress(),
                DefaultBlockParameterName.PENDING
        ).send().getTransactionCount();
    }


}