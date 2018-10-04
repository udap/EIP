/*
 * NB: since truffle-hdwallet-provider 0.0.5 you must wrap HDWallet providers in a 
 * function when declaring them. Failure to do so will cause commands to hang. ex:
 * ```
 * mainnet: {
 *     provider: function() { 
 *       return new HDWalletProvider(mnemonic, 'https://mainnet.infura.io/<infura-key>') 
 *     },
 *     network_id: '1',
 *     gas: 4500000,
 *     gasPrice: 10000000000,
 *   },
 */

module.exports = {
    // See <http://truffleframework.com/docs/advanced/configuration>
    // to customize your Truffle configuration!

    networks: {
        development: {
            host: "127.0.0.1",
            port: 7545, // local ganache runs on this port
            network_id: "*", // * for Match any network id,
            // gas: 4500000,
            // gasPrice: 10000000000
        }
    },

    compilers: {
        solc: {
            version: "native",   // let's use the native for faster work. bran: slower for small project!!
            // version: "0.4.25"
            settings: {
                optimizer: {
                    enabled: true, // Default: false
                    // runs: 100     // Default: 200
                },
                //evmVersion: "homestead"  // Default: "byzantium"
            }
        }
    }
};

let mnemonic = 'stuff denial chuckle permit shell orbit priority solution dog cool stone blush';