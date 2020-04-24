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
        ViewReader vr = new ViewReader("data/xml/view/view.xml");
        List<String> result = vr.getLanguages();
        List<String> expected = new ArrayList<String>(List.of(
            "English", "Spanish"
        ));
        for (int i = 0; i < result.size(); i ++)
            assertEquals(expected.get(i), result.get(i));
    }

    @Test
    void getStylesheets() throws GeneralXMLException {
        ViewReader vr = new ViewReader("data/xml/view/view.xml");
        List<String> result = vr.getStylesheets();
        List<String> expected = new ArrayList<String>(List.of(
                "Light", "Dark", "Coral"
        ));
        for (int i = 0; i < result.size(); i ++)
            assertEquals(expected.get(i), result.get(i));
    }
}