package actionFactory;

import engine.bet.Bet;
import engine.dealer.Card;
import engine.dealer.Dealer;
import engine.player.Player;

import java.util.function.Supplier;

public class Stay extends Action {

    public Stay() {
        super();
    }

    @Override
    public void execute(Player player, Bet target, Supplier<Card> getCard) {
        target.setActive(false);
    }
}
