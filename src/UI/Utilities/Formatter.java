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

    public static void formatGameStarter(VBox gameStarterBox) {
        gameStarterBox.setAlignment(Pos.CENTER);
        gameStarterBox.setPrefHeight(GAME_STARTER_HEIGHT);
        gameStarterBox.setPrefWidth(GAME_STARTER_WIDTH);
    }

    public static void formatSelectorButton(Button selectorButton) {
        selectorButton.setPrefHeight(CARD_HEIGHT + BET_INFO_HEIGHT);
    }

    public static void formatAllFilesBox(VBox allFilesVBox) {
        allFilesVBox.setAlignment(Pos.CENTER);
        allFilesVBox.setSpacing(FILE_ICON_VERTICAL_SPACING);
    }

    public static void formatAllFilesStage(Stage filesStage) {
        filesStage.setWidth(ALL_FILES_WIDTH);
        filesStage.setHeight(ALL_FILES_HEIGHT);
        filesStage.setX(ScreenPosition.LEFT.getX() - HALF_ALL_FILES_WIDTH);
        filesStage.setY(ScreenPosition.LEFT.getY());
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

    private static void setMaxBounds(Pane rawPane) {
        rawPane.setMaxWidth(Double.MAX_VALUE);
        rawPane.setMaxHeight(Double.MAX_VALUE);
    }

    public static void formatLobbyView(VBox lobbyBox) {
        lobbyBox.setSpacing(GAP_BETWEEN_SETTINGS_AND_GAMESTARTERS);
    }

    public static void formatGameStarterFlowPane(FlowPane gameStarterFlowPane) {
        gameStarterFlowPane.setAlignment(Pos.CENTER);
    }

    public static void formatHandView(HBox handViewBox) {
        setMaxBounds(handViewBox);
        handViewBox.setAlignment(Pos.CENTER);
    }

    public static void formatPot(VBox potBox) {
        potBox.setAlignment(Pos.CENTER);
    }

    public static void formatPlayerView(HBox playerViewBox) {
        setMaxBounds(playerViewBox);
        playerViewBox.setAlignment(Pos.CENTER_LEFT);
    }

    public static void formatOtherPlayers(VBox otherPlayersBox) {
        setMaxBounds(otherPlayersBox);
        otherPlayersBox.setAlignment(Pos.CENTER_LEFT);
    }

    public static void formatMainPlayer(HBox mainPlayerBox) {
        setMaxBounds(mainPlayerBox);
        mainPlayerBox.setAlignment(Pos.CENTER);
    }

    public static void formatGameView(VBox gameViewBox, double width, double height) {
        gameViewBox.setPrefWidth(width);
        gameViewBox.setPrefHeight(height);
    }
}
