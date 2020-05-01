package actions.group;

import engine.bet.Bet;
import engine.player.Player;
import exceptions.ActionException;

import java.util.function.Consumer;

/**
 * A raise action enables the user to select a wager greater than the current outstanding wager
 */
public class Raise extends GroupAction {

    public Raise() {
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
     * @throws ActionException if a raise is not valid (must be outstanding bet on table)
     */
    @Override
    public void execute(Player p, Bet b,
                        WagerSelector selectWager, Consumer<Double> setTableBet, Consumer<Bet> activatePlayers,
                        double tableMin, double tableMax, double currentBet) throws ActionException {
        if (currentBet == 0)
            throw new ActionException(this);
        double wager = selectWager.getBet(currentBet, tableMax);

        b.setWager(b.getWager() + wager);
        b.setRoundActive(false);
        activatePlayers.accept(b);
        setTableBet.accept(wager);

    }
}
