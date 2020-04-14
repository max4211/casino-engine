package xmlreader;

import Utility.Pair;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ViewReader implements ViewReaderInterface{

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
