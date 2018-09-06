# Design of Cross Channel Swap for Non-fungible Tokens

The case in study:

There are two state channels connected by a common member:

initial states in two channels, which verified on chain:

`A(♤$)===============B` & `B===============(♧)C`

The end state after the transaction is completed in the state channel is:  

`A(♧)-------------($)B` & `B---------------(♤)C`

A now owns the club ♧, B owns the $ as tip, and C owns the spade ♤. With the help of B, A and C have successfully swapped a pair of poker tokens.



Two strategies:

1. intermediary as the witness: the intermediary B only plays the role of witnessing the latest off-chain states in both channels by signing on the offline transaction contracts. It does not intervene between the two parties in a swap transaction. This is referred in some design as "virtual state channel" in Perun or "magachannel" in Counterfactual. 

2. The intermediary plays the role of direct counterparty in a two-transaction transitive swap. This scheme is similar to the Lightening Network, which relies on some form of Hashed Time-locked Contract (HTLC).   



We're proposing a new strategy named "merged update" and "merged exit". 

## Protocol for non-fungible asset swap between two channels

Note: we assume that any offline contracts must be signed by parties involved. The counterparty usually will counter-sign the contract after verifying the state of token involved from his/her local evidence (usually the latest agreed state statement in a channel). We leave out the co-signing step unless we need to give in-depth analysis of the specific step. 

1. In `channel(A, B)`, A and B manage to agree on an offer contract `contract(A <-> B)`: 
```
(
    offer: A.♤
    need: C.♧ in channel(B,C)
    validTill: now + 1h
    service fee: A.$
    signatures: sig(A), sig(B)
)
```

To achieve the contract, B uses his LOCAL evidence to verifies the state in both channels to make sure of the both tokens are in a state that can potentially settle the contract.

This happens off-chain.
  
2. B shows C the guaranteed offer to C, by whatever off-line means. 
3. C verifies that the `A.♤` is in custody of a time-locked contract `contract(A, B)` which consigns the `A.♤` token to B for the intended swap before a deadline which has not expired. 
4. B and C agree on an offer contract `contract(C <-> B)`
```
(
    offer: C.♧
    need: A.♤ in channel(A, B)
    validTill: now + 50m
    service fee: 0
    signatures: sig(C), sig(B)
)
```
This happens off-chain.

As of now, both `A.♤` and `C.♧` are locked in a time-locked contract in respective channels, counterfactually.

5. A, B and C agree on a swap contract and the resultant state:
```
(
    contracts to reconcile: contract(A <-> B), contract(C <-> B)
    pre-state: A.♤, A.$, C.♧
    post-state: C.♤, B.$, A.♧
    signatures: sig(A), sig(C), sig(B)
)
```
Once this contract has been signed, the token swap has been concluded counterfactually, and the current state in the channels are:

`A(♧)---------($)B` & `B--------------(♤)C`


The on-chin state is still:

`A(♤$)===========B` & `B==============(♧)C`


### Attack analysis:

1. Consider C colludes with B to withdraw ♧ from `channel(B, C)`. They submit a co-signed an channel close transaction to the channel contract `channelContract(B, C)` to exit with a stale state.
```
(
    action: close
    current-state: B.null C.♧
    signatures: sig(C), sig(B)
)
```

In regular state channel implementation, this is considered a collaborative channel closure and and be carried out instantly, resulting in a insolvent and in-settleable `channel(A, B)`. 

*Consequence*: all the transaction from the step 1 in the previous sections will be rolled back. In the best scenario the attack would turn to a griefing attack and in the worst case the some asset is lost in the form of opportunity cost or volatility cost. 

*Mitigations*: 

1. any channel closure event is broadcasted and must go through a challenge period. 
2. Proof-of-existence challenge???
3. A layered 


In Hub-and-Spoke configuration, B is equipped with the full knowledge of all the off-chain agreements and play to the role of common intermediary.

But this does not mean that B must be trusted to conduct and transactions. Using B is simply a convenient path to do business. All parties must keep all the signed agreements locally to hold the hub (the common intermediary) accountable. 

Moreover, all the parties must monitor the previous owners of their tokens to get involved with any transactions that involve the previously-owned tokens, which is a compromise of each others' privacy. 

Mitigations:

1. One way of keep everyone honest is to enact a random challenge model.
2. Entrust an independent party to monitor the transactions to prevent double-spending or collusion between a node and the hub. 
   

