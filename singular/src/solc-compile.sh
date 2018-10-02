#!/bin/bash
solc --bin -o tmp --overwrite --optimize --optimize-runs 1 contracts=/Users/bran/projects/udap/EIP/singular/src/contracts  openzeppelin-solidity=/Users/bran/projects/udap/EIP/singular/src/node_modules/openzeppelin-solidity ./contracts/**/*.sol
