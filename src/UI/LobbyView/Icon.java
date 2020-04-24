package UI.LobbyView;

import UI.Interfaces.NodeViewInterface;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// shell whose subclasses implement the onClick method
public class Icon implements NodeViewInterface {

    private static final int ICON_SIZE = 40;
    protected ImageView myIcon;

    public Icon(String imageFile) {
        myIcon = new ImageView();
        Image myBackgroundImage = new Image(imageFile);
        myIcon.setImage(myBackgroundImage);
        myIcon.setFitHeight(ICON_SIZE);
        myIcon.setFitWidth(ICON_SIZE);
    }

    public ImageView getView() {
        return myIcon;
    }
}
