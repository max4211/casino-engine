package actions.group;

import engine.bet.Bet;
import engine.player.Player;
import exceptions.ActionException;

import java.util.function.Consumer;

public class Call extends GroupAction {

    public Call() {
        super();
    }

    @Override
    public void execute(Player p, Bet b,
                        WagerSelector selectWager, Consumer<Double> setTableBet, Consumer<Bet> activatePlayers,
                        double tableMin, double tableMax, double currentBet) throws ActionException {
        if (currentBet == 0)
            throw new ActionException(this);
        b.setWager(b.getWager() + currentBet);
        b.setRoundActive(false);
    }
}
