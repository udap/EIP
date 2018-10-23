#!/bin/bash
CONTRACTS=$PWD/contracts
OPENZEPPELIN=$PWD/node_modules/openzeppelin-solidity
solc --bin -o tmp --overwrite --optimize --optimize-runs 1 contracts=$CONTRACTS openzeppelin-solidity=$OPENZEPPELIN ./contracts/**/*.sol
