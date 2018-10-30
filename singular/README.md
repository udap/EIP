
### status

- 0.1 version is cut. The basics and ERC20 and 721 adapters are in the ERC20/721 directories. 
- 0.2 version is cut. Swapping and buys with ERC20Debit are working. A bunch of tests have been added to the the source code tree, which can be invoked with truffle test in the singular/src directory. Check the truffle-config.json for the eth client setup.  

  
Do the following steps to start hacking:

`git clone git@github.com:udap/EIP.git`

In the singular/src directory:
1. `npm install --save-exact openzeppelin-solidity`
2. `truffle compile`
3. `truffle test` 

*New since 2018/10/29*: This version also introduced full testing support with web3j wrappers. The project is managed by gradle and the specific tasks are `solcWithWeb3j` and the artifacts are specified in the build.gradle file. It is intended to be a full replacement for the truffle dependency as the compiling and testing environment, for better performance and robustness.

The steps are

1. install native solc, the Solidity compiler
1. install gradle build tool
1. clone the repository (the web3 integration is currently in the `web3j` branch)
1. to compile the contracts and rebuild the web3j wrappers: `./gradlew solcWithWeb3j`
1. start the test blockchain: `ganache-cli -d`
1. run the tests `./gradlew test`
 
Check out the `build.gradle` for path configuration in the `solc` task.

The tests are in `src/test/java/org/singular/web3j`
 

Note: 

1. I was using ganache GUI as the testing eth client, as reflected in the port setting in the truffle-config.json. 
2. I was using Truffle v5 beta 1 as the dev environment. see `https://github.com/trufflesuite/truffle/releases`
3. The most interesting class is `Tradable`, which is a tradable singular that can do transfers, swaps, buy and sells with erc20 tokens wrapped in ERC20Debit. Check out the Tradable.test.js for the usage. 



# The Singular Token Account in UDAP 

UDAP has the vision that every single thing in the world should have a unique address on the blockchain, including people and everything. 

## Goals
In UDAP we are proposing a new account model specifically to represent a single unique asset.  Specifically we want to achieve these goals when designing the model:

1. Sealing with single asset token is very intuitive. 
2. The API must be clean, simple without ambiguity.
3. There should be strong type safety. 
4. Properties of the token can be made as static as possible. 
5. Wallet API should be made very simple.
6. It should support using an operator to control the ownership transfer on the real owner’s behalf. 
7. It should support a time-lock mechanism that offer a guarantee of ownership to the receiver within a defined period of time. 
8. It should support simple atomic token swap between two `Singular` asset owners. 
9. The `Singular` token should work with state channel mechanisms, which is very important for scalable applications.

## Designs
We call our basic asset contract `Singular` and the design decisions are:

1. A piece of asset is uniquely pegged to a smart contract account. As a result, the full token identification is the account address.
2. The owner of the `Singular` token must be another smart contract account, named `TokenOwner`. There is no direct way for EOAs to own `Singular` tokens.  This design largely conforms to Ethereum’s account abstraction model that will be deployed in a future version of Ethereum. 
3. It should support one-step ownership transfer and two-step ownership transfers.  In so called one-step transfer, the current owner can pass an offer of the token ownership to the receiver account and the receiver account can choose to accept or reject the offer *in the same transaction*. In a two-step ownership transfer, however, the current owner reserves the token for the next owner in a transaction. The address of the token is passed to the receiver out-of-band. The receiver issues a separate transaction to accept the offer, once it determines that the offer is in its interest. 
4. The `Singular` account can assign an operator address to help with ownership transfers.  Having an operator to manage the asset token on the owner’s behalf is a pattern that has been accepted by some other proposals, such as ERC721 and ERC777.  People have found it convenient in handling token trading. The current token owner can appoint an operator for the *next* ownership change.  Once the transaction has been completed, the operator *must* be revoked, since the operator is only valid for the previous ownership. 
5. When an owner make an offer of ownership to someone else by calling the `approve()` function, there is a required argument for expiry time, during which period the receiver can take the ownership at will by invoking `accept(...)`on the token, which will in turn send a notification to the previous owner for it to any state update it wants, or even chain to another action. A critical design is that the owner cannot change his mind during the offer period. This is essentially a time-lock for the transaction. In contrast, neither ERC20 nor ERC721 or any of their derivatives offers built-in time-locks for ownership trading. 
6. A feature is provided for fast token swap between two accounts with a hashlock:
```
// offer from Alice: 
	AliceToken.offerToSwap(BobToken, hashLock);
// Bob takes offer:
	BobToken.swap(AliceToken, hashLock);

// then
```

## notes

1. Swap is not in the specification yet. 
2. License: PGL2.0
