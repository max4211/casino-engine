package UI.Validation;

import UI.Interfaces.LanguageResponder;
import UI.Interfaces.TaggedNode;
import UI.LanguageBundle;
import UI.LobbyView.Icon;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ResourceBundle;

// TODO: discuss with max whether they should have access to the whole bundle; not a big deal either way!
public class FileDisplay implements TaggedNode, LanguageResponder {

    private static final String PATH_TO_LANGUAGE = "languages/";
    private VBox myVBox;
    private HBox myHBox;
    private MutableIcon myStatusIcon;

    private static final int ICON_SPACING = 5;
    private String myLanguageKey;
    private Label myFileTypeLabel;
    private LanguageBundle myLanguageBundle;
    private ResourceBundle myStatusIcons;

    private static final String PATH_TO_STATUS_ICON_BUNDLE = "iconBundles/fileStatuses/";
    private static final String PATH_TO_FILE_ICON_IMAGE = "iconImages/fileValidatorIcons/";
    private static final String PATH_TO_STATIC_ICONS = "iconImages/fileStatusIcons/";

    public FileDisplay(String statusIconBundle, LanguageBundle languageBundle, XMLFile fileType, String fileIconName, String equalIconName) {
        myVBox = new VBox();
        myVBox.setAlignment(Pos.CENTER);
        myHBox = new HBox();
        myHBox.setSpacing(ICON_SPACING);
        myHBox.setAlignment(Pos.CENTER);

        myLanguageBundle = languageBundle;
        myLanguageKey = fileType.name();
        myFileTypeLabel = new Label(myLanguageBundle.getBundle().getString(myLanguageKey));
        myFileTypeLabel.setAlignment(Pos.CENTER);
        myVBox.getChildren().add(myFileTypeLabel);

        Icon myFileIcon = new Icon(PATH_TO_FILE_ICON_IMAGE.concat(fileIconName));
        Icon myEqualIcon = new Icon(PATH_TO_FILE_ICON_IMAGE.concat(equalIconName));

        myStatusIcons = ResourceBundle.getBundle(PATH_TO_STATUS_ICON_BUNDLE.concat(statusIconBundle));
        myStatusIcon = new MutableIcon(PATH_TO_STATIC_ICONS.concat(myStatusIcons.getString(FileStatus.EMPTY.name())));
        myHBox.getChildren().addAll(myFileIcon.getView(), myEqualIcon.getView(), myStatusIcon.getView());
        myVBox.getChildren().add(myHBox);
    }

    @Override
    public Node getView() {
        return myVBox;
    }

    @Override
    public void updateLanguage() {
        myFileTypeLabel.setText(myLanguageBundle.getBundle().getString(myLanguageKey));
    }

    public void updateStatusIcon(FileStatus newStatus) {
        myStatusIcon.setIconImage(PATH_TO_STATIC_ICONS.concat(myStatusIcons.getString(newStatus.name())));
    }
}
