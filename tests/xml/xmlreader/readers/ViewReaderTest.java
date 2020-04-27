package xml.xmlreader.readers;

import exceptions.GeneralXMLException;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ViewReaderTest {

    @Test
    void getLanguages() throws GeneralXMLException {
        ViewReader vr = new ViewReader("data/xml/good/VIEW_basicview.xml");
        List<String> result = vr.getLanguages();
        List<String> expected = new ArrayList<String>(List.of(
            "English", "Spanish"
        ));
        assertEquals(expected, result);
    }

    @Test
    void getStylesheets() throws GeneralXMLException {
        ViewReader vr = new ViewReader("data/xml/good/VIEW_basicview.xml");
        List<String> result = vr.getStylesheets();
        List<String> expected = new ArrayList<String>(List.of(
                "Pastel", "Light", "Dark", "Empty"
        ));
        assertEquals(expected, result);
    }
}