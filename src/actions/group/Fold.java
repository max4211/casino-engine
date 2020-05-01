package actions.group;

import engine.bet.Bet;
import engine.player.Player;
import exceptions.ActionException;

import java.util.function.Consumer;

/**
 * A fold action updates the current bet to a loser for the round and deactivates them
 */
public class Fold extends GroupAction {

    public Fold() {
        super();
    }

    /**
     * Execute a fold action by updating loser and active tags
     * @param p the player that is performing the action
     * @param b the current bet of said player that is being evaluated
     * @param selectWager a functional interface to select a wager
     * @param setTableBet a functional interface to set the table bet
     * @param activatePlayers a functional interface to active the rest of the players on the table
     * @param tableMin the table minimum
     * @param tableMax the table maximum
     * @param currentBet the current bet that has been placed on the table (to match)
     * @throws ActionException
     */
    @Override
    public void execute(Player p, Bet b,
                        WagerSelector selectWager, Consumer<Double> setTableBet, Consumer<Bet> activatePlayers,
                        double tableMin, double tableMax, double currentBet) throws ActionException {
        b.getHand().setLoser(true);
        b.setRoundActive(false);
        b.setGameActive(false);
    }
}
