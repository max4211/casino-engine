package actionFactory;

import engine.bet.Bet;
import engine.player.Player;

public interface ActionInterface {

    /**
     * Actions execute on a specific bet
     * @param target bet that the action executes on
     * @param player is the player who has the bet
     */
    void execute(Player player, Bet target);

}
