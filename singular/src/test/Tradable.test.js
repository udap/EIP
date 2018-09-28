const { assertRevert } = require('./helpers/assertRevert');
var Tradable = artifacts.require("./impl/Tradable.sol");
var SingularWallet = artifacts.require("./impl/BasicSingularWallet.sol");
var {TimeUtil} = require("./helpers/TimeUtil");
var lib = artifacts.require("./impl/TradableLib.sol");

contract('Tradable', function ([aliceEOA, bobEOA, someEOA]) {

    const WALL_NAME = "Andrew's wallet";
    const BYTES32SRC = "0123456789abcdef0123456789abcdef";
    const BYTES32= web3.utils.fromAscii(BYTES32SRC);
    const NAME = "Andrew";
    const PERSON = "PERSON";
    const DESCR = "Andrew is a good boy";
    const URI = "http://t.me/123123";

    // let link the lib to the contract first
    lib.deployed().then((libinstance) => {
        // console.log("lib: " + libinstance.address);
        Tradable.link("TradableLib", libinstance.address); // has to link explicitly to the symbol
    })

    describe('all about transfers', function () {
        let aliceWallet = null;
        let bobWallet = null;
        let token = null;

        beforeEach(async function () {
            aliceWallet = await SingularWallet.new(
                "alice",
                "wallet",
                "simple wallet for alice",
                "",
                web3.utils.fromAscii("0"),
                {from: aliceEOA}
            );

            bobWallet = await SingularWallet.new(
                "bob",
                "wallet",
                "simple wallet for bob",
                "",
                web3.utils.fromAscii("0"),
                {from: bobEOA}
            );

            token = await Tradable.new(
                NAME,
                PERSON,
                DESCR,
                URI,
                BYTES32, // to bytes32
                aliceEOA,  //
                aliceWallet.address,
                {from: aliceEOA,/* gas: 4000000, gasprice: 100000000000*/}
            );
            // console.log("token: " + token.address);

        });

        it("should probably set up in the constructor", async () => {
            assert.equal(await token.contractName.call(), "Tradable");
            assert.equal(await token.creator.call(), aliceEOA);
            assert.equal(await token.tokenType.call(), aliceEOA);
            // the metadata part
            assert.equal(await token.name.call(), NAME);
            assert.equal(await token.symbol.call(), PERSON);
            assert.equal(await token.description.call(), DESCR);
            assert.equal(await token.tokenURI.call(), URI);
            // use startsWith to avoid the trailing nulls with the string. toAscii bug?
            // assert.isTrue(web3.utils.toAscii(await token.tokenURIDigest.call()).startsWith(BYTES32SRC));
            assert.equal(await token.tokenURIDigest.call(), BYTES32);
            // owners
            assert.equal(await token.owner.call(), aliceWallet.address);
            assert.equal(await token.previousOwner.call(), 0);
            assert.equal(await token.nextOwner.call(), 0);

        });

        it("approve from owner", async () => {
            let now = TimeUtil.nowInSeconds();
            let validTill = now + 10;
            let tx = await token.approveReceiver(
                bobWallet.address,
                now,
                validTill,
                "for fun",
                {from: aliceEOA}
            );
            // console.log(tx);
        });
        it("shoudl not approve from unauthorized", async () => {
            let now = TimeUtil.nowInSeconds();
            let validTill = now + 10;
            assertRevert(token.approveReceiver(
                bobWallet.address,
                now,
                validTill,
                "for fun",
                {from: bobEOA}
            ))

        });
    });

    describe('swapping', function () {
        let aliceWallet = null;
        let bobWallet = null;
        let aliceToken = null;
        let bobToken = null;

        beforeEach(async function () {
            aliceWallet = await SingularWallet.new(
                "alice",
                "wallet",
                "simple wallet for alice",
                "",
                web3.utils.fromAscii("0"),
                {from: aliceEOA}
            );

            bobWallet = await SingularWallet.new(
                "bob",
                "wallet",
                "simple wallet for bob",
                "",
                web3.utils.fromAscii("0"),
                {from: bobEOA}
            );

            aliceToken = await Tradable.new(
                "aliceToken",
                PERSON,
                DESCR,
                URI,
                BYTES32, // to bytes32
                aliceEOA,  //
                aliceWallet.address,
                {from: aliceEOA}
            );

            bobToken = await Tradable.new(
                "bobToken",
                PERSON,
                DESCR,
                URI,
                BYTES32, // to bytes32
                aliceEOA,  //
                aliceWallet.address,
                {from: bobEOA}
            );

        });

        it("approves swaps", async () => {
            let now = TimeUtil.nowInSeconds();
            let validTill = now + 10;
            await aliceToken.approveSwap(
                bobToken.address,
                now,
                validTill,
                "cool",
                {from: aliceEOA}
            )

        });
        it("not approve swaps", async () => {
            let now = TimeUtil.nowInSeconds();
            let validTill = now + 10;
            assertRevert(aliceToken.approveSwap(
                bobToken.address,
                now,
                validTill,
                "cool",
                {from: bobEOA}
            ))
        });
    });
})

