var NonTradable = artifacts.require("./impl/NonTradable.sol");
var w = artifacts.require("./impl/BasicSingularWallet.sol");

contract('NonTradable', async function ([acct1, acct2]) {

    it("should probably set up in the constructor", async () => {
        var wallet = await w.deployed();
        const BYTES32SRC = "0123456789abcdef0123456789abcdef";
        const BYTES32= web3.utils.fromAscii(BYTES32SRC)
        const NAME = "Andrew";
        const PERSON = "PERSON";
        const DESCR = "Andrew is a good boy";
        const URI = "http://t.me/123123";
        var nonTrada = await NonTradable.new(
            NAME,
            PERSON,
            DESCR,
            URI,
            BYTES32, // to bytes32
            acct2,  //
            wallet.address,
            {from: acct1}
        );
        assert.equal(await nonTrada.creator.call(), acct1);
        assert.equal(await nonTrada.owner.call(), wallet.address);
        assert.equal(await nonTrada.tokenType.call(), acct2);
        // the metadata part
        assert.equal(await nonTrada.name.call(), NAME);
        assert.equal(await nonTrada.symbol.call(), PERSON);
        assert.equal(await nonTrada.description.call(), DESCR);
        assert.equal(await nonTrada.tokenURI.call(), URI);
        // use startsWith to avoid the trailing nulls with the string. toAscii bug?
        assert.isTrue(web3.utils.toAscii(await nonTrada.tokenURIDigest.call()).startsWith(BYTES32SRC));
        assert.equal(await nonTrada.tokenURIDigest.call(), BYTES32);
        try {
            await nonTrada.sendTo(acct2, nonTrada.address);// sendTo does not exist
        } catch (error) {
            console.log(error);
            // const revertFound = error.message.search('revert') >= 0;
            // assert(revertFound, `Expected "revert", got ${error} instead`);
            // return;
        }
    });

})

