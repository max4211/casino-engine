package actions.group;

import engine.bet.Bet;
import engine.player.Player;
import exceptions.ActionException;

import java.util.function.Consumer;
/**
 * A check action changes the round active status of the user's bet
 * @author Max Smith
 */
public class Check extends GroupAction {

    public Check() {
        super();
    }

    /**
     *
     * @param p the player that is performing the action
     * @param b the current bet of said player that is being evaluated
     * @param selectWager a functional interface to select a wager
     * @param setTableBet a functional interface to set the table bet
     * @param activatePlayers a functional interface to active the rest of the players on the table
     * @param tableMin the table minimum
     * @param tableMax the table maximum
     * @param currentBet the current bet that has been placed on the table (to match)
     * @throws ActionException when the player cannot check (outstanding bets)
     */
    @Override
    public void execute(Player p, Bet b,
                        WagerSelector selectWager, Consumer<Double> setTableBet, Consumer<Bet> activatePlayers,
                        double tableMin, double tableMax, double currentBet) throws ActionException {
        if (currentBet >= tableMin)
            throw new ActionException(this);
        b.setRoundActive(false);
    }
}
