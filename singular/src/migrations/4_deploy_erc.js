var e20 = artifacts.require("./samples/SampleERC20.sol");
var e721 = artifacts.require("./samples/SampleERC721.sol");

module.exports = function(deployer, network, accounts) {
    deployer.deploy(e20, {from: accounts[0]});
    // deployer.deploy(e721, {from: accounts[0]});
};
