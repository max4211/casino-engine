package UI.LobbyView;

import UI.Interfaces.NodeViewInterface;
import Utility.Formatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.Map;
import java.util.function.Consumer;

public abstract class GameIcon implements NodeViewInterface {

    private ImageView myIconImage;
    private static final String ICON_RESOURCE_FOLDER = "iconImages/";
    private Formatter myFormatter;

    public GameIcon(String imageFile, Consumer<Map<String, File>> myFileAccepter) {

        myIconImage = new ImageView();
        Image icon = new Image(ICON_RESOURCE_FOLDER + imageFile);
        myIconImage.setImage(icon);
        myFormatter = new Formatter();
        myFormatter.formatIconView(myIconImage);
        myIconImage.setOnMouseClicked(e -> {
            myFileAccepter.accept(getFiles());
        });
    }

    @Override
    public ImageView getView() {
        return myIconImage;
    }

    protected abstract Map<String, File> getFiles();
}
