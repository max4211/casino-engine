package xml.xmlreader.interfaces;

import exceptions.XMLParseException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public interface XMLValidatorInterface {

    static String getMetaTag(File file) throws XMLParseException {
        try {
            Document doc = XMLGeneratorInterface.createDocument(file);
            return doc.getDocumentElement().getTagName();
        } catch (IOException | SAXException | ParserConfigurationException e) {
            throw new XMLParseException(e, file);
        }
    }

}
