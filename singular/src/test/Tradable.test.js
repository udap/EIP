const { assertRevert } = require('./helpers/assertRevert');
var Tradable = artifacts.require("./impl/Tradable.sol");
var SingularWallet = artifacts.require("./impl/BasicSingularWallet.sol");

contract('Tradable', function ([aliceEOA, bobEOA, someEOA]) {

    const WALL_NAME = "Andrew's wallet";
    const BYTES32SRC = "0123456789abcdef0123456789abcdef";
    const BYTES32= web3.utils.fromAscii(BYTES32SRC);
    const NAME = "Andrew";
    const PERSON = "PERSON";
    const DESCR = "Andrew is a good boy";
    const URI = "http://t.me/123123";


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
                {from: aliceEOA}
            );

        });

        it("should probably set up in the constructor", async () => {
            // var alice = await SingularWallet.deployed();
            // var alice = await SingularWallet.at("0xE6c2c610d48C95793a201D85824C6c6AEcdF0079");
            // console.log("alice address:" + alice.address);

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

        it("approve", async () => {
            let now = Math.round(new Date().getTime()/1000); // turn to seconds since epoch.
            let validTill = now + 10;
            // in solidity we have on the token
            //     function approveReceiver(
            //         ISingularWallet _to,
            //         uint256 _validFrom,
            //         uint256 _validTill,
            //         string _reason
            //      )
            let tx = await token.approveReceiver(
                bobWallet.address,
                now,
                validTill,
                "for fun",
                {from: aliceEOA}
            );
            console.log(tx);
        });
    });
})

