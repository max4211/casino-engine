package actions.group;

import engine.bet.Bet;
import engine.player.Player;

import java.util.function.Consumer;

public class Fold extends GroupAction {

    public Fold() {
        super();
        System.out.println("Created a fold action");
    }

    @Override
    public void execute(Player p, Bet b,
                        WagerSelector selectWager, Consumer<Double> setTableBet, Consumer<Bet> activatePlayers,
                        double tableMin, double tableMax, double currentBet) {
        b.getHand().setLoser(true);
        b.setGameActive(false);
    }
}
