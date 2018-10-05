const { assertRevert } = require('./helpers/assertRevert');
var Wallet = artifacts.require("./impl/BasicSingularWallet.sol");
var Tradable = artifacts.require("./impl/Tradable.sol");

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
        let token = null;

        beforeEach(async function () {
            aliceWallet = await Wallet.new(
                "alice",
                {from: aliceEOA}
            );

            bobWallet = await Wallet.new(
                "bob",
                {from: bobEOA}
            );

            token = await Tradable.new();
            await token.init(
                "name",
                "PERSON",
                "DESCR",
                "URI",
                web3.utils.fromAscii("0123456789abcdef0123456789abcdef"),
                aliceEOA,  //
                aliceWallet.address,
                {from: aliceEOA}
            );
        });

        it("should own the token", async () => {
            assert.isTrue(await aliceWallet.owns.call(token.address));
            let {tokenNum, timestamp} = await aliceWallet.numOfTokens.call();
            assert.equal(tokenNum, 1);
            assert.isTrue(timestamp > 1);

        });

        it("sendTo on wallet", async () => {
            let tx = await aliceWallet.sendTo(bobWallet.address, token.address, "", {from: aliceEOA});
            // console.log(tx);
            let {tokenNum, timestamp} = await aliceWallet.numOfTokens.call();
            assert.equal(tokenNum, 0);
            ({tokenNum, timestamp} = await bobWallet.numOfTokens.call()); // note (...)
            assert.equal(tokenNum, 1);



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
})

