package UI.Icons;

import UI.Interfaces.StylizedNode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class that creates the functionality of a simple ImageView formatted to an enumerated size and open to extension in subclasses.
 * If no IconSize is given, the default is Normal
 * Implements the StylizedNode interface, returning an ImageView on a getView() call with a CSS ID of Icon.
 */
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
