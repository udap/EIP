const { assertRevert } = require('./helpers/assertRevert');
const { logit } = require('./helpers/logit');
const expectEvent = require('./helpers/expectEvent');
var e20Contract = artifacts.require("./samples/SampleERC20.sol");
var e721Contract = artifacts.require("./samples/SampleERC721.sol");
var w = artifacts.require("./impl/SingularWalletWithE20E721.sol");
var Erc20DebitContract = artifacts.require("./ERC20/ERC20Debit.sol");
var e721TradableCon = artifacts.require("./ERC721/ERC721Tradable.sol");
var e721NonTradableCon = artifacts.require("./ERC721/ERC721NonTradable.sol");

contract('SingularWalletWithE20E721', function ([defaultEOA, aliceEOA, bobEOA, someEOA]) {

    let BYTES32SRC = "0123456789abcdef0123456789abcdef";
    let BYTES32 = web3.utils.fromAscii(BYTES32SRC)

    it("can be deployed", async () => {
        let erc20 = await e20Contract.new({from: aliceEOA});
        assert.isNotEmpty(erc20);
        let wa = await w.new("wallet", {from: aliceEOA});
        assert.equal(await wa.ownerAddress.call(), aliceEOA);
    });


    it("can activate erc20 debit card", async () => {
        let wal = await w.new("alice wallet", {from: aliceEOA});
        // let's create an ERC20
        let erc20 = await e20Contract.new({from: aliceEOA});
        assert.isNotEmpty(erc20);

        // transfer some balance to the wallet
        let INIT_AMOUNT = 2000;
        let ttx = await erc20.transfer(wal.address, INIT_AMOUNT, {from: aliceEOA});

        // create an empty ERC20Debit
        let erc20Debit = await Erc20DebitContract.new({from: aliceEOA});

        // let's activate it by a wallet which will transfer some fund to the debit card

        let AMOUNT = 1000;
        let tx = await wal.activateE20Debit(
            "alice debit",
            erc20Debit.address,
            erc20.address,
            AMOUNT,
            {from: aliceEOA}
        );
        // console.log(tx);
        assert.equal(await erc20Debit.owner.call(), wal.address);
        assert.equal(await erc20Debit.denomination.call(), AMOUNT);
        assert.equal((await erc20.balanceOf.call(wal.address)).toNumber(), INIT_AMOUNT - AMOUNT);
        assert.isTrue(await wal.owns.call(erc20Debit.address));
    });

    it("can activate  erc721 Tradable", async () => {
        let wal = await w.new("alice wallet", {from: aliceEOA});
        let e721 = await e721Contract.new({from: aliceEOA});

        // send a new token to the wallet
        let TID = 101;
        await e721.mint(wal.address, TID);
        assert.equal(await e721.ownerOf.call(TID), wal.address);

        let FROM_ALICE = {from: aliceEOA};
        let e721Tradable = await e721TradableCon.new(FROM_ALICE);
        assert.notEmpty(e721Tradable);
        assert.isFalse(await wal.owns.call(e721Tradable.address));

        await wal.activateTradable721(
            e721Tradable.address,
            e721.address,
            "a car",
            "alice has it",
            "",
            BYTES32,
            TID,
            FROM_ALICE
        );
        // console.log(tx);
        assert.equal(await e721Tradable.owner.call(), wal.address);
        assert.equal((await e721Tradable.tokenID.call()).toNumber(), TID);
        assert.equal((await e721.ownerOf.call(TID)), e721Tradable.address);
        assert.isTrue(await wal.owns.call(e721Tradable.address));
    });

    it("can activate erc721 NonTradable", async () => {
        let wal = await w.new("alice wallet", {from: aliceEOA});
        let e721 = await e721Contract.new({from: aliceEOA});


        // // send a new token to the wallet
        let TID = 102;
        await e721.mint(wal.address, TID);
        assert.equal(await e721.ownerOf.call(TID), wal.address);

        let FROM_ALICE = {from: aliceEOA};

        let e721NonTradable = await e721NonTradableCon.new(FROM_ALICE);
        assert.notEmpty(e721NonTradable);
        assert.isFalse(await wal.owns.call(e721NonTradable.address));

        await wal.activateNonTradable721(
            e721NonTradable.address,
            e721.address,
            "a car",
            "alice has it",
            "",
            BYTES32,
            TID,
            FROM_ALICE
        );
        // console.log(tx);
        assert.equal(await e721NonTradable.owner.call(), wal.address);
        assert.equal((await e721NonTradable.tokenID.call()).toNumber(), TID);
        assert.equal((await e721.ownerOf.call(TID)), e721NonTradable.address);
        assert.isTrue(await wal.owns.call(e721NonTradable.address));
    });

});