package xml.xmlvalidator;

import UI.Validation.XMLFile;
import exceptions.XMLParseException;
import ooga.GameConstructor;
import UI.Validation.MultipleXMLChooser;
import xml.xmlbundle.XMLBundle;
import xml.xmlreader.interfaces.XMLValidatorInterface;

import java.io.File;
import java.util.List;

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
            MultipleXMLChooser chooser = new MultipleXMLChooser();
            tryFileAdd(chooser.getFileList());
        }
        createGame();
    }

    private void tryFileAdd(List<File> fileList) {
        for (File file: fileList) {
            try {
                if (bundleWantsFile(file))
                    this.myXMLBundle.addFile(file);
            } catch (XMLParseException ignored) {
                ;
            }
        }
    }

    private boolean bundleWantsFile(File file) {
        String tag = XMLValidatorInterface.getMetaTag(file);
        XMLFile enumTag = XMLFile.valueOf(tag);
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
