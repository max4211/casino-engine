package xml.xmlvalidator;

import ooga.GameConstructor;
import xml.xmlbundle.XMLBundle;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterValidator {

    private XMLBundle myXMLBundle;

    /**
     * Refactor to list of strings
     * Needs a bundle of files, lambdas to interface with validator view (affirm, reject)
     * Lambda to show errors on front end and prompt new files
     * @param fileList
     */
    public MasterValidator(List<File> fileList) {
        this.myXMLBundle = new XMLBundle();
        validateFiles(fileList);
    }

    private void validateFiles(List<File> fileList) {

    }

    private void createGame() {
        new GameConstructor(this.myXMLBundle.getXMLFiles());
    }



}
