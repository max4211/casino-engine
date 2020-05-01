package controller.interfaces;

import engine.bet.Bet;
import engine.player.Player;

import java.util.Collection;
import java.util.function.BiConsumer;

/**
 * Static interface to garbage collect hands at the end of a round
 * Leveraged by the contrller
 * @author Max Smith
 */
public interface GarbageCollect {

    /**
     * Uses several funtional interfaces to modify tags in bets and update their view rendering
     * @param players is a collection of players at the table
     * @param removeViewBet is a fucntional interface to remove the bet form the view (now deprecated, now color)
     * @param setLoser functional interface to color the bet as a loser in the view
     */
    static void clearLosers(Collection<Player> players, BiConsumer<Integer, Integer> removeViewBet, BiConsumer<Integer, Integer> setLoser) {
        for (Player p: players) {
            for (Bet b: p.getActiveBets()) {
                if (b.getHand().isLoser()) {
//                    removeViewBet.accept(p.getID(), b.getID());
                    setLoser.accept(p.getID(), b.getID());
                }
            }
        }
    }
}
