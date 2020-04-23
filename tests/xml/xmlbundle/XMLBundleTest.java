package xml.xmlbundle;

import UI.Validation.XMLFile;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class XMLBundleTest {

    @Test
    void testBundle() {
        XMLBundle bundle = new XMLBundle();
        bundle.addFile(new File("data/xml/deck/standard.xml"), XMLFile.DECK);
        bundle.addFile(new File("data/xml/game/poker.xml"), XMLFile.GAME);
        bundle.addFile(new File("data/xml/hands/pokerHands.xml"), XMLFile.HANDS);
        bundle.addFile(new File("data/xml/players/3players.xml"), XMLFile.PLAYERS);
        bundle.addFile(new File("data/xml/view/view.xml"), XMLFile.VIEW);
        Set<XMLFile> set = bundle.getMissingFiles();
        assertEquals(0, set.size());
        assertEquals(true, bundle.isComplete());
    }

}