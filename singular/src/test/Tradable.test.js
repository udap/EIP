const { assertRevert } = require('./helpers/assertRevert');
const { logit } = require('./helpers/logit');
const expectEvent = require('./helpers/expectEvent');
var Tradable = artifacts.require("./impl/Tradable.sol");
var Wallet = artifacts.require("./impl/SingularWalletWithE20E721.sol");
var TradeExecutor = artifacts.require("./impl/TradeExecutor.sol");
var {TimeUtil} = require("./helpers/TimeUtil");

var E20Contract = artifacts.require("./samples/SampleERC20.sol");
var Erc20DebitContract = artifacts.require("./ERC20/ERC20Debit.sol");

contract('Tradable', function ([defaultEOA, aliceEOA, bobEOA, someEOA]) {

    const WALL_NAME = "Andrew's wallet";
    const BYTES32SRC = "0123456789abcdef0123456789abcdef";
    const BYTES32= web3.utils.fromAscii(BYTES32SRC);
    const NAME = "Andrew";
    const PERSON = "PERSON";
    const DESCR = "Andrew is a good boy";
    const URI = "http://t.me/123123";

    var exe;
    TradeExecutor.deployed().then((inst) => {exe = inst})

    describe('transfers', function () {
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
            assert.equal(await token.tokenURIDigest.call(), BYTES32);
            // owners
            assert.equal(await token.owner.call(), aliceWallet.address);
            assert.equal(await token.previousOwner.call(), 0);
            assert.equal(await token.nextOwner.call(), 0);
            assert.isTrue(await aliceWallet.owns.call(token.address));
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
            aliceWallet = await Wallet.new(
                "alice",
                {from: aliceEOA}
            );

            bobWallet = await Wallet.new(
                "bob",
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
            // assert.equal(swapOffer.note, "cool");
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
            // assert.equal(swapOffer.note, "why not");
            // 2. verify the onchain time
            let evmTime = (await bobToken.ping.call({from: bobEOA})).toNumber();
            // logit(evmTime, "evem time")
            assert.isAtLeast(evmTime, validFrom);
            assert.isAtMost(evmTime, validTill);
            assert.isTrue(await aliceToken.isInSwap.call());
            assert.isTrue(await bobToken.isInSwap.call());
            // 3. let's do it
            // assertRevert(bobToken.acceptSwap("do it", {from: bobEOA})) // because this method is for counterparty to call
            // logit (" from Alice:" + aliceEOA + " to bobToken:" + bobToken.address, "acceptSwap");
            tx = await  exe.swap(aliceToken.address, bobToken.address, {from: aliceEOA});
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
    describe('sell and buy', function () {
        let ERC20_AMOUNT = 2000;
        let DEBIT_AMOUNT = 1000;

        let aliceWallet = null;
        let bobWallet = null;
        let aliceToken = null;
        let bobToken = null;
        let erc20;
        let debit; // bob has a debit card

        beforeEach(async function () {
            aliceWallet = await Wallet.new(
                "alice",
                {from: aliceEOA}
            );

            bobWallet = await Wallet.new(
                "bob",
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
            await bobToken.setExecutor(exe.address, {from: bobEOA});

            erc20 = await E20Contract.new({from: bobEOA});
            let ttx = await erc20.transfer(bobWallet.address, ERC20_AMOUNT, {from: bobEOA});
            let amount = await erc20.balanceOf.call(bobWallet.address)

            debit = await Erc20DebitContract.new({from: bobEOA});

            // let's activate it by a wallet which will transfer some fund to the debit card

            let tx = await bobWallet.activateE20Debit(
                "bob's debit card",
                debit.address,
                erc20.address,
                DEBIT_AMOUNT,
                {from: bobEOA}
            );
            // now bob's debit card is ready to buy something

        });

        it("makes a sell", async () => {
            let validFrom = TimeUtil.nowInSeconds() -1;
            let validTill = validFrom + 2000;
            const PRICE = 900;
            let tx = await aliceToken.sellFor(
                erc20.address,
                PRICE,
                validFrom,
                validTill,
                "buy it now!",
                {from: aliceEOA}
            )
            let saleOffer = await aliceToken.saleOffer.call();
            assert.equal(saleOffer.owner, aliceWallet.address);
            assert.equal(saleOffer.erc20, erc20.address);
            assert.equal(saleOffer.price, PRICE);
            assert.equal(saleOffer.validFrom, validFrom);
            assert.equal(saleOffer.validTill, validTill);
            // assert.equal(saleOffer.note, "buy it now!");
            expectEvent.inLogs(tx.logs, "SaleOfferApproved", {
                item: aliceToken.address,
                erc20: erc20.address,
                price: PRICE,
                validFrom: validFrom,
                validTill: validTill
            } );

            // let's make a purchase
            // 1. bob propose a  swap

            tx = await debit.setExecutor(exe.address, {from: bobEOA});
            tx = await debit.approveSwap(
                aliceToken.address,
                validFrom,
                validTill - 2,
                "why not",
                {from: bobEOA}
            )
            swapOffer = await debit.swapOffer.call();
            assert.equal(swapOffer.target, aliceToken.address);
            assert.equal(swapOffer.validFrom, validFrom);
            assert.equal(swapOffer.validTill, validTill - 2);
            // assert.equal(swapOffer.note, "why not");
            // 2. verify states
            let evmTime = (await bobToken.ping.call({from: bobEOA})).toNumber();
            // logit(evmTime, "evem time")
            assert.isAtLeast(evmTime, validFrom);
            assert.isAtMost(evmTime, validTill);
            assert.isTrue(await aliceToken.isForSale.call());
            assert.isTrue(await debit.isInSwap.call());
            // 3. let's do it
            tx = await exe.buy(aliceToken.address, debit.address, {from: bobEOA});
            // logit(tx, "accept tx");
            expectEvent.inLogs(tx.logs, "Sold");
            expectEvent.inLogs(tx.logs, "GiveChange");
            // check change amount
            let change = tx.logs[0].args.changeAmount.toNumber();
            assert.equal(change, DEBIT_AMOUNT-PRICE, "change is not correct")

            assert.equal(await aliceToken.owner.call(), bobWallet.address, "aliceToken owner was not transferred to bob");
            assert.equal(await debit.owner.call(), aliceWallet.address, "bob's debit was not transferred to alice");
        });
        //
        // it("not approve swaps aliceToken from Bob", async () => {
        //     let now = TimeUtil.nowInSeconds();
        //     let validTill = now + 10;
        //     assertRevert(aliceToken.approveSwap(
        //         bobToken.address,
        //         now,
        //         validTill,
        //         "cool",
        //         {from: bobEOA}
        //     ))
        // });
    });
})

