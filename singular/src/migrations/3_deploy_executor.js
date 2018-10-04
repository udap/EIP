var e = artifacts.require("./impl/TradeExecutor.sol");

module.exports = function(deployer, network, accounts) {
    deployer.deploy(e)
};
