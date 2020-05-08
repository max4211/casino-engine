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

/**
 * Class that is used via means of static method calls to format objects displayed in all View files.
 * @author Eric Doppelt
 */
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

    /**
     * Formats a BetVie object based on the number of cards in its hand.
     * @param betViewBox is the VBox representing the BetView
     * @param numberCards is the number of cards in its hand
     */
    public static void formatBetView(VBox betViewBox, int numberCards) {
        if (numberCards == NO_CARDS) betViewBox.setPrefWidth(MIN_BET_WIDTH);
        else updateBetViewWidth(betViewBox, numberCards);
        betViewBox.setPrefHeight(CARD_HEIGHT + BET_INFO_HEIGHT);
        betViewBox.setAlignment(Pos.CENTER);
    }

    /**
     * Formats a CardView that is shown by adding formatting the Label alignment to be centered.
     * @param shownCardView is the VBox representing the shown card.
     * @param valueLabel is the Label holding the value of the card.
     * @param suitLabel is the Label holding the suit of the card.
     */
    public static void formatShownCardView(VBox shownCardView, Label valueLabel, Label suitLabel) {
        shownCardView.setPrefHeight(CARD_HEIGHT);
        shownCardView.setPrefWidth(CARD_WIDTH);
        shownCardView.setAlignment(Pos.CENTER);
        valueLabel.setTextAlignment(TextAlignment.CENTER);
        suitLabel.setTextAlignment(TextAlignment.CENTER);
    }

    /**
     * Formats a CardView that is hidden by simply setting the proper width and height of the image.
     * @param hiddenCardView is the ImageView of the hidden card.
     */
    public static void formatHiddenCardView(ImageView hiddenCardView) {
        hiddenCardView.setFitHeight(CARD_HEIGHT);
        hiddenCardView.setFitWidth(CARD_WIDTH);
    }

    /**
     * Method that is called on a BetView when a new card is added.
     * Sets the width to increase to accommodate the new card.
     * @param betViewBox is the VBox holding the BetView.
     * @param numberOfCards is the number of cards now in the BetView.
     */
    public static void updateBetViewWidth(VBox betViewBox, int numberOfCards) {
        betViewBox.setPrefWidth(CARD_WIDTH * numberOfCards);
    }

    /**
     * Method that formats the BetInfo objects in the UI by setting the proper size and alignment.
     * @param betInfoBox is the VBox representing a BetInfo object.
     */
    public static void formatBetInfoBox(VBox betInfoBox) {
        betInfoBox.setPrefHeight(BET_INFO_HEIGHT);
        betInfoBox.setMinWidth(MIN_BET_WIDTH);
        betInfoBox.setAlignment(Pos.CENTER);
    }

    /**
     * Method that formats the GameStarter in the LobbyView by setting the proper size and alignment.
     * @param gameStarterBox is the VBox of the GameStarter.
     */
    public static void formatGameStarter(VBox gameStarterBox) {
        gameStarterBox.setAlignment(Pos.CENTER);
        gameStarterBox.setPrefHeight(GAME_STARTER_HEIGHT);
        gameStarterBox.setPrefWidth(GAME_STARTER_WIDTH);
    }

    /**
     * Method that formats the Selector Button used to represent a Ready Button. This formats it to the same height as the Main Player View.
     * @param selectorButton is the Button is the ReadyButton's node.
     */
    public static void formatSelectorButton(Button selectorButton) {
        selectorButton.setPrefHeight(CARD_HEIGHT + BET_INFO_HEIGHT);
    }

    /**
     * Method that formats the AllFilesDisplay by setting the proper alignment and spacing.
     * @param allFilesVBox is the VBox containing all the individual FileDisplays.
     */
    public static void formatAllFilesBox(VBox allFilesVBox) {
        allFilesVBox.setAlignment(Pos.CENTER);
        allFilesVBox.setSpacing(FILE_ICON_VERTICAL_SPACING);
    }

    /**
     * Method that formats the stage of the All Files display.
     * This is done by setting the proper coordinates and size of the stage.
     * @param filesStage is the Stage that the AllFilesDisplay is later added to.
     */
    public static void formatAllFilesStage(Stage filesStage) {
        filesStage.setWidth(ALL_FILES_WIDTH);
        filesStage.setHeight(ALL_FILES_HEIGHT);
        filesStage.setX(ScreenPosition.LEFT.getX() - HALF_ALL_FILES_WIDTH);
        filesStage.setY(ScreenPosition.LEFT.getY());
    }

    /**
     * Method that formats an Individual File Display by setting the proper size of the icons in it and and alignment of the VBox.
     * @param fullDisplayBox is the VBox of the entire display.
     * @param iconBox is the HBox containing the three icons in the display (file type, equal sign, and status).
     */
    public static void formatFileDisplay(VBox fullDisplayBox, HBox iconBox) {
        fullDisplayBox.setAlignment(Pos.CENTER);
        iconBox.setAlignment(Pos.CENTER);
        iconBox.setSpacing(FILE_ICON_SPACING);
    }

    /**
     * Method that formats an PlayerInfo object
     * @param playerInfoViewBox is the VBox representing a single PlayerInfo object.
     */
    public static void formatPlayerInfoView(VBox playerInfoViewBox) {
        playerInfoViewBox.setAlignment(Pos.CENTER);
        playerInfoViewBox.setPrefHeight(CARD_HEIGHT + BET_INFO_HEIGHT);
        playerInfoViewBox.setPrefWidth(PLAYER_INFO_VIEW_WIDTH);
    }

    /**
     * Method that formats the LobbyView object by setting the proper spacing.
     * @param lobbyBox is the VBox representing the entire LobbyView.
     */
    public static void formatLobbyView(VBox lobbyBox) {
        lobbyBox.setSpacing(GAP_BETWEEN_SETTINGS_AND_GAMESTARTERS);
    }

    /**
     * Method that formats the LobbyView FlowPane that holds GameStarters by setting the proper alignment.
     * @param gameStarterFlowPane is the FlowPane holding all the GameStarters.
     */
    public static void formatGameStarterFlowPane(FlowPane gameStarterFlowPane) {
        gameStarterFlowPane.setAlignment(Pos.CENTER);
    }

    /**
     * Method that formats the HandView object by setting its bounds to be as large as possible and its alignment to the center.
     * @param handViewBox is the VBox representing a HandView node.
     */
    public static void formatHandView(HBox handViewBox) {
        setMaxBounds(handViewBox);
        handViewBox.setAlignment(Pos.CENTER);
    }

    /**
     * Method that formats the PotView object by setting its alignment.
     * @param potBox is the VBox representing a PotView node.
     */
    public static void formatPot(VBox potBox) {
        potBox.setAlignment(Pos.CENTER);
    }

    /**
     * Method that formats a PlayerView object by settings its bounds to maximum and the proper alignment.
     * @param playerViewBox is the HBox representing a PlayerView object.
     */
    public static void formatPlayerView(HBox playerViewBox) {
        setMaxBounds(playerViewBox);
        playerViewBox.setAlignment(Pos.CENTER_LEFT);
    }

    /**
     * Method that formats the OtherPlayer VBox by setting its max bounds and proper alignment.
     * @param otherPlayersBox is the VBox representing the OtherPlayers object.
     */
    public static void formatOtherPlayers(VBox otherPlayersBox) {
        setMaxBounds(otherPlayersBox);
        otherPlayersBox.setAlignment(Pos.CENTER_LEFT);
    }

    /**
     * Method that formats the MainPlayer HBox by setting its max bounds and proper alignment.
     * @param mainPlayerBox is the HBox representing the MainPlayer object.
     */
    public static void formatMainPlayer(HBox mainPlayerBox) {
        setMaxBounds(mainPlayerBox);
        mainPlayerBox.setAlignment(Pos.CENTER);
    }

    /**
     * Formats the entire GameView object by settings its data-driven height and width.
     * @param gameViewBox is the VBox representing the entire GameView.
     * @param width is the width of the GameView.
     * @param height is the height of the GameView.
     */
    public static void formatGameView(VBox gameViewBox, double width, double height) {
        gameViewBox.setPrefWidth(width);
        gameViewBox.setPrefHeight(height);
    }

    private static void setMaxBounds(Pane rawPane) {
        rawPane.setMaxWidth(Double.MAX_VALUE);
        rawPane.setMaxHeight(Double.MAX_VALUE);
    }
}
