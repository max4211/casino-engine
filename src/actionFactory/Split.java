package actionFactory;

import engine.bet.Bet;
import engine.dealer.Card;
import engine.hand.PlayerHand;
import engine.player.Player;

import java.util.function.Supplier;

public class Split extends Action {

    public Split() {
        super();
    }

//    // TODO implement split method
//    @Override
//    public void execute(Player player, Bet target, Dealer dealer) {
//        PlayerPlayerHand h = target.getHand();
//        if (exactlyTwoCards(h) && sameCards(h)) {
//            // TODO implement split
//            target.setActive(false);
//        } else {
//            throw new ActionException();
//        }
//
//    }

    private boolean exactlyTwoCards(PlayerHand h) {
        return h.getCards().size() == 2;
    }

    private boolean sameCards(PlayerHand h) {
        if (exactlyTwoCards(h)) {
            if (h.getCards().get(0).getValue() == h.getCards().get(1).getValue()) {
                return true;
            }
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
            // TODO - throw cannot split exception
        }
    }
}

