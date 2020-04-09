package actionFactory;

import engine.bet.Bet;
import engine.player.Player;

public class Check extends Action {

    public Check() {
        super();
        System.out.println("Created a check action");
    }

    @Override
    public void execute(Player player, Bet target) {
        target.setActive(false);
    }
}
