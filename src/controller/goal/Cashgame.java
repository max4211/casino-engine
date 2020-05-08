package controller.goal;

import engine.player.Player;

import java.util.Collection;
import java.util.PriorityQueue;
import java.util.function.Supplier;

/**
 * An endless goal polciy, you play indefinitely, goal is to accumulate lots of money
 * @author Max Smith
 */
public class Cashgame extends Goal {

    private static final String HEADER = "CURRENT CASH GAME STANDINGS...";

    private static final String STANDINGS = "Standings:";
    private static final String NEWLINE = "\n";

    public Cashgame(Supplier<Collection<Player>> getPlayers) {
        super(getPlayers);
    }

    /**
     * Overriden implementation of the game
     * @return a string to populate the splash screen with the current goal
     */
    @Override
    public String evaluate() {
        PriorityQueue<Player> pq = sortPlayersByStack();
        return formatOutput(pq);
    }

    private String formatOutput(PriorityQueue<Player> pq) {
        StringBuilder sb = new StringBuilder();
        sb.append(HEADER+ NEWLINE);
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
