package actions.individual;

import engine.bet.Bet;
import engine.dealer.Card;
import engine.player.Player;

import java.util.function.Supplier;

public class DoubleDown extends IndividualAction {

    public DoubleDown() {
        super();
        System.out.println("Created a double down action");
    }

    @Override
    public void execute(Player player, Bet target, Supplier<Card> getCard) {
        target.setWager(target.getWager() * 2);
        target.acceptCard(getCard.get());
        target.setGameActive(false);
    }
}
