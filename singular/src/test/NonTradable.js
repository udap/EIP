var NonTradable = artifacts.require("./impl/NonTradable.sol");
var w = artifacts.require("./impl/BasicSingularWallet.sol");

contract('NonTradable', async function ([acct1, acct2]) {

    it("should probably set up in the constructor", async () => {
        var wallet = await w.deployed();
        var nonTrada = await NonTradable.new(
            "Andrew",
            "PERSON",
            "Andrew is a good boy",
            "http://t.me/123123",
            web3.utils.fromAscii("0"), // to bytes32
            0,
            wallet.address,
            {from: acct1}
        );
        assert.equal(await nonTrada.creator.call(), acct1);
        assert.equal(await nonTrada.owner.call(), wallet.address);
    });

})

