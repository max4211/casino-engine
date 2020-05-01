package actions.individual;

import engine.bet.Bet;
import engine.dealer.Card;
import engine.player.Player;
import exceptions.ActionException;

import java.util.function.Supplier;

/**
 * Double down modifies the players bet and accepts another card
 */
public class DoubleDown extends IndividualAction {

    public DoubleDown() {
        super();
    }

    /**
     * Doubles the players bet and accepts another card (via consumer)
     * @param player is the player who has the bet
     * @param target bet that the action executes on
     * @param getCard is a lambda to get a card (may be needed)
     * @throws ActionException if the player cannot double down (hardcoded, more than 2 cards in hand)
     */
    @Override
    public void execute(Player player, Bet target, Supplier<Card> getCard) throws ActionException {
        if (target.getHand().getCards().size() > 2)
            throw new ActionException(this);
        target.setWager(target.getWager() * 2);
        target.acceptCard(getCard.get());
        target.setRoundActive(false);
    }
}
