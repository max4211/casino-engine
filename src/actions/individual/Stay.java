package actions.individual;

import engine.bet.Bet;
import engine.dealer.Card;
import engine.player.Player;

import java.util.function.Supplier;

public class Stay extends IndividualAction {

    public Stay() {
        super();
    }

    @Override
    public void execute(Player player, Bet target, Supplier<Card> getCard) {
        target.setGameActive(false);
    }
}
