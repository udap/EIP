var e = artifacts.require("./impl/TradableExecutor.sol");

module.exports = function(deployer, network, accounts) {
    deployer.deploy(e)
};
