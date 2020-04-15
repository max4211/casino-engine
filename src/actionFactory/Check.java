package actionFactory;

import engine.bet.Bet;
import engine.dealer.Card;
import engine.dealer.Dealer;
import engine.player.Player;

import java.util.function.Supplier;

public class Check extends Action {

    public Check() {
        super();
        System.out.println("Created a check action");
    }

    @Override
    public void execute(Player player, Bet target, Supplier<Card> getCard) {
        target.setActive(false);
    }

}
