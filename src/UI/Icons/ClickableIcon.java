package UI.Icons;

/**
 * Class that represents an Icon which undergoes some type of action on a click. Methods called by Icons take no parameters and return nothing, as specified by the MethodRunner Functional Interface.
 * Icons must be formatted using the IconSize enumerated type for consistency.
 * @author Eric Doppelt
 */
public class ClickableIcon extends Icon {

    /**
     * Constructor that creates an Icon with the given image, and runs a specific method given by a lambda on a mouse click.
     * The IconSize is put to the default NormalSize, which has a length and width of 40.
     * @param image is the picture of the Icon to display, given as the full path of the image.
     * @param methodOnClick is the method to execute on a button click, given by a MethodRunner lambda expression.
     */
    public ClickableIcon(String image, MethodRunner methodOnClick) {
        super(image);
        myIcon.setOnMouseClicked(e -> methodOnClick.runMethod());
    }

    /**
     * Constructor that creates an Icon with the given image and size, and runs a specific method given by a lambda on a mouse click.
     * @param image is the picture of the Icon to display, given as the full path of the image.
     * @param size is the enumerated type of the size of the icon, given as an IconSize.
     * @param methodOnClick is the method to execute on a button click, given by a MethodRunner lambda expression.
     */
    public ClickableIcon(String image, IconSize size, MethodRunner methodOnClick) {
        super(image, size);
        myIcon.setOnMouseClicked(e -> methodOnClick.runMethod());
    }
}
