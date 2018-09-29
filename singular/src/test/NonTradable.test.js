const { assertRevert } = require('./helpers/assertRevert');
var NonTradableTest = artifacts.require("./impl/NonTradable.sol");
var w = artifacts.require("./impl/BasicSingularWallet.sol");

contract('NonTradable', async function ([acct1, acct2]) {

    it("should probably set up in the constructor", async () => {
        // var wallet = await w.deployed();
        // var wallet = await w.at("0x3536Ca51D15f6fc0a76c1f42693F7949b5165F0D");
        wallet = await w.new(
            "alice",
            "wallet",
            "simple wallet for alice",
            "",
            web3.utils.fromAscii("0"),
            {from: acct1}
        );

        console.log("wallet address:" + wallet.address);
        const BYTES32SRC = "0123456789abcdef0123456789abcdef";
        const BYTES32= web3.utils.fromAscii(BYTES32SRC)
        const NAME = "Andrew";
        const PERSON = "PERSON";
        const DESCR = "Andrew is a good boy";
        const URI = "http://t.me/123123";
        var nonTrada = await NonTradableTest.new(
            NAME,
            PERSON,
            DESCR,
            URI,
            BYTES32, // to bytes32
            acct2,  //
            wallet.address,
            {from: acct1}
        );
        assert.equal(await nonTrada.contractName.call(), "NonTradable");
        assert.equal(await nonTrada.creator.call(), acct1);
        assert.equal(await nonTrada.owner.call(), wallet.address);
        assert.equal(await nonTrada.tokenType.call(), acct2);
        // the metadata part
        assert.equal(await nonTrada.name.call(), NAME);
        assert.equal(await nonTrada.symbol.call(), PERSON);
        assert.equal(await nonTrada.description.call(), DESCR);
        assert.equal(await nonTrada.tokenURI.call(), URI);
        // use startsWith to avoid the trailing nulls with the string. toAscii bug?
        // assert.isTrue(web3.utils.toAscii(await nonTrada.tokenURIDigest.call()).startsWith(BYTES32SRC));
        assert.equal(await nonTrada.tokenURIDigest.call(), BYTES32);
        try {
            await nonTrada.sendTo(acct2, nonTrada.address);// sendTo does not exist
            assert.fail("the error was not caught");
        } catch (error) {
            // console.log(error);
            // const revertFound = error.message.search('revert') >= 0;
            // assert(revertFound, `Expected "revert", got ${error} instead`);
            // return;
        }
    });

    it("should revert if the token creator is not the wallet owner", async () => {
        wallet = await w.new(
            "alice",
            "wallet",
            "simple wallet for alice",
            "",
            web3.utils.fromAscii("0"),
            {from: acct1}
        );

        // var wallet = await w.at("0x3536Ca51D15f6fc0a76c1f42693F7949b5165F0D");
        console.log("wallet address:" + wallet.address);
        const BYTES32SRC = "0123456789abcdef0123456789abcdef";
        const BYTES32 = web3.utils.fromAscii(BYTES32SRC)
        const NAME = "Andrew";
        const PERSON = "PERSON";
        const DESCR = "Andrew is a good boy";
        const URI = "http://t.me/123123";
        assertRevert(NonTradableTest.new(
            NAME,
            PERSON,
            DESCR,
            URI,
            BYTES32, // to bytes32
            acct2,  //
            wallet.address,
            {from: acct2}
        ))
    });
})

