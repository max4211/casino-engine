package UI.Utilities;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Formatter {

    private static final double CORNER_RADIUS = 5;

    private static final double CARD_HEIGHT = 100;
    private static final double WAGER_HEIGHT = 20;

    private static final int GAME_STARTER_HEIGHT = 150;
    private static final int GAME_STARTER_WIDTH = 75;

    public void formatFixedVBox(VBox rawVBox, double height, double width) {
        rawVBox.setMinHeight(height);
        rawVBox.setMaxHeight(height);

        rawVBox.setMinWidth(width);
        rawVBox.setMaxWidth(width);

        rawVBox.setAlignment(Pos.CENTER);
    }



    // TODO: duplication here, talk to DUVALL
    public void formatUnfixedCenter(VBox rawVBox) {
        setMaxBounds(rawVBox);
        rawVBox.setAlignment(Pos.CENTER);
    }

    public static void formatGameStarter(VBox rawVBox) {
        rawVBox.setAlignment(Pos.CENTER);
        rawVBox.setPrefHeight(GAME_STARTER_HEIGHT);
        rawVBox.setPrefWidth(GAME_STARTER_WIDTH);
    }

    public void formatUnfixedCenter(HBox rawHBox) {
        setMaxBounds(rawHBox);
        rawHBox.setAlignment(Pos.CENTER);
    }

    public void formatUnfixedLeft(VBox rawVBox) {
        setMaxBounds(rawVBox);
        rawVBox.setAlignment(Pos.CENTER_LEFT);
    }

    public void formatUnfixedLeft(HBox rawHBox) {
        setMaxBounds(rawHBox);
        rawHBox.setAlignment(Pos.CENTER_LEFT);
    }

    public static void formatSelectorButton(Button rawButton) {
        rawButton.setPrefHeight(CARD_HEIGHT + WAGER_HEIGHT);
    }

    private void setMaxBounds(Pane rawPane) {
        rawPane.setMaxWidth(Double.MAX_VALUE);
        rawPane.setMaxHeight(Double.MAX_VALUE);
    }

    public void formatGrowingHBox(HBox rawHBox, double height, double minWidth) {
        formatUnfixedCenter(rawHBox);
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
