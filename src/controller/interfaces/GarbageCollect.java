package controller.interfaces;

import engine.bet.Bet;
import engine.player.Player;

import java.util.Collection;
import java.util.function.BiConsumer;

public interface GarbageCollect {

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
