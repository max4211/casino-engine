package controller.interfaces;

import engine.bet.Bet;
import engine.player.Player;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;

public interface GarbageCollect {

    static void clearLosers(Collection<Player> players, BiConsumer<Integer, Integer> removeViewBet) {
        for (Player p: players) {
            for (Bet b: p.getActiveBets()) {
                if (b.getHand().isLoser()) {
                    removeViewBet.accept(p.getID(), b.getID());
                }
            }
        }
    }

}
