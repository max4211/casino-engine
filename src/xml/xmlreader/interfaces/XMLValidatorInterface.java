package xml.xmlreader.interfaces;

import exceptions.GeneralXMLException;
import exceptions.XMLParseException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Interface to get the first tag name in an xml file for validation asssistance
 * @author Max Smith
 */
public interface XMLValidatorInterface {

    /**
     * Get the root level tag in a file
     * @param file is the file to parse
     * @return the root level tag
     * @throws XMLParseException if the file is not XML
     */
    static String getMetaTag(File file) throws XMLParseException {
        try {
            Document doc = XMLGeneratorInterface.createDocument(file);
            return doc.getDocumentElement().getTagName();
        } catch (GeneralXMLException e) {
            throw new XMLParseException(e, file);
        }
    }

}
