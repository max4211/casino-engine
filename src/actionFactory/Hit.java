package actionFactory;

import engine.bet.Bet;
import engine.player.Player;

public class Hit extends Action {

    public Hit() {
        super();
        System.out.println("Created a hit action");
    }

    @Override
    public void execute(Player player, Bet target) {
        target.setNeedsCard(true);
    }
}
