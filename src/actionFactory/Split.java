package actionFactory;

import engine.bet.Bet;
import engine.hand.Hand;
import engine.player.Player;
import exceptions.ActionException;

public class Split extends Action {

    public Split() {
        super();
        System.out.println("Created a split action");
    }

    // TODO implement split method
    @Override
    public void execute(Player player, Bet target) {
        Hand h = target.getHand();
        if (exactlyTwoCards(h) && sameCards(h)) {
            // TODO implement split
            target.setActive(false);
        } else {
            System.out.println("could not split hand");
            throw new ActionException();
        }

    }

    private boolean exactlyTwoCards(Hand h) {
        return h.getCards().size() == 2;
    }

    private boolean sameCards(Hand h) {
        if (exactlyTwoCards(h)) {
            if (h.getCards().get(0).getValue() == h.getCards().get(1).getValue()) {
                return true;
            }
        }
        return false;
    }
}

