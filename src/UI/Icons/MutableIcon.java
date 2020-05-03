package UI.Icons;

import javafx.scene.image.Image;

/**
 * Subclass of Icon that allows the image to be changed. This is used primarily to display the status of XML files that are in the validation process and updated often.
 * Icon size is specified by the enumerated type IconSize, with the default size being Normal (side length of 40).
 * Icon image is updated with the single setImage() method.
 */
public class MutableIcon extends Icon {

    /**
     * Basic constructor that takes in the full path to an image and sets the image of the Icon to it.
     * IconSize defaults to Normal.
     * @param imageName is the picture of the Icon to display, given as the full path of the image.
     */
    public MutableIcon(String imageName) {
        super(imageName);
    }

    /**
     * Basic constructor that takes in the full path to an image and sets the image of the Icon to it with the specified size.
     * IconSize is either Normal or Large.
     * @param imageName is the picture of the Icon to display, given as the full path of the image.
     * @param size is the size of the image, given as an enumerated type as Normal or Large.
     *
     */
    public MutableIcon(String imageName, IconSize size) {
        super(imageName, size);
    }


    /**
     * Basic setter method that updates the image of the icon to a new displayed image.
     * @param newImageFilePath is the new image to display as the Icon, given as the full path to it.
     */
    public void setImage(String newImageFilePath) {
        Image newImage = new Image(newImageFilePath);
        myIcon.setImage(newImage);
    }
}
