package actions.group;

import engine.bet.Bet;
import engine.player.Player;

import java.util.function.Consumer;

public class Call extends GroupAction {

    public Call() {
        super();
        System.out.println("Created a call action");
    }

    @Override
    public void execute(Player p, Bet b,
                        WagerSelector selectWager, Consumer<Double> setTableBet, Consumer<Bet> activatePlayers,
                        double tableMin, double tableMax, double currentBet) {
        b.setWager(currentBet);
        b.setRoundActive(false);
    }
}
