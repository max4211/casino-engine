package actions.group;

import engine.bet.Bet;
import engine.player.Player;
import exceptions.ActionException;

import java.util.function.Consumer;

/**
 * A bet action enables the user to select a wager, using various functional interfaces
 * to affect other components inside of the view and the controller without
 * intimate knowledge of the full objects
 */
public class BetAction extends GroupAction {

    public BetAction() {
        super();
    }

    /**
     * Execute a bet action, which prompts the user in the view to place a wager
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
        double min = tableMin;
        double max = Math.min(tableMax, p.getBankroll());
        double wager = selectWager.getBet(min, max);
        b.setWager(b.getWager() + wager);
        p.updateBankroll(wager);
        b.setRoundActive(false);
        activatePlayers.accept(b);
        setTableBet.accept(wager);
    }
}
