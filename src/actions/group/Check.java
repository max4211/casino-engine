package actions.group;

import engine.bet.Bet;
import engine.player.Player;

import java.util.function.Consumer;

public class Check extends GroupAction {

    public Check() {
        super();
        System.out.println("Created a check action");
    }

    @Override
    public void execute(Player p, Bet b,
                        WagerSelector selectWager, Consumer<Double> setTableBet, Consumer<Bet> activatePlayers,
                        double tableMin, double tableMax, double currentBet){
        b.setRoundActive(false);
    }
}
