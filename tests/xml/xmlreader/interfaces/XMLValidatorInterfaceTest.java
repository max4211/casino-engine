package xml.xmlreader.interfaces;

import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class XMLValidatorInterfaceTest {

    @Test
    void getMetaTag() throws ParserConfigurationException, SAXException, IOException {
        File file = new File("data/xml/deck/cs308.xml");
        String result = XMLValidatorInterface.getMetaTag(file);
        String expected = "Deck";
        assertEquals(expected, result);
    }
}