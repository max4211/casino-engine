package UI.IconFactories;

import javafx.scene.image.Image;

public class MutableIcon extends Icon {

    public MutableIcon(String imageName) {
        super(imageName);
    }

    public MutableIcon(String imageName, IconSize size) {
        super(imageName, size);
    }

    public void setImage(String newImageFilePath) {
        Image newImage = new Image(newImageFilePath);
        myIcon.setImage(newImage);
    }
}
