package UI.GameView;

import UI.Interfaces.NodeViewInterface;
import Utility.Formatter;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.util.ResourceBundle;

public class WagerView implements NodeViewInterface {

    private HBox myWager;

    //TODO: BIND THIS?
    private static final double VIEW_HEIGHT = 20;
    private static final double MIN_VIEW_WIDTH = 106;
    private static final Color backgroundColor = Color.web("2FC436");

    private static final Formatter myFormatter = new Formatter();

    //TODO: make a language option that changes this, keeping it static final to draw attention
    private static final String RESOURCE_LANGUAGE = "English";
    private static final String HEADER_KEY = "Wager";
    private ResourceBundle myResources = ResourceBundle.getBundle(RESOURCE_LANGUAGE);

    public WagerView(double wager) {
        myWager = new HBox();
        myFormatter.formatGrowingHBox(myWager, VIEW_HEIGHT, MIN_VIEW_WIDTH);
        myFormatter.updateBackground(myWager, backgroundColor);
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

