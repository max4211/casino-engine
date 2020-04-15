package xmlreader.interfaces;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Collection;

public interface XMLParseInterface {

    static NodeList getNodeList(Document doc, String tag) {
        return doc.getElementsByTagName(tag);
    }

    static String getElement(Element e, String tag) {
        return e.getElementsByTagName(tag).item(0).getTextContent();
    }

    static String getSingleTag(Document d, String tag) {
        final int ZERO = 0;
        return d.getElementsByTagName(tag).item(ZERO).getTextContent();
    }

    static Collection<String> getXMLList(Document document, String metatag, String subtag) {
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
