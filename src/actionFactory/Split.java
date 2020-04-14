package actionFactory;

import engine.bet.Bet;
import engine.hand.PlayerPlayerHand;
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
        PlayerPlayerHand h = target.getHand();
        if (exactlyTwoCards(h) && sameCards(h)) {
            // TODO implement split
            target.setActive(false);
        } else {
            System.out.println("could not split hand");
            throw new ActionException();
        }

    }

    private boolean exactlyTwoCards(PlayerPlayerHand h) {
        return h.getCards().size() == 2;
    }

    private boolean sameCards(PlayerPlayerHand h) {
        if (exactlyTwoCards(h)) {
            if (h.getCards().get(0).getValue() == h.getCards().get(1).getValue()) {
                return true;
            }
        }
        return false;
    }
}

