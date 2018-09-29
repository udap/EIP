var w = artifacts.require("./impl/BasicSingularWallet.sol");
// var lib = artifacts.require("./impl/TradableLib.sol");

module.exports = function(deployer, network, accounts) {
  // deployer.deploy(
  //     w,
  //     "Andrew's wallet",
  //     "wallet",
  //     "simple wallet",
  //     "",
  //     web3.utils.fromAscii("0"),
  //     {from: accounts[0]}
  // ).then(async (inst) => {
  //     console.log("deployed wallet 1: " + inst.address);
  //     let w2 = await deployer.deploy(
  //         w,
  //         "Andrew's wallet",
  //         "wallet",
  //         "simple wallet",
  //         "",
  //         web3.utils.fromAscii("0"),
  //         {from: accounts[1]}
  //     )
  //     console.log("deployed wallet 2: " + w2.address);
  // });
  // deployer.deploy(lib);
};
