package actionFactory;

import engine.bet.Bet;
import engine.dealer.Card;
import engine.dealer.Dealer;
import engine.player.Player;

import java.util.function.Supplier;

public class DoubleDown extends Action {

    public DoubleDown() {
        super();
        System.out.println("Created a double down action");
    }

    @Override
    public void execute(Player player, Bet target, Supplier<Card> getCard) {
        target.setWager(target.getWager() * 2);
        target.acceptCard(getCard.get());
        target.setActive(false);
    }
}
