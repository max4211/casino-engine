package controller.interfaces;

import engine.bet.Bet;
import engine.player.Player;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface GarbageCollect {

    static void clearLosers(Collection<Player> players, BiConsumer<Integer, Integer> removeViewBet, Consumer<Integer> setLoser) {
        for (Player p: players) {
            for (Bet b: p.getActiveBets()) {
                if (b.getHand().isLoser()) {
                    removeViewBet.accept(p.getID(), b.getID());
                    setLoser.accept(b.getID());
                }
            }
        }
    }

}
