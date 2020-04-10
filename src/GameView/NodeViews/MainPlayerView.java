package GameView.NodeViews;

import GameView.NodeViews.Interfaces.NodeViewInterface;
import GameView.NodeViews.Interfaces.TaggableInterface;
import Utility.Formatter;
import javafx.scene.layout.VBox;

public class MainPlayerView implements NodeViewInterface, TaggableInterface {

    private PlayerView myPlayer;
    private VBox myView;
    private Formatter myFormatter;


    public MainPlayerView() {
        myView = new VBox();
        myFormatter = new Formatter();
        myFormatter.formatUnFixedVBox(myView);
    }

    public void setMainPlayer(PlayerView newMainPlayer) {
        myPlayer = newMainPlayer;
        myView.getChildren().add(myPlayer.getView());
    }

    public void clearMainPlayer() {
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
