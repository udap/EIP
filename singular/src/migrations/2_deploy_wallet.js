var w = artifacts.require("./impl/BasicSingularWallet.sol");

module.exports = function(deployer, network, accounts) {
  deployer.deploy(
      w,
      "Andrew's wallet",
      "wallet",
      "simple wallet",
      "",
      web3.utils.fromAscii("0"), // to bytes32
      {from: accounts[0]}
  ).then((inst) => {
      console.log(inst.address);
  });
};
