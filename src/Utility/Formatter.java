package Utility;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Formatter {

    public Formatter() {}

    public void formatFixedVBox(VBox rawVBox, double height, double width) {
        rawVBox.setMinHeight(height);
        rawVBox.setMaxHeight(height);

        rawVBox.setMinWidth(width);
        rawVBox.setMaxWidth(width);

        rawVBox.setAlignment(Pos.CENTER);
    }

    public void formatUnfixedHBox(HBox rawHBox) {
        rawHBox.setMaxWidth(Double.MAX_VALUE);
        rawHBox.setMaxHeight(Double.MAX_VALUE);
        rawHBox.setAlignment(Pos.CENTER);
    }

    public void formatWideHBox(HBox rawHBox, double amount) {
        formatUnfixedHBox(rawHBox);
        rawHBox.setMinHeight(amount);
        rawHBox.setMaxHeight(amount);
    }

    public void updateVBoxWidth(VBox rawVBox, double newWidth) {
        rawVBox.setMinWidth(newWidth);
        rawVBox.setMaxWidth(newWidth);
    }
}
