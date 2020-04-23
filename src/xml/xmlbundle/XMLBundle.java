package xml.xmlbundle;

import xml.xmlreader.interfaces.XMLValidatorInterface;

import java.io.File;
import java.util.*;

public class XMLBundle implements XMLBundleInterface {

    // TODO - use Eric's enum types
    private static final String DECK_KEY = "Deck";
    private static final String GAME_KEY = "Game";
    private static final String PLAYER_KEY = "Players";
    private static final String HAND_KEY = "Hands";
    private static final String VIEW_KEY = "View";

    private Map<String, File> myXMLFiles;

    public XMLBundle() {
        this.myXMLFiles = new HashMap<>();
    }

    @Override
    public Map<String, File> getXMLFiles() {
        return this.myXMLFiles;
    }

    @Override
    public boolean needsFile(String tag) {
        return !(this.myXMLFiles.containsKey(tag));
    }

    @Override
    public void addFile(File file) {
        String tag = XMLValidatorInterface.getMetaTag(file);
        this.myXMLFiles.put(tag, file);
    }

    @Override
    public Set<String> missingFiles() {

        return null;
    }

    private Set<String> createFileSet() {
        List<String> list = new ArrayList<>(List.of(
//                DECK_KEY, GAME_KEY,
        ));
        return new HashSet<>();
    }


}
