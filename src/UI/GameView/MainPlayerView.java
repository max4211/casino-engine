package UI.GameView;

import UI.Interfaces.NodeViewInterface;
import UI.Interfaces.TaggableInterface;
import UI.LanguageBundle;
import UI.Selectors.SelectorReadyInput;
import UI.Selectors.SelectorType;
import Utility.Formatter;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class MainPlayerView implements NodeViewInterface, TaggableInterface {

    private PlayerView myPlayer;
    private HBox myView;
    private Formatter myFormatter;
    private SelectorReadyInput myReadyButton;

    private static final Pos MAIN_PLAYER_ALIGNMENT = Pos.CENTER;

    public MainPlayerView(LanguageBundle languageBundle) {
        myView = new HBox();
        myFormatter = new Formatter();
        myFormatter.formatUnfixedCenter(myView);
        myReadyButton = new SelectorReadyInput(languageBundle);
    }

    public void setMainPlayer(PlayerView newMainPlayer) {
        myPlayer = newMainPlayer;
        myPlayer.getView().setAlignment(MAIN_PLAYER_ALIGNMENT);
        myView.getChildren().add(myPlayer.getView());
    }

    public void clear() {
        myView.getChildren().clear();;
        myPlayer = null;
    }

    public PlayerView getMainPlayer() {
        return myPlayer;
    }

    public boolean hasSameID(int playerID) {
        return myPlayer.hasSameID(playerID);
    }

    public boolean holdsAPlayer() {
        return myPlayer != null;
    }

    @Override
    public HBox getView() {
        return myView;
    }

    public void waitUntilReady(SelectorType mySelectionType) {
        myReadyButton.pauseUntilReady(myView, mySelectionType);
    }

    public void updateLanguage() {
        if (myPlayer != null) myPlayer.updateLanguage();
        myReadyButton.updateLanguage();
    }
}
