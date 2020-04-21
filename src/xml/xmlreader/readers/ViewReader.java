package xml.xmlreader.readers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import xml.xmlreader.interfaces.ViewReaderInterface;
import xml.xmlreader.interfaces.XMLGeneratorInterface;
import xml.xmlreader.interfaces.XMLParseInterface;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewReader implements ViewReaderInterface {

    private static Document myDocument;
    private static final int ZERO = 0;

    private static final String WIDTH_TAG = "Width";
    private static final String HEIGHT_TAG = "Height";
    private static final String TITLE_TAG = "Title";
    private static final String AUTHORS_TAG = "Authors";

    private static final String LANGUAGE_TAG = "Language";
    private static final String STYLESHEET_TAG = "Stylesheet";

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
    public List<String> getLanguages() {
        NodeList nodeList = XMLParseInterface.getNodeList(myDocument, LANGUAGE_TAG);
        return parseNodeListForContent(nodeList);
    }

    @Override
    public List<String> getStylesheets() {
        NodeList nodeList = XMLParseInterface.getNodeList(myDocument, STYLESHEET_TAG);
        return parseNodeListForContent(nodeList);
    }

    private List<String> parseNodeListForContent(NodeList nodeList) {
        List<String> list = new ArrayList<>();
        for (int index = 0; index < nodeList.getLength(); index ++) {
            Node n = nodeList.item(index);
            Element e = (Element) n;
            list.add(e.getTextContent());
        }
        return list;
    }

}