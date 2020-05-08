package UI.Validation;

import UI.Icons.Icon;
import UI.Icons.MutableIcon;
import UI.Interfaces.LanguageResponder;
import UI.Interfaces.StylizedNode;
import UI.Utilities.Formatter;
import UI.Utilities.LanguageBundle;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ResourceBundle;

/**
 * Class used to represent the status of one XML file that is in the validation process by means of an HBox.
 * Relies heavily on Icons mapped to by enumerated types (XMLFileType, FileStatus) via ResourceBundles.
 * This view is controlled by the backend validator, updating the statuses as files are checked.
 * Implements StylizedNode, allowing access to an HBox with the CSS ID FileDisplay.
 * Implements LanguageResponder, updating the text of the file type on a language change.
 * @author Eric Doppelt
 */
public class FileDisplay implements StylizedNode, LanguageResponder {

    private VBox myFullDisplay;
    private HBox myFileIcons;
    private MutableIcon myStatusIcon;

    private String myLanguageKey;
    private Label myFileTypeLabel;
    private LanguageBundle myLanguageBundle;
    private ResourceBundle myStatusIcons;

    private static final String PATH_TO_STATUS_ICON_BUNDLE = "iconBundles/fileStatuses/";
    private static final String PATH_TO_FILE_ICONS = "iconImages/fileValidatorIcons/";
    private static final String PATH_TO_STATUS_ICONS = "iconImages/fileStatusIcons/";

    /**
     * Constructor that initializes the FileDisplay, which can be updated later on by subsequent updateStatusIcon() calls.
     * Creates a display that uses icons to display the File Type and File Status
     * Updates text by means of a LanguageBundle, which is given at construction.
     * @param statusIconBundleName is the name of the ResourceBundle in which icons are to be pulled from.
     * @param languageBundle is the LanguageBundle to be referenced for all text in the UI.
     * @param fileType is the enumerated file type (such as Deck or Hands, corresponding to an XML file).
     * @param fileIconName is the name of the icon used to represent the file type (such as an icon showing a deck for a Deck enumeration).
     * @param equalIconName is the name of the icon used to create the equal sign, showing the relationship from an icon type to its status.
     */
    public FileDisplay(String statusIconBundleName, LanguageBundle languageBundle, XMLFileType fileType, String fileIconName, String equalIconName) {
        myFullDisplay = new VBox();
        myFileIcons = new HBox();
        Formatter.formatFileDisplay(myFullDisplay, myFileIcons);
        StylizedNode.setStyleID(myFullDisplay, this.getClass());

        myLanguageBundle = languageBundle;

        renderFileTypeLabel(fileType);
        renderFileIcons(fileIconName, equalIconName);
        renderStatusIcons(statusIconBundleName);
    }

    /**
     * Basic method that implements the StylizedNode interface and returns a Node containing all the relevant information.
     * The CSS ID of this is FileDisplay.
     * @return a Node, which is a VBox, which contains the icons and text to describe the file.
     */
    @Override
    public Node getView() {
        return myFullDisplay;
    }

    /**
     * Method is called to implement the LanguageResponder interface.
     * This alerts the FileDisplay to update the text it is showing to that which is found in the new LanguageBundle.
     * No parameters or return type.
     */
    @Override
    public void updateLanguage() {
        myFileTypeLabel.setText(getFileTypeTranslation());
    }

    /**
     * Updates the status of the file displayed based on the new enumerated type that is a FileStatus (valid, empty, or invalid).
     * This only changes the icon used to display file status and nothing more.
     * @param newStatus is the enumerated type of the new file status based on the validation process.
     */
    public void updateStatusIcon(FileStatus newStatus) {
        String newIconName = myStatusIcons.getString(newStatus.toString());
        String pathToNewIcon = formatStatusIconPath(newIconName);
        myStatusIcon.setImage(pathToNewIcon);
    }

    private String formatFileIconPath(String iconName) {
        return PATH_TO_FILE_ICONS.concat(iconName);
    }

    private String formatStatusIconPath(String iconName) {
        return PATH_TO_STATUS_ICONS.concat(iconName);
    }

    private String formatStatusBundlePath(String bundleName) {
        return PATH_TO_STATUS_ICON_BUNDLE.concat(bundleName);
    }

    private void renderFileTypeLabel(XMLFileType fileType) {
        myLanguageKey = fileType.name();
        myFileTypeLabel = new Label(getFileTypeTranslation());
        myFileTypeLabel.setAlignment(Pos.CENTER);
        myFullDisplay.getChildren().add(myFileTypeLabel);
    }

    private void renderFileIcons(String fileIconName, String equalIconName) {
        String fileIconPath = formatFileIconPath(fileIconName);
        Icon myFileIcon = new Icon(fileIconPath);
        String equalIconPath = formatFileIconPath(equalIconName);
        Icon myEqualIcon = new Icon(equalIconPath);
        myFileIcons.getChildren().addAll(myFileIcon.getView(), myEqualIcon.getView());
    }

    private void renderStatusIcons(String statusIconBundleName) {
        String statusIconBundlePath = formatStatusBundlePath(statusIconBundleName);
        myStatusIcons = ResourceBundle.getBundle(statusIconBundlePath);

        String emptyStatusIconName = myStatusIcons.getString(FileStatus.EMPTY.toString());
        String statusIconPath = formatStatusIconPath(emptyStatusIconName);
        myStatusIcon = new MutableIcon(statusIconPath);
        myFileIcons.getChildren().add(myStatusIcon.getView());
        myFullDisplay.getChildren().add(myFileIcons);
    }

    private String getFileTypeTranslation() {
        return myLanguageBundle.getBundle().getString(myLanguageKey);
    }
}
