package UI.Utilities;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Formatter {

    private static final double CARD_HEIGHT = 100;
    private static final int CARD_WIDTH = 70;
    private static final int NO_CARDS = 0;

    private static final int GAME_STARTER_HEIGHT = 150;
    private static final int GAME_STARTER_WIDTH = 75;

    private static final int FILE_ICON_VERTICAL_SPACING = 8;
    private static final int GAP_BETWEEN_SETTINGS_AND_GAMESTARTERS = 10;

    private static final int ALL_FILES_WIDTH = 175;
    private static final int ALL_FILES_HEIGHT = 400;
    private static final double HALF_ALL_FILES_WIDTH = ALL_FILES_WIDTH / 2;

    private static final int FILE_ICON_SPACING = 5;

    private static final int BET_INFO_HEIGHT = 40;
    private static final int MIN_BET_WIDTH = 100;

    private static final int PLAYER_INFO_VIEW_WIDTH = 100;

    public void formatFixedVBox(VBox rawVBox, double height, double width) {
        rawVBox.setMinHeight(height);
        rawVBox.setMaxHeight(height);

        rawVBox.setMinWidth(width);
        rawVBox.setMaxWidth(width);

        rawVBox.setAlignment(Pos.CENTER);
    }

    public static void formatBetView(VBox betViewBox, int numberCards) {
        if (numberCards == NO_CARDS) betViewBox.setPrefWidth(MIN_BET_WIDTH);
        else updateBetViewWidth(betViewBox, numberCards);
        betViewBox.setPrefHeight(CARD_HEIGHT + BET_INFO_HEIGHT);
        betViewBox.setAlignment(Pos.CENTER);
    }

    public static void formatShownCardView(VBox shownCardView, Label valueLabel, Label suitLabel) {
        shownCardView.setPrefHeight(CARD_HEIGHT);
        shownCardView.setPrefWidth(CARD_WIDTH);
        shownCardView.setAlignment(Pos.CENTER);
        valueLabel.setTextAlignment(TextAlignment.CENTER);
        suitLabel.setTextAlignment(TextAlignment.CENTER);
    }

    public static void formatHiddenCardView(ImageView hiddenCardView) {
        hiddenCardView.setFitHeight(CARD_HEIGHT);
        hiddenCardView.setFitWidth(CARD_WIDTH);
    }

    public static void updateBetViewWidth(VBox betViewBox, int numberOfCards) {
        betViewBox.setPrefWidth(CARD_WIDTH * numberOfCards);
    }

    public static void formatBetInfoBox(VBox betInfoBox) {
        betInfoBox.setPrefHeight(BET_INFO_HEIGHT);
        betInfoBox.setMinWidth(MIN_BET_WIDTH);
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
        rawButton.setPrefHeight(CARD_HEIGHT + BET_INFO_HEIGHT);
    }

    public static void formatAllFilesBox(VBox allFilesVBox) {
        allFilesVBox.setAlignment(Pos.CENTER);
        allFilesVBox.setSpacing(FILE_ICON_VERTICAL_SPACING);
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

    public static void formatPlayerInfoView(VBox playerInfoViewBox) {
        playerInfoViewBox.setAlignment(Pos.CENTER);
        playerInfoViewBox.setPrefHeight(CARD_HEIGHT + BET_INFO_HEIGHT);
        playerInfoViewBox.setPrefWidth(PLAYER_INFO_VIEW_WIDTH);
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

}
