package xml.xmlreader.readers;

import Utility.handbundle.HandBundle;
import exceptions.GeneralXMLException;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HandReaderTest {

    @Test
    void getWinningHands() throws GeneralXMLException {
        String file = "data/xml/hands/testgeneralhands.xml";
        HandReader myReader = new HandReader(file);
        List<HandBundle> bundles = myReader.getWinningHands();
        List<HandBundle> expected = new ArrayList<HandBundle>(List.of(
                new HandBundle("XCardSumY", "2, 21", "1.5", "Blackjack")
        ));
        assertEquals(bundles.get(0).getName(), expected.get(0).getName());
        assertEquals(bundles.get(0).getMultiplier(), expected.get(0).getMultiplier());
        assertEquals(bundles.get(0).getParams(), expected.get(0).getParams());
        assertEquals(bundles.get(0).getViewName(), expected.get(0).getViewName());
    }

    @Test
    void getLosingHands() {
    }
}