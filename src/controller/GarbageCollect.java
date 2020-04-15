package controller;

import engine.bet.Bet;
import engine.player.Player;

import java.util.List;
import java.util.function.BiConsumer;

public interface GarbageCollect {

    static void clearLosers(List<Player> players, BiConsumer<Integer, Integer> consumer) {
        for (Player p: players) {
            for (Bet b: p.getBets()) {
                if (b.getHand().isLoser()) {
                    consumer.accept(p.getID(), b.getID());
                }
            }
        }
    }

}
