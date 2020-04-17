package actions.group;

import engine.bet.Bet;
import engine.player.Player;

public class BetAction extends GroupAction {

    public BetAction() {
        super();
        System.out.println("Created a bet action");
    }

    @Override
    public void execute(Player p, Bet b, WagerSelector selectWager, int min, int max) {
        int wager = selectWager.getBet(min, max);
    }
}
