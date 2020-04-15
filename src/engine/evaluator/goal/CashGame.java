package engine.evaluator.goal;

import engine.player.Player;

import java.util.Collection;
import java.util.List;

public class CashGame extends Goal {

    private double myTarget;

    public CashGame(List<Player> players, List<String> args) {
        super(players, args);
        this.myTarget = Double.parseDouble(args.get(0));
    }

    @Override
    public boolean goalAchieved() {
        return false;
    }

    @Override
    public Player gameWinner() {
        return this.myWinner;
    }
}
