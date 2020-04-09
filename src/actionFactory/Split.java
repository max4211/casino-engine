package actionFactory;

import engine.bet.Bet;
import engine.hand.Hand;
import engine.player.Player;

public class Split extends Action {

    public Split() {
        super();
        System.out.println("Created a split action");
    }

    // TODO implement split method
    @Override
    public void execute(Player player, Bet target) {
        Hand h = target.getHand();
        target.setActive(false);
    }
}
