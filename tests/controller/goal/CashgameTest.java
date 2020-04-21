package controller.goal;

import engine.player.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class CashgameTest {

    class PlayerCollection {

        private Collection<Player> myPlayers;

        public PlayerCollection() {
            this.myPlayers = new ArrayList<>();
        }

        public void add(Player p) {
            this.myPlayers.add(p);
        }

        public Collection<Player> getPlayers() {
            return this.myPlayers;
        }
    }

    @Test
    void evaluate() {
        PlayerCollection pc = new PlayerCollection();
        pc.add(new Player("Max", 500));
        pc.add(new Player("Eric", 1000));
        pc.add(new Player("Robert", 400));
        pc.add(new Player("Zoe", 12012));
        Goal goal = new Cashgame(pc::getPlayers);
        String result = goal.evaluate();
        String expected = "Standings:\n" +
                "1. Zoe (12012)\n" +
                "2. Eric (1000)\n" +
                "3. Max (500)\n" +
                "4. Robert (400)\n";
        assertEquals(expected, result);
    }
}