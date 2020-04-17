package actions.individual;

import engine.bet.Bet;
import engine.dealer.Card;
import engine.player.Player;

import java.util.function.Supplier;

public class Check extends IndividualAction {

    public Check() {
        super();
        System.out.println("Created a check action");
    }

    @Override
    public void execute(Player player, Bet target, Supplier<Card> getCard) {
        target.setGameActive(false);
    }

}
