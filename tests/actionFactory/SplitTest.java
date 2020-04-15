package actionFactory;

import engine.bet.Bet;
import engine.dealer.Card;
import engine.player.Player;
import exceptions.ActionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SplitTest {

    // HAPPY PATH :)
    @Test
    void testSplitNoThrow() {
        Split split = new Split();
        Player p = new Player("Guy Fieri", 1000);
        p.placeBet(100);
        Bet b = p.getNextBet();
        b.acceptCard(new Card("Diamond", 11));
        b.acceptCard(new Card("Heart", 11));
        assertDoesNotThrow(() -> split.execute(p, b, null));
    }

    // SAD PATH :(
    @Test
    void testSplitDoesThrow() {
        Split split = new Split();
        Player p = new Player("Guy Fieri", 1000);
        p.placeBet(100);
        Bet b = p.getNextBet();
        b.acceptCard(new Card("Diamond", 11));
        b.acceptCard(new Card("Heart", 10));
        assertThrows(ActionException.class, () -> split.execute(p, b, null));
    }

}