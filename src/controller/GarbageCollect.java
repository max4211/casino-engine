package controller;

import engine.bet.Bet;
import engine.player.Player;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;

public interface GarbageCollect {

    static void clearLosers(Collection<Player> players, BiConsumer<Integer, Integer> consumer) {
        for (Player p: players) {
            for (Bet b: p.getBets()) {
                if (b.getHand().isLoser()) {
                    System.out.println("found a loser hand, garbage collecting");
                    consumer.accept(p.getID(), b.getID());
                }
            }
        }
    }

}
