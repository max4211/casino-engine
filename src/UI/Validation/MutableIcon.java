package UI.Validation;

import UI.IconFactories.Icon;
import javafx.scene.image.Image;

public class MutableIcon extends Icon {

    public MutableIcon(String imageFile) {
        super(imageFile);
    }

    public void setIconImage(String newIconName) {
        Image newIconImage = new Image(newIconName);
        myIcon.setImage(newIconImage);
    }
}
