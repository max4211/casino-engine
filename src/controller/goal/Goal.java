package controller.goal;

import engine.player.Player;

import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.Supplier;

/**
 * An abstract goal, used to monitor high level gameplay and generate splash screens for updates
 * @author Max Smith
 */
public abstract class Goal implements GoalInterface {

    private Supplier<Collection<Player>> myGetPlayers;

    public Goal(Supplier<Collection<Player>> getPlayers) {
        this.myGetPlayers = getPlayers;
    }

    protected PriorityQueue<Player> sortPlayersByStack() {
        PriorityQueue<Player> pq = new PriorityQueue<>(this.myGetPlayers.get().size(), new PlayerComparator());
        pq.addAll(this.myGetPlayers.get());
        return pq;
    }

    class PlayerComparator implements Comparator<Player> {

        /**
         * Comparator on players bankrolls, used in priority queue implementation
         * @param p1 first player
         * @param p2 second player
         * @return values depending on relative bankrolls
         */
        @Override
        public int compare(Player p1, Player p2) {
            return (int) (p2.getBankroll() - p1.getBankroll());
        }
    }



}
