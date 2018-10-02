const { assertRevert } = require('./helpers/assertRevert');
const { logit } = require('./helpers/logit');
const expectEvent = require('./helpers/expectEvent');
var e20 = artifacts.require("./samples/SampleERC20.sol");
var w = artifacts.require("./ERC20/SingularWalletWithE20.sol");
var erc20Contract = artifacts.require("./ERC20/ERC20Debit.sol");

contract('DebitFactory', function ([defaultEOA, aliceEOA, bobEOA, someEOA]) {
    // let e20inst;
    // e20.deployed().then((inst) => {
    //     e20inst = inst;
    // });

    it("can be deployed", async () => {
        let erc20 = await e20.new({from: aliceEOA});
        assert.notEmpty(erc20);
        let wa = await w.new("erc20 wallet", {from: aliceEOA});
        assert.equal(await wa.ownerAddress.call(), aliceEOA);
    });


    it("can create debit card", async () => {
        let wal = await w.new("erc20 wallet", {from: aliceEOA});

        // let's create an ERC20
        let erc20 = await e20.new({from: aliceEOA});
        assert.notEmpty(erc20);

        // transfer some balance to the wallet
        const INIT_AMOUNT = 2000;
        let ttx = await erc20.transfer(wal.address, INIT_AMOUNT, {from: aliceEOA});

        // create an empty ERC20Debit
        let erc20Debit = await erc20Contract.new({from: aliceEOA});

        // let's activate it by a wallet which will transfer some fund to the debit card

        const AMOUNT = 1000;
        let tx = await wal.activateDebit("alice debit", erc20Debit.address, erc20.address, AMOUNT, {from: aliceEOA});
        // console.log(tx);
        assert.equal(await erc20Debit.owner.call(), wal.address);
        assert.equal(await erc20Debit.denomination.call(), AMOUNT);
        assert.equal((await erc20.balanceOf.call(wal.address)).toNumber(), INIT_AMOUNT - AMOUNT);
    });

});