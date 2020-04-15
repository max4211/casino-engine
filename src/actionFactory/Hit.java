package actionFactory;

import engine.bet.Bet;
import engine.dealer.Card;
import engine.dealer.Dealer;
import engine.player.Player;

import java.util.function.Supplier;

public class Hit extends Action {

    public Hit() {
        super();
        System.out.println("Created a hit action");
    }

    @Override
    public void execute(Player player, Bet target, Supplier<Card> getCard) {
        target.acceptCard(getCard.get());
    }
}
