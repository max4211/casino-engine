package UI.LobbyView;

import UI.Interfaces.NodeViewInterface;
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

public class GameIcon implements NodeViewInterface {

    private VBox myGameIcon;
    private Formatter myFormatter;
    private List<File> myFiles;

    //TODO: duplicated in CardView
    private static final String BLACKJACK = "Blackjack";
    private static final String BLACKJACK_ICON_ID = "blackJack-icon";
    private static final String CUSTOM = "Custom Game";
    private static final String CUSTOM_ICON_ID = "custom-icon";

    private static final String PATH_TO_ICON = "iconImages/runnableGameIcons/";

    public GameIcon(String imageFile, String gameName, List<File> files, Consumer<Exception> showException) {
        myGameIcon = new VBox();
        //TODO: move this into formatter
        myGameIcon.setAlignment(Pos.CENTER);
        System.out.println(PATH_TO_ICON.concat(imageFile));
        Icon myIconButton = new Icon(PATH_TO_ICON.concat(imageFile));
        tagIcon(myIconButton, gameName);

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
                    (initializer) -> display.enableGameButton(initializer),
                    showException);
        });

        myGameIcon.getChildren().addAll(myIconButton.getView(), new Label(gameName));
    }

    private void tagIcon(Icon icon, String name) {
        // FIXME: add reflection to IDs, this is used for testing
        if (name.equals(BLACKJACK))
            icon.getView().setId(BLACKJACK_ICON_ID);
        else if (name.equals(CUSTOM))
            icon.getView().setId(CUSTOM_ICON_ID);
    }

    @Override
    public VBox getView() {
        return myGameIcon;
    }

    public List<File> getFiles() {
        return this.myFiles;
    }
}
