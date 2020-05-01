package xml.xmlreader.readers;

import exceptions.GeneralXMLException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import xml.xmlreader.interfaces.LobbyReaderInterface;
import xml.xmlreader.interfaces.XMLGeneratorInterface;
import xml.xmlreader.interfaces.XMLParseInterface;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Xml Reader for lobby files with appropriate getters
 * @author Max Smith
 */
public class LobbyReader implements LobbyReaderInterface  {

    private static Document myDocument;

    private static final String LOBBY_STYLESHEET = "Stylesheet";
    private static final String LANGUAGE_TAG = "Language";
    private static final String ERROR_STYLESHEET = "ErrorStylesheet";
    private static final String ICON_PROPERTIES = "IconProperties";

    private static final String BUNDLE_TAG = "Bundle";
    private static final String NAME_TAG = "Name";
    private static final String ICON_TAG = "Icon";

    private static final String WIDTH_TAG = "Width";
    private static final String HEIGHT_TAG = "Height";

    private static final String DECK_TAG = "Deck";
    private static final String GAME_TAG = "Game";
    private static final String HAND_TAG = "Hands";
    private static final String PLAYER_TAG = "Players";
    private static final String VIEW_TAG = "View";

    private static final String FILES_STATUS_BUNDLE = "FilesStatusBundle";
    private static final String FILES_ICON_BUNDLE = "FilesIconBundle";

    /**
     * Create the reader from string
     * @param file is the file pointer to make the reader from
     * @throws GeneralXMLException if the GeneratorInterface cannot parse the file (non xml)
     */
    public LobbyReader(File file) throws GeneralXMLException {
        myDocument = XMLGeneratorInterface.createDocument(file);
    }

    /**
     * Create the reader from string
     * @param file is the string of path to make the reader from
     * @throws GeneralXMLException if the GeneratorInterface cannot parse the file (non xml)
     */
    public LobbyReader(String file) throws GeneralXMLException {
        myDocument = XMLGeneratorInterface.createDocument(new File(file));
    }

    /**
     * Get dimensions of the screen
     * @return screen width
     */
    @Override
    public int getScreenWidth() {
        return Integer.parseInt(XMLParseInterface.getSingleTag(myDocument, WIDTH_TAG));
    }

    /**
     * Get dimensions of the screen
     * @return screen height
     */
    @Override
    public int getScreenHeight() {
        return Integer.parseInt(XMLParseInterface.getSingleTag(myDocument, HEIGHT_TAG));
    }

    /**
     * Get meta parameters for lobby
     * @return stylesheet
     */
    @Override
    public List<String> getLobbyStylesheet() {
        return XMLParseInterface.getXMLList(myDocument, LOBBY_STYLESHEET);
    }

    /**
     * Get meta parameters for lobby
     * @return languages
     */
    @Override
    public List<String> getLobbyLanguages() {
        return XMLParseInterface.getXMLList(myDocument, LANGUAGE_TAG);
    }

    /**
     * Get meta parameters for errors
     * @return error stylesheet
     */
    @Override
    public String getErrorStylesheet() {
        return XMLParseInterface.getSingleTag(myDocument, ERROR_STYLESHEET);
    }

    /**
     * Get meta parameters for lobby
     * @return icon properties
     */
    @Override
    public String getIconProperties() {
        return XMLParseInterface.getSingleTag(myDocument, ICON_PROPERTIES);
    }

    /**
     * Get a list of maps for all icon arguments
     * @return
     */
    @Override
    public List<Map<String, String>> getBundleArguments() {
        List<Map<String, String>> list = new ArrayList<>();
        NodeList nodeList = XMLParseInterface.getNodeList(myDocument, BUNDLE_TAG);
        for (int index = 0; index < nodeList.getLength(); index ++) {
            Node node = nodeList.item(index);
            list.add(createArgsMap(node));
        }
        return list;
    }

    /**
     * Get a list of maps for all file arguments
     * @return maps of files
     */
    @Override
    public List<List<File>> getFileTags() {
        List<List<File>> list = new ArrayList<>();
        NodeList nodeList = XMLParseInterface.getNodeList(myDocument, BUNDLE_TAG);
        for (int index = 0; index < nodeList.getLength(); index ++) {
            Node node = nodeList.item(index);
            list.add(createFilesList(node));
        }
        return list;
    }

    /**
     * Get metadata that drives game starter all files display
     * @return status of files
     */
    @Override
    public String getFilesDisplayStatus() {
        return XMLParseInterface.getSingleTag(myDocument, FILES_STATUS_BUNDLE);
    }

    /**
     * Get metadata that drives game starter all files display
     * @return icons for files
     */
    @Override
    public String getFilesDisplayIcon() {
        return XMLParseInterface.getSingleTag(myDocument, FILES_ICON_BUNDLE);
    }

    private Map<String, String> createArgsMap(Node n) {
        Map<String, String> map = new HashMap<>();
        map.put(NAME_TAG, getElementByTag(n, NAME_TAG));
        map.put(ICON_TAG, getElementByTag(n, ICON_TAG));

        return map;
    }

    private List<File> createFilesList(Node n) {
        List<File> list = new ArrayList<>();
        List<String> tags = new ArrayList<>(List.of(DECK_TAG, GAME_TAG, HAND_TAG, PLAYER_TAG, VIEW_TAG));
        for (String tag: tags) {
            try {
                list.add(new File(getElementByTag(n, tag)));
            } catch (Exception ignored) {
                ;
            }
        }
        return list;
    }

    private String getElementByTag(Node n, String tag) {
        return XMLParseInterface.getElement(n, tag);
    }

}
