package GameView.NodeViews;

import engine.player.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerViewTest {

    @Test
    void testIDEquivalence() {
        Player p = new Player("Robert Duvall", 10000);
        PlayerView pv = new PlayerView("Robert Duvall", p.getID(), 1000000);
        assertEquals(pv.hasSameID(p.getID()), p.getID());
        assertEquals(p.getID(), p.hashCode());
    }

}