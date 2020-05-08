package actions.individual;

import engine.bet.Bet;
import engine.dealer.Card;
import engine.player.Player;

import java.util.function.Supplier;

/**
 * A stay action does nothing to the bet, and sends it on to the next player
 * @author Max Smith
 */
public class Stay extends IndividualAction {

    public Stay() {
        super();
    }

    /**
     * Stays for the user (no change to wager or cards, updates flag status)
     * @param player is the player who has the bet
     * @param target bet that the action executes on
     * @param getCard is a lambda to get a card (may be needed)
     */
    @Override
    public void execute(Player player, Bet target, Supplier<Card> getCard) {
        target.setGameActive(false);
    }
}
