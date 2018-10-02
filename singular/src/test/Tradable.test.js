const { assertRevert } = require('./helpers/assertRevert');
const { logit } = require('./helpers/logit');
const expectEvent = require('./helpers/expectEvent');
var Tradable = artifacts.require("./impl/Tradable.sol");
var SingularWallet = artifacts.require("./impl/BasicSingularWallet.sol");
var e = artifacts.require("./impl/TradableExecutor.sol");
var {TimeUtil} = require("./helpers/TimeUtil");
// var lib = artifacts.require("./impl/TradableLib.sol");

contract('Tradable', function ([defaultEOA, aliceEOA, bobEOA, someEOA]) {

    const WALL_NAME = "Andrew's wallet";
    const BYTES32SRC = "0123456789abcdef0123456789abcdef";
    const BYTES32= web3.utils.fromAscii(BYTES32SRC);
    const NAME = "Andrew";
    const PERSON = "PERSON";
    const DESCR = "Andrew is a good boy";
    const URI = "http://t.me/123123";

    // let link the lib to the contract first // note: we don't use library in this implementation
    // lib.deployed().then((libinstance) => {
    //     // console.log("lib: " + libinstance.address);
    //     Tradable.link("TradableLib", libinstance.address); // has to link explicitly to the symbol
    // })

    var exe;
    e.deployed().then((inst) => {exe = inst})

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

            token = await Tradable.new();
            await token.init(
                NAME,
                PERSON,
                DESCR,
                URI,
                BYTES32, // to bytes32
                aliceEOA,  //
                aliceWallet.address,
                {from: aliceEOA}
            );

            await token.setExecutor(exe.address, {from: aliceEOA})
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
            // logit(tx, "approveReceiver");
            expectEvent.inLogs(
                tx.logs,
                'ReceiverApproved',
                {
                    from: aliceWallet.address,
                    to: bobWallet.address,
                    validFrom: now,
                    validTill: validTill,
                    senderNote: 'for fun'}
                );
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

            aliceToken = await Tradable.new({from: aliceEOA});
            await aliceToken.init(
                "aliceToken",
                PERSON,
                DESCR,
                URI,
                BYTES32, // to bytes32
                aliceEOA,  //
                aliceWallet.address,
                {from: aliceEOA}
            );
            await aliceToken.setExecutor(exe.address, {from: aliceEOA})

            bobToken = await Tradable.new({from: bobEOA});
            await bobToken.init(
                "bobToken",
                PERSON,
                DESCR,
                URI,
                BYTES32, // to bytes32
                aliceEOA,  //
                bobWallet.address,
                {from: bobEOA}
            );
            await bobToken.setExecutor(exe.address, {from: bobEOA})
        });

        it("approves swaps from the owner", async () => {
            let validFrom = TimeUtil.nowInSeconds() -1;
            let validTill = validFrom + 2000;
            let tx = await aliceToken.approveSwap(
                bobToken.address,
                validFrom,
                validTill,
                "cool",
                {from: aliceEOA}
            )
            let swapOffer = await aliceToken.swapOffer.call();
            // logit(swapOffer);
            assert.equal(swapOffer.target, bobToken.address);
            assert.equal(swapOffer.validFrom, validFrom);
            assert.equal(swapOffer.validTill, validTill);
            assert.equal(swapOffer.note, "cool");
            // let's do the swap
            // 1. propose a reverse swap
            tx = await bobToken.approveSwap(
                aliceToken.address,
                validFrom,
                validTill - 2,
                "why not",
                {from: bobEOA}
            )
            swapOffer = await bobToken.swapOffer.call();
            // logit(swapOffer, "swp offer on bob");
            assert.equal(swapOffer.target, aliceToken.address);
            assert.equal(swapOffer.validFrom, validFrom);
            assert.equal(swapOffer.validTill, validTill - 2);
            assert.equal(swapOffer.note, "why not");
            // 2. verify the onchain time
            let evmTime = (await bobToken.ping.call({from: bobEOA})).toNumber();
            // logit(evmTime, "evem time")
            assert.isAtLeast(evmTime, validFrom);
            assert.isAtMost(evmTime, validTill);
            assert.isTrue(await aliceToken.isInSwap.call());
            assert.isTrue(await bobToken.isInSwap.call());
            // 3. let's do it
            assertRevert(bobToken.acceptSwap("do it", {from: bobEOA})) // because this method is for counterparty to call
            // logit (" from Alice:" + aliceEOA + " to bobToken:" + bobToken.address, "acceptSwap");
            tx = await  bobToken.acceptSwap("do it", {from: aliceEOA})
            // logit(tx, "accept tx");
            assert.equal(await aliceToken.owner.call(), bobWallet.address, "aliceToken owner was not swapped to bob");
            assert.equal(await bobToken.owner.call(), aliceWallet.address, "bobToken owner was not swapped to alice");
        });

        it("not approve swaps aliceToken from Bob", async () => {
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

