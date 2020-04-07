package Formatting;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class Formatter {

    public Formatter() {}

    public void formatVBox(VBox rawVBox, double height, double width) {
        rawVBox.setMinHeight(height);
        rawVBox.setMaxHeight(height);

        rawVBox.setMinWidth(width);
        rawVBox.setMaxWidth(width);

        rawVBox.setAlignment(Pos.CENTER);
    }
}
