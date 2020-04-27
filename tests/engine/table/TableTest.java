package engine.table;

import Utility.StringPair;
import engine.bet.Bet;
import engine.dealer.Dealer;
import engine.deck.Deck;
import engine.deck.RemoveDeck;
import engine.player.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    private Table createTable(Player p1, Player p2, Player p3) {
        Collection<Player> players = new ArrayList<>(List.of(
                p1, p2, p3
        ));
        List<StringPair> cards = new ArrayList<StringPair>(List.of(
                new StringPair("Hearts", "5")
        ));
        Deck deck = new RemoveDeck(cards);
        Dealer dealer = new Dealer(deck);
        double min = 100;
        double max = 200;
        return new Table(players, dealer, min, max);
    }

    @Test
    void getNextPlayer() {
        Player p1 = new Player("Eric", 500);
        Bet b1 = new Bet(10);
        p1.placeBet(b1);

        Player p2 = new Player("Max", 500);
        Bet b2 = new Bet(10);
        p2.placeBet(b2);

        Player p3 = new Player("Robert", 500);
        Bet b3 = new Bet(10);
        p3.placeBet(b3);

        Table table = createTable(p1, p2, p3);

        assertEquals("Eric", table.getNextPlayer().getName());
        b1.setRoundActive(false);

        assertEquals("Max", table.getNextPlayer().getName());
        b2.setRoundActive(false);

        assertEquals("Robert", table.getNextPlayer().getName());
        assertEquals(table.hasActivePlayers(), true);

    }
}