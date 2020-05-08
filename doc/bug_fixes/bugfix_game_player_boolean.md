## Description

In a game of blackjack, all players should have the opportunity to act on their hands.

## Expected Behavior

Normally, all players should have an opportunity to play their hand, regardless of position in the round.

## Current Behavior

The feature's bug is at during a round of blackjack, all players except the last player are given the opportunity to act.

## Steps to Reproduce

Provide detailed steps for reproducing the issue.

 1. Play a game of "blackjack", checking all the way through
 2. Note the last player does not have the opportunity to act.

## Failure Logs

Player blackjack game on prior git commit, last player did not act.

## Hypothesis for Fixing the Bug

I believe to fix the bug, I will need to update a boolean operation in the backend. One was implemented to protect a poker hand from stopping at standstill, but inadvertantly halted blackjack games prematurely.

## Bug Resolution

The bug resoluion was consistent with my original hypothesis. There was a boolean operation in `Table` which was evaluating on values `> 1`, but should have been `>=1`.

```java3
    @Override
    public boolean hasActivePlayers() {
        return getNextPlayer() != null && getTotalActivePlayers() >= 1;
    }
```