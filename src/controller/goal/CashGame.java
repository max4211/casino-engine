package controller.goal;

import engine.player.Player;

import java.util.Collection;
import java.util.PriorityQueue;
import java.util.function.Supplier;

public class CashGame extends Goal {

    private static final int ZERO = 0;
    private static final String STANDINGS = "Standings:";
    private static final String NEWLINE = "\n";

    public CashGame(Supplier<Collection<Player>> getPlayers) {
        super(getPlayers);
    }

    @Override
    public String evaluate() {
        PriorityQueue<Player> pq = sortPlayersByStack();
        return formatOutput(pq);
    }

    private String formatOutput(PriorityQueue<Player> pq) {
        StringBuilder sb = new StringBuilder();
        sb.append(STANDINGS + NEWLINE);
        int count = 1;
        while (!(pq.isEmpty())) {
            Player p = pq.poll();
            sb.append(singleLine(count, p.getName(), p.getBankroll()));
            count ++;
        }
        return sb.toString();
    }

    private String singleLine(int index, String name, double bankroll) {
        return String.format("%d. %s (%d)%s", index, name, (int)bankroll, NEWLINE);
    }

}
