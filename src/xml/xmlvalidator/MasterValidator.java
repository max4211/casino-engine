package xml.xmlvalidator;

import UI.Interfaces.GameCaller;
import UI.Utilities.ScreenPosition;
import UI.Validation.FileStatus;
import UI.Validation.MultipleXMLChooser;
import UI.Validation.UpdateFilesDisplayInterface;
import UI.Validation.XMLFileType;
import exceptions.CustomEnumException;
import exceptions.ValidatorException;
import exceptions.XMLParseException;
import ooga.GameConstructor;
import xml.xmlbundle.XMLBundle;
import xml.xmlreader.interfaces.XMLValidatorInterface;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;

/**
 * Master validation object, works hand in hand with validator view to assemble complete set of files to construct game
 * Created after activating icon in lobby view, with consumers to throw exceptions and activate start game button
 * @author Max Smith
 */
public class MasterValidator {

    private XMLBundle myXMLBundle;
    private ValidatorFactory myFactory;
    private UpdateFilesDisplayInterface myUpdate;
    private Consumer<GameCaller> myCaller;
    private Consumer<Exception> myExceptionShow;
    private Consumer<Double> mySetX;

    /**
     * Construct a master validator object to continue looping until all files have been selected
     * @param fileList is the list of files to validate (first pass, could be empyt)
     * @param update a way to update the validatotr interface while maintaining view separation
     * @param caller is a way to activate game starter button
     * @param setX set the position of the validator window
     * @param showException throws exception in view for poor files
     */
    public MasterValidator(List<File> fileList,
                           UpdateFilesDisplayInterface update,
                           Consumer<GameCaller> caller,
                           Consumer<Double> setX,
                           Consumer<Exception> showException) {
        this.myXMLBundle = new XMLBundle();
        this.myFactory = new ValidatorFactory();
        this.myUpdate = update;
        this.myCaller = caller;
        this.myExceptionShow = showException;
        this.mySetX = setX;
        validateFiles(fileList);
    }

    private void validateFiles(List<File> fileList) {
        tryFileAdd(fileList);
        while (!(this.myXMLBundle.isComplete())) {
            MultipleXMLChooser chooser = new MultipleXMLChooser();
            this.mySetX.accept(ScreenPosition.FARLEFT.getX());
            try {
                tryFileAdd(chooser.getFileList());
            } catch (NullPointerException e) {
                this.myExceptionShow.accept(e);
            }
        }
        this.myCaller.accept(this::createGame);
    }

    private void tryFileAdd(List<File> fileList) {
        for (File file: fileList) {
            try {
                XMLFileType tag = getTag(file);
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
            } catch (XMLParseException | CustomEnumException e) {
                this.myExceptionShow.accept(e);
            }
        }
    }

    private XMLFileType getTag(File file) {
        try {
            String tag = XMLValidatorInterface.getMetaTag(file);
            XMLFileType enumTag = XMLFileType.valueOf(tag.toUpperCase());
            return enumTag;
        } catch (Exception e) {
            throw new CustomEnumException(e);
        }
    }

    private boolean needsFile(XMLFileType tag) {
        return this.myXMLBundle.needsFile(tag);
    }

    private boolean bundleWantsFile(File file, XMLFileType tag) {
        boolean needsFile = needsFile(tag);
        boolean validFile = isValidFile(file, tag);
        return needsFile && validFile;
    }

    private boolean isValidFile(File file, XMLFileType enumTag) throws ValidatorException {
        Validator validator = this.myFactory.createValidator(enumTag.toString());
        return validator.validate(file);
    }

    private void createGame() {
        new GameConstructor(this.myXMLBundle.getXMLFiles(), this.myExceptionShow);
    }

}
