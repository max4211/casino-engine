package UI.LobbyView;

import UI.Interfaces.NodeViewInterface;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

// shell whose subclasses implement the onClick method
public class Icon implements NodeViewInterface {

    private static final String ICON_RESOURCE_FOLDER = "iconImages/";
    private static final int FULL_BACKGROUND_FILL = 1;
    private static final boolean FILL_AS_PERCENT = true;
    private static final boolean BACKGROUNDFILL_CONTAIN = false;
    private static final boolean BACKGROUNDFILL_COVER = false;
    private static final BackgroundSize FULL_BACKGROUND_SIZE = new BackgroundSize(FULL_BACKGROUND_FILL, FULL_BACKGROUND_FILL, FILL_AS_PERCENT, FILL_AS_PERCENT, BACKGROUNDFILL_CONTAIN, BACKGROUNDFILL_COVER);

    protected Button myButton;

    public Icon(String imageFile) {
        myButton = new Button();
        setBackgroundImage(imageFile);
    }

    protected void setBackgroundImage(String imageFile) {
        Image tempImage = new Image(ICON_RESOURCE_FOLDER + imageFile);
        BackgroundImage tempBackgroundImage = new BackgroundImage(tempImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, FULL_BACKGROUND_SIZE);
        myButton.setBackground(new Background(tempBackgroundImage));
    }

    public Button getView() {
        return myButton;
    }
}
