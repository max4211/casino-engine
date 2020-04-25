package UI.Utilities;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Formatter {

    private static final double CORNER_RADIUS = 5;

    private static final double CARD_HEIGHT = 100;
    private static final double WAGER_HEIGHT = 20;

    private static final int GAME_STARTER_HEIGHT = 150;
    private static final int GAME_STARTER_WIDTH = 75;

    private static final int FILE_ICON_VERTICAL_SPACING = 8;
    private static final int GAP_BETWEEN_SETTINGS_AND_GAMESTARTERS = 10;

    private static final int ALL_FILES_WIDTH = 175;
    private static final int ALL_FILES_HEIGHT = 400;
    private static final double HALF_ALL_FILES_WIDTH = ALL_FILES_WIDTH / 2;

    private static final int FILE_ICON_SPACING = 5;

    private static final int BET_INFO_HEIGHT = 40;
    private static final int MIN_BET_INFO_WIDTH = 106;

    public void formatFixedVBox(VBox rawVBox, double height, double width) {
        rawVBox.setMinHeight(height);
        rawVBox.setMaxHeight(height);

        rawVBox.setMinWidth(width);
        rawVBox.setMaxWidth(width);

        rawVBox.setAlignment(Pos.CENTER);
    }

    public static void formatBetInfoBox(VBox betInfoBox) {
        betInfoBox.setPrefHeight(BET_INFO_HEIGHT);
        betInfoBox.setMinWidth(MIN_BET_INFO_WIDTH);
        betInfoBox.setAlignment(Pos.CENTER);
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

    public static void formatAllFilesBox(VBox rawVBox) {
        rawVBox.setAlignment(Pos.CENTER);
        rawVBox.setSpacing(FILE_ICON_VERTICAL_SPACING);
    }

    public static void formatAllFilesStage(Stage rawStage) {
        rawStage.setWidth(ALL_FILES_WIDTH);
        rawStage.setHeight(ALL_FILES_HEIGHT);
        rawStage.setX(ScreenPosition.LEFT.getX() - HALF_ALL_FILES_WIDTH);
        rawStage.setY(ScreenPosition.LEFT.getY());
    }

    public static void formatFileDisplay(VBox fullDisplayBox, HBox iconBox) {
        fullDisplayBox.setAlignment(Pos.CENTER);
        iconBox.setAlignment(Pos.CENTER);
        iconBox.setSpacing(FILE_ICON_SPACING);
    }

    private void setMaxBounds(Pane rawPane) {
        rawPane.setMaxWidth(Double.MAX_VALUE);
        rawPane.setMaxHeight(Double.MAX_VALUE);
    }

    public static void formatLobbyView(VBox rawVBox) {
        rawVBox.setSpacing(GAP_BETWEEN_SETTINGS_AND_GAMESTARTERS);
    }

    public static void formatGameStarterFlowPane(FlowPane rawFlowPane) {
        rawFlowPane.setAlignment(Pos.CENTER);
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

    public static void formatCenteredAndGrowing(HBox rawHBox) {
        rawHBox.setAlignment(Pos.CENTER);
        rawHBox.setMaxWidth(Double.MAX_VALUE);
        rawHBox.setMinWidth(Double.MAX_VALUE);
    }

    public static void formatCenteredGrowingLabel(Label label) {
        label.setAlignment(Pos.CENTER);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setMinWidth(Double.MAX_VALUE);
    }

    public void updateBackground(Pane rawPane, Color newColor) {
        rawPane.setBackground(new Background(new BackgroundFill(newColor, new CornerRadii(CORNER_RADIUS), null)));
    }
}
