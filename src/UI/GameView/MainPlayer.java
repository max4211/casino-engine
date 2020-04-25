package UI.GameView;

import UI.Interfaces.StylizedNode;
import UI.Interfaces.TaggableNode;
import UI.Utilities.LanguageBundle;
import UI.Selectors.ReadyButton;
import UI.Selectors.SelectorType;
import UI.Utilities.Formatter;
import javafx.scene.layout.HBox;

public class MainPlayer implements StylizedNode, TaggableNode {

    private PlayerView myPlayer;
    private HBox myMainPlayerView;
    private ReadyButton myReadyButton;

    public MainPlayer(LanguageBundle languageBundle) {
        myMainPlayerView = new HBox();
        Formatter.formatMainPlayer(myMainPlayerView);
        myReadyButton = new ReadyButton(languageBundle);
    }

    public void setMainPlayer(PlayerView newMainPlayer) {
        myPlayer = newMainPlayer;
        myMainPlayerView.getChildren().add(myPlayer.getView());
    }

    public PlayerView getMainPlayer() {
        return myPlayer;
    }

    @Override
    public boolean hasSameID(int playerID) {
        return myPlayer.hasSameID(playerID);
    }

    public boolean holdsAPlayer() {
        return myPlayer != null;
    }

    @Override
    public HBox getView() {
        return myMainPlayerView;
    }

    public void waitUntilReady(SelectorType mySelectionType) {
        myReadyButton.pauseUntilReady(myMainPlayerView, mySelectionType);
    }

    public void updateLanguage() {
        if (myPlayer != null) myPlayer.updateLanguage();
    }

    public void showAllClassification() {
        myPlayer.showAllClassifications();
    }

    public void hideAllClassification() {
        myPlayer.hideAllClassifications();
    }
}
