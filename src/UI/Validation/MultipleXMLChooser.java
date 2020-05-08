package UI.Validation;

import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

/**
 * Creates a filechooser, allows the user to select multiple XML files
 * @author Max Smith
 */
public class MultipleXMLChooser {

    private static final String ACCEPTED_XML_DESCRIPTION = "All XMLs";
    private static final String ACCEPTED_XML_EXTENSION = "*.xml";
    private static final String DIRECTORY_TO_XML = System.getProperty("user.dir") + "/data/xml/good";

    private List<File > myFileList;

    /**
     * Construct a multiple xml chooser (essentially interface)
     */
    public MultipleXMLChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(DIRECTORY_TO_XML));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(ACCEPTED_XML_DESCRIPTION, ACCEPTED_XML_EXTENSION));

        /* Show open file dialog to select multiple files. */
        this.myFileList = fileChooser.showOpenMultipleDialog(null);
    }

    /**
     * Retrieves the list of files as selected by the file chooser
     * @return the list of selected files
     */
    public List<File> getFileList() {
        return this.myFileList;
    }


}
