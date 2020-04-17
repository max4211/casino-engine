package actions.group;

import engine.bet.Bet;
import engine.player.Player;

public interface GroupActionInterface {

    void execute(Player p, Bet b, WagerSelector selectWager, int min, int max);

}
