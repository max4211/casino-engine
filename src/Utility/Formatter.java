package Utility;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Formatter {

    private static final double CORNER_RADIUS = 5;

    public Formatter() {}

    public void formatFixedVBox(VBox rawVBox, double height, double width) {
        rawVBox.setMinHeight(height);
        rawVBox.setMaxHeight(height);

        rawVBox.setMinWidth(width);
        rawVBox.setMaxWidth(width);

        rawVBox.setAlignment(Pos.CENTER);


    }

    // TODO: duplication here, talk to DUVALL
    public void formatUnFixedVBox(VBox rawVBox) {
        rawVBox.setMaxWidth(Double.MAX_VALUE);
        rawVBox.setMaxHeight(Double.MAX_VALUE);
        rawVBox.setAlignment(Pos.CENTER);
    }

    public void formatUnfixedHBox(HBox rawHBox) {
        rawHBox.setMaxWidth(Double.MAX_VALUE);
        rawHBox.setMaxHeight(Double.MAX_VALUE);
        rawHBox.setAlignment(Pos.CENTER);
    }

    public void formatGrowingHBox(HBox rawHBox, double height, double minWidth) {
        formatUnfixedHBox(rawHBox);
        rawHBox.setMinHeight(height);
        rawHBox.setMaxHeight(height);

        rawHBox.setMinWidth(minWidth);
    }

    public void updateVBoxWidth(VBox rawVBox, double newWidth) {
        rawVBox.setMinWidth(newWidth);
        rawVBox.setMaxWidth(newWidth);
    }

    public void updateBackground(Pane rawPane, Color newColor) {
        rawPane.setBackground(new Background(new BackgroundFill(newColor, new CornerRadii(CORNER_RADIUS), null)));
    }
}
