package UI.Validation;

import UI.IconFactories.Icon;
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

    public FileDisplay(String statusIconBundleName, LanguageBundle languageBundle, XMLFileType fileType, String fileIconName, String equalIconName) {
        myFullDisplay = new VBox();
        myFileIcons = new HBox();
        Formatter.formatFileDisplay(myFullDisplay, myFileIcons);
        StylizedNode.setStyleID(myFullDisplay, this.getClass());

        myLanguageBundle = languageBundle;

        createFileTypeLabel(fileType);
        createFileIcons(fileIconName, equalIconName);
        createStatusIcons(statusIconBundleName);

        myFullDisplay.getChildren().add(myFileIcons);
    }

    @Override
    public Node getView() {
        return myFullDisplay;
    }

    @Override
    public void updateLanguage() {
        myFileTypeLabel.setText(getFileTypeTranslation());
    }

    public void updateStatusIcon(FileStatus newStatus) {
        String newIconName = myStatusIcons.getString(newStatus.toString());
        String pathToNewIcon = formatStatusIconPath(newIconName);
        myStatusIcon.setIconImage(pathToNewIcon);
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

    private void createFileTypeLabel(XMLFileType fileType) {
        myLanguageKey = fileType.name();
        myFileTypeLabel = new Label(getFileTypeTranslation());
        myFileTypeLabel.setAlignment(Pos.CENTER);
        myFullDisplay.getChildren().add(myFileTypeLabel);
    }

    private void createFileIcons(String fileIconName, String equalIconName) {
        String fileIconPath = formatFileIconPath(fileIconName);
        Icon myFileIcon = new Icon(fileIconPath);
        String equalIconPath = formatFileIconPath(equalIconName);
        Icon myEqualIcon = new Icon(equalIconPath);
        myFileIcons.getChildren().addAll(myFileIcon.getView(), myEqualIcon.getView());
    }

    private void createStatusIcons(String statusIconBundleName) {
        String statusIconBundlePath = formatStatusBundlePath(statusIconBundleName);
        myStatusIcons = ResourceBundle.getBundle(statusIconBundlePath);

        String emptyStatusIconName = myStatusIcons.getString(FileStatus.EMPTY.toString());
        String statusIconPath = formatStatusIconPath(emptyStatusIconName);
        myStatusIcon = new MutableIcon(statusIconPath);
        myFileIcons.getChildren().add(myStatusIcon.getView());
    }

    private String getFileTypeTranslation() {
        return myLanguageBundle.getBundle().getString(myLanguageKey);
    }
}
