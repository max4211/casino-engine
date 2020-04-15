package xmlreader.readers;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import xmlreader.interfaces.ViewReaderInterface;
import xmlreader.interfaces.XMLGeneratorInterface;
import xmlreader.interfaces.XMLParseInterface;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ViewReader implements ViewReaderInterface {

    private static Document myDocument;
    private static final int ZERO = 0;

    private static final String WIDTH_TAG = "Width";
    private static final String HEIGHT_TAG = "Height";
    private static final String TITLE_TAG = "Title";
    private static final String AUTHORS_TAG = "Authors";
    private static final String LANGUAGE_TAG = "Language";

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
        return Integer.parseInt(XMLParseInterface.getSingleTag(myDocument, WIDTH_TAG));
    }

    @Override
    public int getScreenHeight() {
        return Integer.parseInt(XMLParseInterface.getSingleTag(myDocument, HEIGHT_TAG));
    }

    @Override
    public String getTitle() {
        return XMLParseInterface.getSingleTag(myDocument, TITLE_TAG);
    }

    @Override
    public String getAuthors() {
        return XMLParseInterface.getSingleTag(myDocument, AUTHORS_TAG);
    }

    @Override
    public String getLanguage() {
        return XMLParseInterface.getSingleTag(myDocument, LANGUAGE_TAG);
    }
}
