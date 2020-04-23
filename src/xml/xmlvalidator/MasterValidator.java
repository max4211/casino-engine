package xml.xmlvalidator;

import ooga.GameConstructor;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterValidator {

    private Map<String, File> myXMLFiles;

    private static final String DECK_KEY = "Deck";
    private static final String GAME_KEY = "Game";
    private static final String PLAYER_KEY = "Players";
    private static final String HAND_KEY = "Hands";
    private static final String VIEW_KEY = "View";

    /**
     * Refactor to list of strings
     * Needs a bundle of files, lambdas to interface with validator view (affirm, reject)
     * Lambda to show errors on front end and prompt new files
     * @param fileList
     */
    public MasterValidator(List<File> fileList) {
        this.myXMLFiles = new HashMap<String, File>();
        validateFiles(fileList);
    }

    private void validateFiles(List<File> fileList) {

    }

    private void createGame() {
        new GameConstructor(myXMLFiles);
    }



}
