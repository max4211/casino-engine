package engine.evaluator.goal;

import engine.player.Player;

import java.util.Collection;
import java.util.List;

/**
 * Specific type of goal, play indefinitely
 */
public class CashGame extends Goal {

    public CashGame(List<Player> players, List<String> args) {
        super(players, args);
    }

    /**
     * Determine if the goal has been achieved
     * @return always false, cash games are indefinite
     */
    @Override
    public boolean goalAchieved() {
        return false;
    }

    /**
     * Retusn the appropiate winner form the game
     * @return the player with the highest bankroll
     */
    @Override
    public Player gameWinner() {
        return this.myWinner;
    }
}
