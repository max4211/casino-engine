package UI.LobbyView;

import UI.Interfaces.NodeViewInterface;
import UI.LanguageBundle;
import UI.Validation.AllFilesDisplay;
import UI.Validation.UpdateFilesDisplayInterface;
import Utility.Formatter;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import xml.xmlvalidator.MasterValidator;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class GameIcon implements NodeViewInterface {

    private VBox myGameIcon;
    private Formatter myFormatter;
    private List<File> myFiles;

    //TODO: duplicated in CardView
    private static final String BLACKJACK = "Blackjack";
    private static final String BLACKJACK_ICON_ID = "blackJack-icon";
    private static final String CUSTOM = "Custom Game";
    private static final String CUSTOM_ICON_ID = "custom-icon";

    public GameIcon(String imageFile, String gameName, List<File> files) {
        myGameIcon = new VBox();
        //TODO: move this into formatter
        myGameIcon.setAlignment(Pos.CENTER);
        Icon myIconButton = new Icon(imageFile);

        // FIXME: add reflection to IDs
        if (gameName.equals(BLACKJACK)) {
            myIconButton.getView().setId(BLACKJACK_ICON_ID);
        }

        if (gameName.equals(CUSTOM)) {
            myIconButton.getView().setId(CUSTOM_ICON_ID);
        }

        myFormatter = new Formatter();
        myFormatter.formatGameIconView(myIconButton.getView());

        this.myFiles = files;
        this.myGameIcon.setOnMouseClicked(e -> {
            // TODO - parametrize display in data
            LanguageBundle testBundle = new LanguageBundle("English");
            String statusBundle= "StandardStatuses";
            String iconBundle = "StandardXMLs";
            AllFilesDisplay display = new AllFilesDisplay(testBundle, statusBundle, iconBundle);
            new MasterValidator(this.myFiles,
                    (file, status) -> display.updateStatus(file, status),
                    (initializer) -> display.enableGameButton(initializer));
        });

        //TODO: move this to formatting
        Label myGameLabel = new Label(gameName);
        myGameIcon.getChildren().addAll(myIconButton.getView(), myGameLabel);
    }

    @Override
    public VBox getView() {
        return myGameIcon;
    }

    public List<File> getFiles() {
        return this.myFiles;
    }
}
