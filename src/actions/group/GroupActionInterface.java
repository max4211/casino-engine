package actions.group;

import engine.bet.Bet;
import engine.player.Player;
import exceptions.ActionException;

import java.util.function.Consumer;

/**
 * Interface implemented by all gruop actions with all parameters they need
 * within the controller and view to operate
 * @author Max Smith
 */
public interface GroupActionInterface {

    /**
     * Give the group action all of the information it needs to operate
     * @param p the player that is performing the action
     * @param b the current bet of said player that is being evaluated
     * @param selectWager a functional interface to select a wager
     * @param setTableBet a functional interface to set the table bet
     * @param activatePlayers a functional interface to active the rest of the players on the table
     * @param tableMin the table minimum
     * @param tableMax the table maximum
     * @param currentBet the current bet that has been placed on the table (to match)
     * @throws ActionException if the action cannot be created
     */
    void execute(Player p, Bet b,
                 WagerSelector selectWager, Consumer<Double> setTableBet, Consumer<Bet> activatePlayers,
                 double tableMin, double tableMax, double currentBet) throws ActionException;

}
