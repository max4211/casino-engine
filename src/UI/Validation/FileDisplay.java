package UI.Validation;

import UI.Interfaces.LanguageUpdater;
import UI.Interfaces.NodeViewInterface;
import UI.LanguageBundle;
import UI.LobbyView.Icon;
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


    private String myLanguageKey;
    private Label myFileTypeLabel;
    private LanguageBundle myLanguageBundle;
    private ResourceBundle myStatusIcons;

    private static final String EQUAL_KEY = "Equals";

    public FileDisplay(String statusIconBundle, LanguageBundle languageBundle, XMLFile fileType, String fileIconName) {
        myVBox = new VBox();
        myHBox = new HBox();

        myLanguageBundle = languageBundle;
        myLanguageKey = fileType.name();
        myFileTypeLabel = new Label(myLanguageBundle.getBundle().getString(myLanguageKey));
        myVBox.getChildren().add(myFileTypeLabel);

        Icon myFileIcon = new Icon(fileIconName);
        Icon myArrowIcon = new Icon(EQUAL_KEY);

        myStatusIcons = ResourceBundle.getBundle(statusIconBundle);
        myStatusIcon = new MutableIcon(myStatusIcons.getString(FileStatus.EMPTY.name()));
        myHBox.getChildren().addAll(myFileIcon.getView(), myArrowIcon.getView(), myStatusIcon.getView());
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
