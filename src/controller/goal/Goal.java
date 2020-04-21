package controller.goal;

import engine.bet.Bet;
import engine.evaluator.handevaluator.HandEvaluator;
import engine.player.Player;

import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.Supplier;

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

        @Override
        public int compare(Player p1, Player p2) {
            return (int) (p2.getBankroll() - p1.getBankroll());
        }
    }



}
