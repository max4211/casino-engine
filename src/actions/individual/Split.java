package actions.individual;

import engine.bet.Bet;
import engine.dealer.Card;
import engine.hand.PlayerHand;
import engine.player.Player;
import exceptions.ActionException;

import java.util.function.Supplier;

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

