package actionFactory;

import engine.bet.Bet;
import engine.player.Player;

public class DoubleDown extends Action {

    public DoubleDown() {
        super();
        System.out.println("Created a double down action");
    }

    @Override
    public void execute(Player player, Bet target) {
        target.setActive(false);
        target.setNeedsCard(true);
        target.setWager(2*target.getWager());
    }
}
