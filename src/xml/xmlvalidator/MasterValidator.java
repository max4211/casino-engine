package xml.xmlvalidator;

import UI.Interfaces.GameCaller;
import UI.Validation.FileStatus;
import UI.Validation.UpdateFilesDisplayInterface;
import UI.Validation.XMLFile;
import exceptions.CustomEnumException;
import exceptions.ValidatorException;
import exceptions.XMLParseException;
import ooga.GameConstructor;
import UI.Validation.MultipleXMLChooser;
import org.xml.sax.SAXException;
import xml.xmlbundle.XMLBundle;
import xml.xmlreader.interfaces.XMLValidatorInterface;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class MasterValidator {

    private XMLBundle myXMLBundle;
    private ValidatorFactory myFactory;
    private UpdateFilesDisplayInterface myUpdate;
    private Consumer<GameCaller> myCaller;
    private Consumer<Exception> myExceptionShow;

    /**
     * Lambda to show errors on front end and prompt new files
     * @param fileList
     */
    public MasterValidator(List<File> fileList,
                           UpdateFilesDisplayInterface update,
                           Consumer<GameCaller> caller,
                           Consumer<Exception> showException) {
        this.myXMLBundle = new XMLBundle();
        this.myFactory = new ValidatorFactory();
        this.myUpdate = update;
        this.myCaller = caller;
        this.myExceptionShow = showException;
        validateFiles(fileList);
    }

    private void validateFiles(List<File> fileList) {
        tryFileAdd(fileList);
        while (!(this.myXMLBundle.isComplete())) {
            MultipleXMLChooser chooser = new MultipleXMLChooser();
            tryFileAdd(chooser.getFileList());
        }
        this.myCaller.accept(this::createGame);
    }

    private void tryFileAdd(List<File> fileList) {
        for (File file: fileList) {
            try {
                XMLFile tag = getTag(file);
                if (needsFile(tag)) {
                    try {
                        if (isValidFile(file, tag)) {
                            this.myXMLBundle.addFile(file, tag);
                            this.myUpdate.updateStatus(tag, FileStatus.VALID);
                        }
                    } catch (ValidatorException e) {
                        this.myExceptionShow.accept(e);
                        this.myUpdate.updateStatus(tag, FileStatus.INVALID);
                    }
                }
            } catch (XMLParseException | CustomEnumException ignored) {
                // TODO - throw invalid XML file exception
                ;
            }
        }
    }

    private XMLFile getTag(File file) {
        try {
            String tag = XMLValidatorInterface.getMetaTag(file);
            XMLFile enumTag = XMLFile.valueOf(tag.toUpperCase());
            return enumTag;
        } catch (Exception e) {
            throw new CustomEnumException(e);
        }
    }

    private boolean needsFile(XMLFile tag) {
        return this.myXMLBundle.needsFile(tag);
    }

    private boolean bundleWantsFile(File file, XMLFile tag) {
        boolean needsFile = needsFile(tag);
        boolean validFile = isValidFile(file, tag);
        return needsFile && validFile;
    }

    private boolean isValidFile(File file, XMLFile enumTag) throws ValidatorException {
        Validator validator = this.myFactory.createValidator(enumTag.toString());
        return validator.validate(file);
    }

    private void createGame() {
        new GameConstructor(this.myXMLBundle.getXMLFiles());
    }

}
