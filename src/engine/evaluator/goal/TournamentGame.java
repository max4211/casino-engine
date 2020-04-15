package engine.evaluator.goal;

import engine.player.Player;

import java.util.Collection;
import java.util.List;

public class TournamentGame extends Goal {

    private Player myWinner;

    public TournamentGame(List<Player> players, List<String> args) {
        super(players, args);
    }

    @Override
    public boolean goalAchieved() {
        return this.myPlayers.size() == 1;
    }

    @Override
    public Player gameWinner() {
        this.myWinner = this.myPlayers.get(0);
        return this.myWinner;
    }
}
