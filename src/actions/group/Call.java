package actions.group;

import engine.bet.Bet;
import engine.player.Player;
import exceptions.ActionException;

import java.util.function.Consumer;

/**
 * A call action enables the user to match an already oustanding wager within the table
 * @author Max Smith
 */
public class Call extends GroupAction {

    public Call() {
        super();
    }

    /**
     * Execute a call action, first validates that you can call the bet
     * @param p the player that is performing the action
     * @param b the current bet of said player that is being evaluated
     * @param selectWager a functional interface to select a wager
     * @param setTableBet a functional interface to set the table bet
     * @param activatePlayers a functional interface to active the rest of the players on the table
     * @param tableMin the table minimum
     * @param tableMax the table maximum
     * @param currentBet the current bet that has been placed on the table (to match)
     * @throws ActionException when there are no current bets to call
     */
    @Override
    public void execute(Player p, Bet b,
                        WagerSelector selectWager, Consumer<Double> setTableBet, Consumer<Bet> activatePlayers,
                        double tableMin, double tableMax, double currentBet) throws ActionException {
        if (currentBet == 0)
            throw new ActionException(this);
        b.setWager(b.getWager() + currentBet);
        b.setRoundActive(false);
    }
}
