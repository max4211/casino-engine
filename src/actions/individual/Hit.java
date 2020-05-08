package actions.individual;

import engine.bet.Bet;
import engine.dealer.Card;
import engine.player.Player;

import java.util.function.Supplier;

/**
 * Hit action uses the consumer to accept another card from the dealer
 * @author Max Smith
 */
public class Hit extends IndividualAction {

    public Hit() {
        super();
    }

    /**
     * Uses lambad from dealer to get card (no knowledge of dealer obejct)
     * @param player is the player who has the bet
     * @param target bet that the action executes on
     * @param getCard is a lambda to get a card (may be needed)
     */
    @Override
    public void execute(Player player, Bet target, Supplier<Card> getCard) {
        target.acceptCard(getCard.get());
    }
}
