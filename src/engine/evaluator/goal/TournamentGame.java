package engine.evaluator.goal;

import engine.player.Player;

import java.util.Collection;
import java.util.List;

/**
 * Specific type of goal, play until only one player left
 */
public class TournamentGame extends Goal {

    private Player myWinner;

    public TournamentGame(List<Player> players, List<String> args) {
        super(players, args);
    }

    /**
     * Evaluates how many players are left in the game
     * @return true if only one player left
     */
    @Override
    public boolean goalAchieved() {
        return this.myPlayers.size() == 1;
    }

    /**
     * Assigns winner
     * @return the player with the highest bankroll
     */
    @Override
    public Player gameWinner() {
        this.myWinner = this.myPlayers.get(0);
        return this.myWinner;
    }
}
