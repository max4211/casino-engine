package engine.evaluator.goal;

import engine.player.Player;

import java.util.Collection;
import java.util.List;

public abstract class Goal implements GoalInterface {

    protected final List<Player> myPlayers;
    protected final List<String> myArgs;
    protected Player myWinner;

    public Goal(List<Player> players, List<String> args) {
        this.myPlayers = players;
        this.myArgs = args;
    }

}
