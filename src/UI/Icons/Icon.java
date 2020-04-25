package UI.Icons;

import UI.Interfaces.StylizedNode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Icon implements StylizedNode {

    private static final IconSize DEFAULT_ICON_SIZE = IconSize.NORMAL;
    protected ImageView myIcon;

    public Icon(String imageFile) {
        this(imageFile, DEFAULT_ICON_SIZE);
    }

    public Icon(String imageFile, IconSize size) {
        myIcon = new ImageView();
        StylizedNode.setStyleID(myIcon, this.getClass());
        Image myImage = new Image(imageFile);
        myIcon.setImage(myImage);
        myIcon.setFitHeight(size.getSize());
        myIcon.setFitWidth(size.getSize());
    }

    public ImageView getView() {
        return myIcon;
    }
}
