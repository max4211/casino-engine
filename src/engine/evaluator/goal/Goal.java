package engine.evaluator.goal;

import engine.player.Player;

import java.util.Collection;
import java.util.List;

/**
 * Abstract Goal object, implements goal interface for all subclasses
 */
public abstract class Goal implements GoalInterface {

    protected final List<Player> myPlayers;
    protected final List<String> myArgs;
    protected Player myWinner;

    /**
     * Construct a goal object with players and arguments
     * @param players list of all players to evaluate goal on
     * @param args arguments into goal
     */
    public Goal(List<Player> players, List<String> args) {
        this.myPlayers = players;
        this.myArgs = args;
    }

}
