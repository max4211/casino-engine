package UI.LobbyView;

import UI.Interfaces.NodeViewInterface;
import Utility.Formatter;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.File;
import java.util.Map;
import java.util.function.Consumer;

public abstract class GameIcon implements NodeViewInterface {

    private VBox myGameIcon;
    private static final String ICON_RESOURCE_FOLDER = "iconImages/";
    private Formatter myFormatter;

    //TODO: duplicated in CardView
    private static final int FULL_BACKGROUND_FILL = 1;
    private static final boolean FILL_AS_PERCENT = true;
    private static final boolean BACKGROUNDFILL_CONTAIN = false;
    private static final boolean BACKGROUNDFILL_COVER = false;
    private static final BackgroundSize FULL_BACKGROUND_SIZE = new BackgroundSize(FULL_BACKGROUND_FILL, FULL_BACKGROUND_FILL, FILL_AS_PERCENT, FILL_AS_PERCENT, BACKGROUNDFILL_CONTAIN, BACKGROUNDFILL_COVER);

    public GameIcon(String imageFile, String gameName, Consumer<Map<String, File>> myFileAccepter) {

        myGameIcon = new VBox();
        //TODO: move this into formatter
        myGameIcon.setAlignment(Pos.CENTER);
        Button myIconButton = new Button();
        Image icon = new Image(ICON_RESOURCE_FOLDER + imageFile);
        myIconButton.setBackground(new Background(new BackgroundImage(icon, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, FULL_BACKGROUND_SIZE)));
        myFormatter = new Formatter();
        myFormatter.formatIconView(myIconButton);
        myIconButton.setOnMouseClicked(e -> {
            myFileAccepter.accept(getFiles());
        });
        //TODO: move this to formatting
        Label myGameLabel = new Label(gameName);
        myGameIcon.getChildren().addAll(myIconButton, myGameLabel);
    }

    @Override
    public VBox getView() {
        return myGameIcon;
    }

    protected abstract Map<String, File> getFiles();
}
