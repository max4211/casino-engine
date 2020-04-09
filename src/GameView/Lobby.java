package GameView;

import GameView.NodeViews.GameView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Lobby extends Application {

    private GameView myGameView;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.myGameView = new GameView();
    }

    public GameView getGameView() {
        return this.myGameView;
    }
}
