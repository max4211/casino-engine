package GameView.NodeViews;

import GameView.NodeViews.Interfaces.NodeViewInterface;
import Utility.Formatter;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.util.ResourceBundle;

public class WagerView implements NodeViewInterface {

    private HBox myWager;

    private static final double CORNER_RADIUS = 5;
    //TODO: BIND THIS?
    private static final double VIEW_HEIGHT = 20;
    private static final Color backgroundColor = Color.web("2FC436");

    private static final Formatter myFormatter = new Formatter();

    //TODO: make a language option that changes this, keeping it static final to draw attention
    private static final String RESOURCE_LANGUAGE = "English";
    private static final String HEADER_KEY = "Wager";
    private ResourceBundle myResources = ResourceBundle.getBundle(RESOURCE_LANGUAGE);

    public WagerView(double wager) {
        myWager = new HBox();
        myFormatter.formatWideHBox(myWager, VIEW_HEIGHT);
        myWager.setBackground(new Background(new BackgroundFill(backgroundColor, new CornerRadii(CORNER_RADIUS), null)));
        updateWager(wager);
    }

    public void updateWager(double newWager) {
        myWager.getChildren().clear();
        Label updatedWager = new Label(myResources.getString(HEADER_KEY) + newWager);
        updatedWager.setTextAlignment(TextAlignment.CENTER);
        myWager.getChildren().add(updatedWager);
        myWager.setHgrow(updatedWager, Priority.ALWAYS);
    }

    public HBox getView() {
        return myWager;
    }
}

