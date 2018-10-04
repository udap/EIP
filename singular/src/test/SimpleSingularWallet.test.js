const { assertRevert } = require('./helpers/assertRevert');
var SingularWallet = artifacts.require("./impl/BasicSingularWallet.sol");

contract('BasicSingularWallet', function ([aliceEOA, bobEOA, someEOA]) {

    describe('all about transfers', function () {
        let aliceWallet = null;

        beforeEach(async function () {
            aliceWallet = await SingularWallet.new(
                "alice",
                {from: aliceEOA}
            );
        });

        it("should probably set up in the constructor", async () => {
            assert.equal(await aliceWallet.ownerAddress.call(), aliceEOA);
        });
    });
})

