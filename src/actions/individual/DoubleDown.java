package actions.individual;

import engine.bet.Bet;
import engine.dealer.Card;
import engine.player.Player;
import exceptions.ActionException;

import java.util.function.Supplier;

public class DoubleDown extends IndividualAction {

    public DoubleDown() {
        super();
    }

    @Override
    public void execute(Player player, Bet target, Supplier<Card> getCard) throws ActionException {
        if (target.getHand().getCards().size() > 2)
            throw new ActionException(this);
        target.setWager(target.getWager() * 2);
        target.acceptCard(getCard.get());
        target.setGameActive(false);
    }
}