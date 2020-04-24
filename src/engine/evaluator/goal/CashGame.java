package engine.evaluator.goal;

import engine.player.Player;

import java.util.Collection;
import java.util.List;

public class CashGame extends Goal {

    public CashGame(List<Player> players, List<String> args) {
        super(players, args);
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
