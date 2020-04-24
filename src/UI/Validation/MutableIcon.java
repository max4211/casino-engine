package UI.Validation;

import UI.LobbyView.Icon;
import javafx.scene.image.Image;

public class MutableIcon extends Icon {

    public MutableIcon(String imageFile) {
        super(imageFile);
    }

    public void setIconImage(String newIconName) {
        System.out.println(newIconName);
        Image newIconImage = new Image(newIconName);
        myIcon.setImage(newIconImage);
    }
}
