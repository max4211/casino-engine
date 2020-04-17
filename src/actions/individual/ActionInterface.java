package actions.individual;

import engine.bet.Bet;
import engine.dealer.Card;
import engine.player.Player;

import java.util.function.Supplier;

public interface ActionInterface {

    /**
     * Actions execute on a specific bet
     * @param target bet that the action executes on
     * @param player is the player who has the bet
     * @param getCard is a lambda to get a card (may be needed)
     */
    void execute(Player player, Bet target, Supplier<Card> getCard);

}
