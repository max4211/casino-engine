package controller.goal;

import engine.player.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TournamentTest {

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
        pc.add(new Player("Robert", 0));
        pc.add(new Player("Zoe", 0));
        Tournament goal = new Tournament(pc::getPlayers);
        String result = goal.evaluate();
        String expected = "CURRENT TOURNAMENT STANDINGS...\n" +
                "Active:\n" +
                "1. Eric (1000)\n" +
                "2. Max (500)\n" +
                "Losers:\n" +
                "1. Robert (0)\n" +
                "2. Zoe (0)\n";
        assertEquals(expected, result);
    }
}