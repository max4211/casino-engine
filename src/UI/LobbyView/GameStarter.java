package UI.LobbyView;

import UI.Interfaces.LanguageResponder;
import UI.Interfaces.StylizedNode;
import UI.LanguageBundle;
import UI.Validation.AllFilesDisplay;
import Utility.Formatter;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import xml.xmlvalidator.MasterValidator;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;

public class GameStarter implements StylizedNode, LanguageResponder {

    private VBox myGameStarter;
    private Formatter myFormatter;
    private List<File> myFiles;
    private Label myGameLabel;

    private LanguageBundle myLanguageBundle;
    private final String myGameKey;

    private static final String PATH_TO_ICON = "iconImages/runnableGameIcons/";

    public GameStarter(String imageFile, String gameKey, List<File> files, Consumer<Exception> showException, LanguageBundle languageBundle) {
        myGameStarter = new VBox();
        myFormatter = new Formatter();
        StylizedNode.setStyleID(myGameStarter, this.getClass());
        //TODO: move this into formatter
        myGameStarter.setAlignment(Pos.CENTER);

        Icon myIconButton = new Icon(PATH_TO_ICON.concat(imageFile));

        myFormatter.formatGameIconView(myIconButton.getView());

        this.myFiles = files;
        this.myGameStarter.setOnMouseClicked(e -> {
            // TODO - parametrize display in data
            LanguageBundle testBundle = new LanguageBundle("English");
            String statusBundle= "StandardStatuses";
            String iconBundle = "StandardXMLs";
            AllFilesDisplay display = new AllFilesDisplay(testBundle, statusBundle, iconBundle);
            new MasterValidator(this.myFiles,
                    (file, status) -> display.updateStatus(file, status),
                    (initializer) -> display.enableGameButton(initializer),
                    showException);
        });

        myLanguageBundle = languageBundle;
        myGameKey = gameKey;
        myGameLabel = new Label();
        myGameLabel.setAlignment(Pos.CENTER);
        updateLanguage();
        myGameStarter.getChildren().addAll(myIconButton.getView(), myGameLabel);
    }

    @Override
    public VBox getView() {
        return myGameStarter;
    }

    public List<File> getFiles() {
        return this.myFiles;
    }

    @Override
    public void updateLanguage() {
        myGameLabel.setText(myLanguageBundle.getBundle().getString(myGameKey));
    }
}
