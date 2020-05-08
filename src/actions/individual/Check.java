package actions.individual;

import engine.bet.Bet;
import engine.dealer.Card;
import engine.player.Player;

import java.util.function.Supplier;

/**
 * Check actions set the active status of the player to false, passing action on to the next player
 * @author Max Smith
 */
public class Check extends IndividualAction {

    public Check() {
        super();
    }

    /**
     * Updates the players active status to false
     * @param player is the player who has the bet
     * @param target bet that the action executes on
     * @param getCard is a lambda to get a card (may be needed)
     */
    @Override
    public void execute(Player player, Bet target, Supplier<Card> getCard) {
        target.setGameActive(false);
    }

}
