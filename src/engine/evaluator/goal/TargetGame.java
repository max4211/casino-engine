package engine.evaluator.goal;

import engine.player.Player;

import java.util.Collection;
import java.util.List;

public class TargetGame extends Goal {

    private double myTarget;
    private Player myWinner;

    public TargetGame(List<Player> players, List<String> args) {
        super(players, args);
        this.myTarget = Double.parseDouble(args.get(0));
    }

    @Override
    public boolean goalAchieved() {
        for (Player p: this.myPlayers) {
            if (p.getBankroll() >= myTarget) {
                this.myWinner = p;
                return true;
            }
        }
        return false;
    }

    @Override
    public Player gameWinner() {
        return this.myWinner;
    }
}
