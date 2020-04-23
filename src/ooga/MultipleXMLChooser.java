package ooga;

import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MultipleXMLChooser {

    private List<File> fileList;
    private static final String ACCEPTED_XML_DESCRIPTION = "All XMLs";
    private static final String ACCEPTED_XML_EXTENSION = "*.xml";
    private static final String DIRECTORY_TO_XML = System.getProperty("user.dir") + "/data/xml/";

    private List<File > myFileList;


    public MultipleXMLChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(DIRECTORY_TO_XML));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(ACCEPTED_XML_DESCRIPTION, ACCEPTED_XML_EXTENSION));

        /* Show open file dialog to select multiple files. */
        this.myFileList = fileChooser.showOpenMultipleDialog(null);
    }

    public List<File> getFileList() {
        return this.myFileList;
    }


}
