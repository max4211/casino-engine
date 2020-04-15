package UI.GameView;

import UI.Interfaces.NodeViewInterface;
import UI.Interfaces.TaggableInterface;
import Utility.Formatter;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class MainPlayerView implements NodeViewInterface, TaggableInterface {

    private PlayerView myPlayer;
    private VBox myView;
    private Formatter myFormatter;

    private static final Pos MAIN_PLAYER_ALIGNMENT = Pos.CENTER;

    public MainPlayerView() {
        myView = new VBox();
        myFormatter = new Formatter();
        myFormatter.formatUnfixedCenter(myView);

        String css = "-fx-border-color: #52d780;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-width: 3;\n" +
                "-fx-border-style: dashed;\n";
        myView.setStyle(css);
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
    public VBox getView() {
        return myView;
    }
}
