package xml.xmlbundle;

import UI.Validation.XMLFileType;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class XMLBundleTest {

    @Test
    void testBundle() {
        XMLBundle bundle = new XMLBundle();
        bundle.addFile(new File("data/xml/deck/standard.xml"), XMLFileType.DECK);
        bundle.addFile(new File("data/xml/game/poker.xml"), XMLFileType.GAME);
        bundle.addFile(new File("data/xml/hands/pokerHands.xml"), XMLFileType.HANDS);
        bundle.addFile(new File("data/xml/players/3players.xml"), XMLFileType.PLAYERS);
        bundle.addFile(new File("data/xml/view/view.xml"), XMLFileType.VIEW);
        Set<XMLFileType> set = bundle.getMissingFiles();
        assertEquals(0, set.size());
        assertEquals(true, bundle.isComplete());
    }

}