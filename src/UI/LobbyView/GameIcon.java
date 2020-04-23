package UI.LobbyView;

import UI.Interfaces.NodeViewInterface;
import Utility.Formatter;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.Map;
import java.util.function.Consumer;

public abstract class GameIcon implements NodeViewInterface {

    private VBox myGameIcon;
    private Formatter myFormatter;

    //TODO: duplicated in CardView

    private static final String BLACKJACK = "Blackjack";
    private static final String BLACKJACK_ICON_ID = "blackJack-icon";
    private static final String CUSTOM = "Custom Game";
    private static final String CUSTOM_ICON_ID = "custom-icon";

    public GameIcon(String imageFile, String gameName, Consumer<Map<String, File>> myFileAccepter) {

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
        myGameIcon.setOnMouseClicked(e -> {
            myFileAccepter.accept(getFiles());
        });

        //TODO: move this to formatting
        Label myGameLabel = new Label(gameName);
        myGameIcon.getChildren().addAll(myIconButton.getView(), myGameLabel);
    }

    @Override
    public VBox getView() {
        return myGameIcon;
    }

    protected abstract Map<String, File> getFiles();
}
