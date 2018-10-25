#!/bin/bash

if [ ! -d "$PWD/node_modules" ]; then
  printf "==ERROR==\\nOps, \`node_modules\` not found. Please run \`npm install\` first.\\n==ERROR==\\n"
  exit 1
fi

CONTRACTS=${PWD}/contracts
OPENZEPPELIN=${PWD}/node_modules/openzeppelin-solidity

solc --bin -o tmp --overwrite --optimize --optimize-runs 1 contracts=${CONTRACTS} openzeppelin-solidity=${OPENZEPPELIN} ./contracts/**/*.sol
