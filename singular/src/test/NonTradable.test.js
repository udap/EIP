const { assertRevert } = require('./helpers/assertRevert');
let NonTradableTest = artifacts.require("./impl/NonTradable.sol");
let w = artifacts.require("./impl/BasicSingularWallet.sol");

contract('NonTradable', function ([acct1, acct2]) {
    const BYTES32SRC = "0123456789abcdef0123456789abcdef";
    const BYTES32 = web3.utils.fromAscii(BYTES32SRC)
    const NAME = "Andrew";
    const PERSON = "PERSON";
    const DESCR = "Andrew is a good boy";
    const URI = "http://t.me/123123";

    var wallet;
    var nonTrada;
    // w.new(
    //     "alice wallet",
    //     {from: acct1}
    // ).then((i) => {wallet = i; });

    beforeEach(async function () {
        wallet = await w.new(
            "alice wallet",
            {from: acct1}
        );

        nonTrada = await NonTradableTest.new(
            {from: acct1}
        );
        await nonTrada.init(
            NAME,
            PERSON,
            DESCR,
            URI,
            BYTES32, // to bytes32
            acct2,  //
            wallet.address,
            // {from: acct1}
        );

    });

    it("should probably set up", async () => {

        assert.equal(await nonTrada.contractName.call(), "NonTradable");
        assert.equal(await nonTrada.creator.call(), acct1);
        assert.equal(await nonTrada.owner.call(), wallet.address);
        assert.equal(await nonTrada.tokenType.call(), acct2);
        // the metadata part
        assert.equal(await nonTrada.name.call(), NAME);
        assert.equal(await nonTrada.symbol.call(), PERSON);
        assert.equal(await nonTrada.description.call(), DESCR);
        assert.equal(await nonTrada.tokenURI.call(), URI);
        assert.equal(await nonTrada.tokenURIDigest.call(), BYTES32);

        // ownership interlocked with the wallet
        assert.isTrue(await wallet.owns.call(nonTrada.address));

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

        // var wallet = await w.at("0x3536Ca51D15f6fc0a76c1f42693F7949b5165F0D");
        // console.log("wallet address:" + wallet.address);
        let nt = await NonTradableTest.new(
            {from: acct2}
        );
        //should revert because the account does not match the wallet owner
        assertRevert(nt.init(
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

    it("should not allow re-initialization", async () => {
        let nt = await NonTradableTest.new(
            {from: acct2}
        );

        // should be fine the first time
        await nt.init(
            NAME,
            PERSON,
            DESCR,
            URI,
            BYTES32, // to bytes32
            acct1,  //
            wallet.address,
            {from: acct1}
        );

        // should revert the second time.
        assertRevert(nt.init(
            NAME,
            PERSON,
            DESCR,
            URI,
            BYTES32, // to bytes32
            acct1,  //
            wallet.address,
            {from: acct1}
        ));
    });

    it("should not allow any other operation before initialization", async () => {
        let nt = await NonTradableTest.new(
            {from: acct2}
        );

        assertRevert(nt.setOperator(acct2));

        // should be fine the first time
        await nt.init(
            NAME,
            PERSON,
            DESCR,
            URI,
            BYTES32, // to bytes32
            acct1,  //
            wallet.address,
            {from: acct1}
        );

        // now we can do that
        await (nt.setOperator(acct2));
        assert.equal((await nt.operator.call()), acct2);
    });
});

