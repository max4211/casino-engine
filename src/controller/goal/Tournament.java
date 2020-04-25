package controller.goal;

import engine.player.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Supplier;

public class Tournament extends Goal {

    private static final String HEADER = "CURRENT TOURNAMENT STANDINGS...";

    private static final int ZERO = 0;
    private static final String WINNERS = "Active:";
    private static final String LOSERS = "Losers:";
    private static final String NEWLINE = "\n";

    public Tournament(Supplier<Collection<Player>> getPlayers) {
        super(getPlayers);
    }

    @Override
    public String evaluate() {
        PriorityQueue<Player> pq = sortPlayersByStack();
        List<Player> myActives = getActivePlayers(pq);
        List<Player> myLosers = getLosingPlayers(pq);
        return formatOutput(myActives, myLosers);
    }

    private String formatOutput(List<Player> myActives, List<Player> myLosers) {
        StringBuilder sb = new StringBuilder();
        sb.append(HEADER + NEWLINE);
        sb.append(WINNERS + NEWLINE);
        for (int i = 0; i < myActives.size(); i ++)
            sb.append(singleLine(i+1, myActives.get(i).getName(), myActives.get(i).getBankroll()));
        sb.append(LOSERS + NEWLINE);
        for (int i = 0; i < myLosers.size(); i ++)
            sb.append(singleLine(i+1, myLosers.get(i).getName(), myLosers.get(i).getBankroll()));
        return sb.toString();
    }

    private String singleLine(int index, String name, double bankroll) {
        return String.format("%d. %s (%d)%s", index, name, (int)bankroll, NEWLINE);
    }

    private List<Player> getActivePlayers(PriorityQueue<Player> pq) {
        List<Player> list = new ArrayList<>();
        while (stillActive(pq)) {
            Player p = pq.poll();
            if (p.getBankroll() > 0)
                list.add(p);
        }
        return list;
    }

    private boolean stillActive(PriorityQueue<Player> pq) {
        return (!(pq.isEmpty()) && pq.peek().getBankroll() > ZERO);
    }


    private List<Player> getLosingPlayers(PriorityQueue<Player> pq) {
        List<Player> list = new ArrayList<>();
        while (!(pq.isEmpty())) {
            Player p = pq.poll();
            if (p.getBankroll() <= ZERO)
                list.add(p);
        }
        return list;
    }

}
