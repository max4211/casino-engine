package actionFactory;

import engine.bet.Bet;
import engine.player.Player;

public class Stay extends Action {

    public Stay() {
        super();
        System.out.println("Created a stay action");
    }

    @Override
    public void execute(Player player, Bet target) {
        target.setActive(false);
    }
}
