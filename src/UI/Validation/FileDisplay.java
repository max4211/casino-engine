package UI.Validation;

import UI.Interfaces.LanguageUpdater;
import UI.Interfaces.NodeViewInterface;
import UI.LanguageBundle;
import UI.LobbyView.Icon;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ResourceBundle;

// TODO: discuss with max whether they should have access to the whole bundle; not a big deal either way!
public class FileDisplay implements NodeViewInterface, LanguageUpdater {

    private VBox myVBox;
    private HBox myHBox;
    private MutableIcon myStatusIcon;

    private static final int ICON_SPACING = 5;
    private String myLanguageKey;
    private Label myFileTypeLabel;
    private LanguageBundle myLanguageBundle;
    private ResourceBundle myStatusIcons;

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

        Icon myFileIcon = new Icon(fileIconName);
        Icon myEqualIcon = new Icon(equalIconName);

        myStatusIcons = ResourceBundle.getBundle(statusIconBundle);
        myStatusIcon = new MutableIcon(myStatusIcons.getString(FileStatus.EMPTY.name()));
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
        myStatusIcon.setIconImage(myStatusIcons.getString(newStatus.name()));
    }
}
