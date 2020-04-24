package UI.IconFactories;

public class ClickableIcon extends Icon {

    public ClickableIcon(String image, MethodRunner methodOnClick) {
        super(image);
        myIcon.setOnMouseClicked(e -> methodOnClick.runMethod());
    }

    public ClickableIcon(String image, IconSize size, MethodRunner methodOnClick) {
        super(image, size);
        myIcon.setOnMouseClicked(e -> methodOnClick.runMethod());
    }
}
