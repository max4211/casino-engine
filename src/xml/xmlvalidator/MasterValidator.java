package xml.xmlvalidator;

import UI.Validation.XMLFile;
import exceptions.XMLParseException;
import ooga.GameConstructor;
import UI.Validation.MultipleXMLChooser;
import xml.xmlbundle.XMLBundle;
import xml.xmlreader.interfaces.XMLValidatorInterface;

import java.io.File;
import java.util.List;
import java.util.Set;

public class MasterValidator {

    private XMLBundle myXMLBundle;
    private ValidatorFactory myFactory;

    /**
     * Needs a bundle of files, lambdas to interface with validator view (affirm, reject)
     * Lambda to show errors on front end and prompt new files
     * @param fileList
     * TODO - add ain calls to validator view for updating status
     */
    public MasterValidator(List<File> fileList) {
        this.myXMLBundle = new XMLBundle();
        this.myFactory = new ValidatorFactory();
        validateFiles(fileList);
    }

    private void validateFiles(List<File> fileList) {
        tryFileAdd(fileList);
        while (!(this.myXMLBundle.isComplete())) {
            printMissingFiles();
            MultipleXMLChooser chooser = new MultipleXMLChooser();
            tryFileAdd(chooser.getFileList());
        }
        System.out.println("all files validated, attempting to create game");
        createGame();
    }

    private void printMissingFiles() {
        System.out.println("MISSING FILES IN MASTER VALIDATOR: \n");
        Set<XMLFile> set = this.myXMLBundle.getMissingFiles();
        for (XMLFile file: set)
            System.out.printf("%s\n", file.toString());
    }

    private void tryFileAdd(List<File> fileList) {
        for (File file: fileList) {
            try {
                XMLFile tag = getTag(file);
                if (bundleWantsFile(file, tag))
                    this.myXMLBundle.addFile(file, tag);
            } catch (XMLParseException ignored) {
                ;
            }
        }
    }

    private XMLFile getTag(File file) {
        String tag = XMLValidatorInterface.getMetaTag(file);
        XMLFile enumTag = XMLFile.valueOf(tag.toUpperCase());
        return enumTag;
    }

    private boolean bundleWantsFile(File file, XMLFile enumTag) {
        boolean needsFile = this.myXMLBundle.needsFile(enumTag);
        boolean validFile = isValidFile(file, enumTag);
        return needsFile && validFile;
    }

    private boolean isValidFile(File file, XMLFile enumTag) {
        Validator validator = this.myFactory.createValidator(enumTag.toString());
        return validator.validate(file);
    }

    private void createGame() {
        new GameConstructor(this.myXMLBundle.getXMLFiles());
    }

}
