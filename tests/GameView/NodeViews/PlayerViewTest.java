package GameView.NodeViews;

import engine.player.Player;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerViewTest {

    @Test
    void testIDEquivalence() {
        JFXPanel jfxPanel;
        jfxPanel = new JFXPanel();

        Player p = new Player("Robert", 10000);
        PlayerView pv = new PlayerView("Robert", p.getID(), 1000000);
        assertEquals(true, pv.hasSameID(p.getID()));

        assertEquals(p.getID(), p.hashCode());

        PlayerView pv2 = new PlayerView("Eric", -1000, 200);
        assertEquals(true, pv2.hasSameID(-1000));
        assertEquals(false, pv2.hasSameID(-10));

        PlayerView pv3 = new PlayerView("Eric", 0, 200);
        assertEquals(true, pv3.hasSameID(0));
        assertEquals(false, pv3.hasSameID(10));
    }
}