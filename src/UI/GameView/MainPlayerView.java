package UI.GameView;

import UI.Interfaces.NodeViewInterface;
import UI.Interfaces.TaggableInterface;
import UI.Selectors.SelectorButton;
import UI.Selectors.SelectorType;
import Utility.Formatter;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class MainPlayerView implements NodeViewInterface, TaggableInterface {

    private PlayerView myPlayer;
    private HBox myView;
    private Formatter myFormatter;
    private boolean isReadyForAction;
    private static final boolean READY = true;
    private static final boolean NOT_READY = false;


    private static final Pos MAIN_PLAYER_ALIGNMENT = Pos.CENTER;

    public MainPlayerView() {
        myView = new HBox();
        myFormatter = new Formatter();
        myFormatter.formatUnfixedCenter(myView);

        //TODO: move this to CSS
        String css = "-fx-border-color: #52d780;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-width: 3;\n" +
                "-fx-border-style: dashed;\n";
        myView.setStyle(css);
        isReadyForAction = false;
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
        SelectorButton addedButton = new SelectorButton(myView, mySelectionType);
        myView.getChildren().add(addedButton.getView());
        Platform.enterNestedEventLoop(addedButton);
    }

}
