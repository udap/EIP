const { assertRevert } = require('./helpers/assertRevert');
var Wallet = artifacts.require("./impl/BasicSingularWallet.sol");
var Tradable = artifacts.require("./impl/Tradable.sol");
var TradableFactory = artifacts.require("./samples/TradableFactory.sol");

numOfTokens = undefined;
contract('BasicSingularWallet', function ([aliceEOA, bobEOA, someEOA]) {

    describe('all about transfers', function () {
        let aliceWallet = null;
        let bobWallet = null;
        let aliceToken = null;

        beforeEach(async function () {
            aliceWallet = await Wallet.new(
                "alice",
                {from: aliceEOA}
            );
        });

        it("should probably set up in the constructor", async () => {
            assert.equal(await aliceWallet.ownerAddress.call(), aliceEOA);
            const {tokenNum, timestamp} = await aliceWallet.numOfTokens.call();
            assert.equal(tokenNum, 0);
            assert.equal(timestamp, 0);

        });
    });

    describe('transfer between', function () {
        let aliceWallet = null;
        let bobWallet = null;
        let token1 = null;
        let token2 = null;

        beforeEach(async function () {
            aliceWallet = await Wallet.new(
                "alice",
                {from: aliceEOA}
            );

            bobWallet = await Wallet.new(
                "bob",
                {from: bobEOA}
            );

            token1 = await Tradable.new();
            await token1.init(
                "name",
                "PERSON",
                "DESCR",
                "URI",
                web3.utils.fromAscii("0123456789abcdef0123456789abcdef"),
                aliceEOA,  //
                aliceWallet.address,
                {from: aliceEOA}
            );
            token2 = await newTradable("token2", aliceWallet);
            assert.equal(await token2.name.call(), "token2");
        });

        it("should own the token & enumerate", async () => {
            assert.isTrue(await aliceWallet.owns.call(token1.address));
            assert.isTrue(await aliceWallet.owns.call(token2.address));
            let {tokenNum, timestamp} = await aliceWallet.numOfTokens.call();
            assert.equal(tokenNum, 2);
            assert.isTrue(timestamp > 1);
            let all = await aliceWallet.getAllTokens.call();
            // console.log(all);
            assert.equal(all[0].length, 2); // the all contains two element: the 1st is the array, the 2nd is the timestamp

        });


        it("sendTo on wallet", async () => {
            let tx = await aliceWallet.sendTo(bobWallet.address, token1.address, "", {from: aliceEOA});
            // console.log(tx);
            let {tokenNum : aa, timestamp : ta} = await aliceWallet.numOfTokens.call();
            assert.equal(aa, 1);
            ({tokenNum : bb, timestamp: tb} = await bobWallet.numOfTokens.call()); // note (...)
            assert.equal(bb, 1);

            // send the token back
            await token1.sendTo(aliceWallet.address, "back to you", {from: bobEOA});
            // console.log(tx);
            ({tokenNum, timestamp} = await aliceWallet.numOfTokens.call());
            assert.equal(tokenNum, 2);
            assert.isTrue(timestamp.toNumber() >= ta.toNumber()); //
            ({tokenNum, timestamp} = await bobWallet.numOfTokens.call()); // note (...)
            assert.equal(tokenNum, 0);
            assert.isTrue(timestamp.toNumber() >= tb.toNumber());



            // expectEvent.inLogs(
            //     tx.logs,
            //     'ReceiverApproved',
            //     {
            //         from: aliceWallet.address,
            //         to: bobWallet.address,
            //         validFrom: now,
            //         validTill: validTill,
            //         senderNote: 'for fun'}
            // );
        });
    });
});

newTradable = async (s, wal) => {
    let token = await Tradable.new();
    await token.init(
        s,
        "PERSON",
        "DESCR",
        "URI",
        web3.utils.fromAscii("0123456789abcdef0123456789abcdef"),
        "",
        wal.address
        // {from: aliceEOA}
    );
    return token;
};

