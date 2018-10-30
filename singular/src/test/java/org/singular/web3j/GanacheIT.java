package org.singular.web3j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 *
 * to prepare test accounts from Ganache-cli, which must have started with "-d" flag for deterministic account
 * creation: <code>ganache-cli -d</code>
 *
 * Better off we use a fixed mnemonics for deterministic account creation.
 *
 */
public class GanacheIT {
    private static final Logger log = LoggerFactory.getLogger(GanacheIT.class);
    public static final BigInteger GAS_PRICE = BigInteger.valueOf(22_000_000_000L);
    public static final BigInteger GAS_LIMIT = BigInteger.valueOf(4_790_000L);
    public static Credentials ALICE = Credentials.create("0x4f3edf983ac636a65a842ce7c78d9aa706d3b113bce9c46f30d7d21715b23b1d");
    public static Credentials BOB = Credentials.create("0x6cbed15c793ce57650b9877cf6fa156fbef513c4e6134f022a85b1ffdd59b2a1");
    public static Credentials SOMEONE = Credentials.create("0x6370fd033278c143179d81c5526140625662b8daa446c22ee2d73db3707e620c");
    public static final Credentials EMPTY = Credentials.create("0x48e568d9b255d3773619486cde02a10b40e5a8ace51d320f7f1ba20825703d00");

    public static Web3j web3j;
    public static ContractGasProvider GAS_PROVIDER = new DefaultGasProvider();

    // anti-pattern. should avoid using static
    static {
        web3j = Web3j.build(new HttpService("http://localhost:8545/"));
        try {
            log.info("Connected to Ethereum client version: " + web3j.web3ClientVersion().send().getWeb3ClientVersion());
            List<String> accounts = web3j.ethAccounts().send().getAccounts();
            assertEquals( "alice account not matched", ALICE.getAddress(), accounts.get(0));
            assertEquals("alice account not matched", BOB.getAddress(), accounts.get(1));
            assertEquals("alice account not matched", SOMEONE.getAddress(), accounts.get(2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Before
//    public void setUp() throws IOException {
//        web3j = Web3j.build(new HttpService("http://localhost:8545/"));
//        log.info("Connected to Ethereum client version: " + web3j.web3ClientVersion().send().getWeb3ClientVersion());
//    }
//
}
