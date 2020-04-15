package xmlreader.readers;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import xmlreader.interfaces.ViewReaderInterface;
import xmlreader.interfaces.XMLGeneratorInterface;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ViewReader implements ViewReaderInterface {

    private static Document myDocument;
    private static final int ZERO = 0;

    private static final String WIDTH_TAG = "Width";
    private static final String HEIGHT_TAG = "Height";

    public ViewReader(File file) throws IOException, SAXException, ParserConfigurationException {
        this.myDocument = XMLGeneratorInterface.createDocument(file);
//        XMLParseInterface.traverseXML(myDocument.getDocumentElement());
    }

    public ViewReader(String file) throws IOException, SAXException, ParserConfigurationException {
        this.myDocument = XMLGeneratorInterface.createDocument(new File(file));
//        XMLParseInterface.traverseXML(myDocument.getDocumentElement());
    }


    @Override
    public int getScreenWidth() {
        return Integer.parseInt(myDocument.getElementsByTagName(WIDTH_TAG).item(ZERO).getTextContent());
    }

    @Override
    public int getScreenHeight() {
        return Integer.parseInt(myDocument.getElementsByTagName(HEIGHT_TAG).item(ZERO).getTextContent());
    }
}
