package GameView.NodeViews;

import GameView.NodeViews.Interfaces.NodeViewInterface;
import Utility.Formatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class MainPlayerView implements NodeViewInterface {

    private PlayerView myPlayer;
    private Pane myView;
    private Formatter myFormatter;


    public MainPlayerView() {

    }
    @Override
    public Pane getView() {
        return myView;
    }
}
