package actions.group;

import engine.bet.Bet;
import engine.player.Player;
import exceptions.ActionException;

import java.util.function.Consumer;

public class Raise extends GroupAction {

    public Raise() {
        super();
    }

    @Override
    public void execute(Player p, Bet b,
                        WagerSelector selectWager, Consumer<Double> setTableBet, Consumer<Bet> activatePlayers,
                        double tableMin, double tableMax, double currentBet) throws ActionException {
        if (currentBet == 0)
            throw new ActionException(this);
        double wager = selectWager.getBet(currentBet, tableMax);

        b.setWager(b.getWager() + wager);
        b.setRoundActive(false);
        activatePlayers.accept(b);
        setTableBet.accept(wager);

    }
}
