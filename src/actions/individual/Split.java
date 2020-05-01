package actions.individual;

import engine.bet.Bet;
import engine.dealer.Card;
import engine.hand.PlayerHand;
import engine.player.Player;
import exceptions.ActionException;

import java.util.function.Supplier;

/**
 * Splits the players cards into multiple, and matches the wager on each bet
 */
public class Split extends IndividualAction {

    public Split() {
        super();
    }

    private boolean exactlyTwoCards(PlayerHand h) {
        return h.getCards().size() == 2;
    }

    private boolean sameCards(PlayerHand h) {
        if (exactlyTwoCards(h)) {
            return h.getCards().get(0).getValue() == h.getCards().get(1).getValue();
        }
        return false;
    }

    private boolean canSplit(PlayerHand h) {
        return sameCards(h) && exactlyTwoCards(h);
    }

    /**
     * Duplicates the bet and adds a new bet
     * @param player is the player who has the bet
     * @param target bet that the action executes on
     * @param getCard is a lambda to get a card (may be needed)
     * throws ActionException when the split is not possible (same cards, more than two)
     */
    @Override
    public void execute(Player player, Bet target, Supplier<Card> getCard) {
        if (canSplit(target.getHand())) {
            Bet bet = new Bet(target.getWager());
            bet.acceptCard(target.getHand().getCards().get(0));
        } else {
            throw new ActionException(this);
        }
    }
}

