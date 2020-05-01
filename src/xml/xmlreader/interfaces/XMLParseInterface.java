package xml.xmlreader.interfaces;

import Utility.StringPair;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Interface to assist with XML parsing
 * @author Max Smith
 */
public interface XMLParseInterface {

    /**
     * Get a list  of nodes from a document and tag
     * @param doc the document to parse
     * @param tag the tag we are searching for
     * @return
     */
    static NodeList getNodeList(Document doc, String tag) {
        return doc.getElementsByTagName(tag);
    }

    /**
     * Get a single elements text content
     * @param e the element to fetch
     * @param tag the tag within the element we want to index
     * @return
     */
    static String getElement(Element e, String tag) {
        return e.getElementsByTagName(tag).item(0).getTextContent();
    }

    /**
     * Get a single element's content from a node (cast)
     * @param n Node to parse
     * @param tag is the tag searching for wihtin the element
     * @return
     */
    static String getElement(Node n, String tag) {
        Element e = (Element) n;
        return e.getElementsByTagName(tag).item(0).getTextContent();
    }

    /**
     * Get a single tag from a document's text content
     * @param d document to parse
     * @param tag tag we are searching for
     * @return
     */
    static String getSingleTag(Document d, String tag) {
        final int ZERO = 0;
        return d.getElementsByTagName(tag).item(ZERO).getTextContent();
    }

    /**
     * Get a collection of XML String objects
     * @param document we are searching
     * @param metatag top level tag
     * @param subtag second level tag
     * @return a collection of strings
     */
    static Collection<String> getXMLCollection(Document document, String metatag, String subtag) {
        Collection<String> list = new ArrayList<>();
        NodeList handNodeList = document.getElementsByTagName(metatag);
        for (int index = 0; index < handNodeList.getLength(); index ++) {
            Node handNode = handNodeList.item(index);
            Element handElement = (Element) handNode;
            String name = getElement(handElement, subtag);
            list.add(name);
        }
        return list;
    }

    /**
     * Parse a list of attribute/element string content
     * @param document we are parsing
     * @param tag tag we are searching for
     * @return
     */
    static List<String> getXMLList(Document document, String tag) {
        List<String> list = new ArrayList<>();
        NodeList handNodeList = document.getElementsByTagName(tag);
        for (int index = 0; index < handNodeList.getLength(); index ++) {
            Node handNode = handNodeList.item(index);
            Element handElement = (Element) handNode;
            String name = handElement.getTextContent();
            list.add(name);
        }
        return list;
    }

    /**
     * Get string pairs of objects
     * @param nodeList is subsection of document (e.g all Card tags)
     * @param firstTag is the first tag we are searching for (e.g. suit)
     * @param secondTag second tag we are searching for in string pair (e.g. value)
     * @return
     */
    static List<StringPair> parseStringPair(NodeList nodeList, String firstTag, String secondTag) {
        List<StringPair> list = new ArrayList<>();
        for (int index = 0; index < nodeList.getLength(); index ++) {
            Node cardNode = nodeList.item(index);
            Element cardElement = (Element) cardNode;
            String suit = XMLParseInterface.getElement(cardElement, firstTag);
            String value = XMLParseInterface.getElement(cardElement, secondTag);
            list.add(new StringPair(suit, value));
        }
        return list;
    }

    /**
     * Debug method to traverse entire XMl document recursively
     * @param node starging node (liley root element)
     */
    static void traverseXML(Node node) {
        System.out.println(node.getNodeName());
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i ++) {
            Node curNode = nodeList.item(i);
            if (curNode.getNodeType() == Node.ELEMENT_NODE)
                traverseXML(curNode);
        }
    }

}
