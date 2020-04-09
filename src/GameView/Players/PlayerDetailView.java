package GameView.Players;

import GameView.ViewInterface;
import Utility.Formatter;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ResourceBundle;

public class PlayerDetailView implements ViewInterface {

    private VBox myDetails;
    private Formatter myFormatter;

    private static final double CORNER_RADIUS = 5;
    private static final Color backgroundColor = Color.web("F3B6FF");

    private static final String RESOURCE_LANGUAGE = "English";
    private static final String NAME_KEY = "Name";
    private static final String BANK_KEY = "Bank";
    private static final int BANK_INDEX = 1;

    // TODO: MAKE A UTILITY CLASS WITH THESE VALUES
    private static final double CARD_HEIGHT = 88.9;
    private static final double WAGER_HEIGHT = 20;
    private static final double VIEW_WIDTH = 100;

    private ResourceBundle myResources = ResourceBundle.getBundle(RESOURCE_LANGUAGE);

    // TODO: noticing some similarities with WagerView, overlap
    public PlayerDetailView(String name, double bankroll) {
        myDetails = new VBox();
        myFormatter = new Formatter();
        myFormatter.formatFixedVBox(myDetails, CARD_HEIGHT + WAGER_HEIGHT, VIEW_WIDTH);
        myDetails.setBackground(new Background(new BackgroundFill(backgroundColor, new CornerRadii(CORNER_RADIUS), null)));

        Label nameLabel = new Label(myResources.getString(NAME_KEY) + name);
        myDetails.getChildren().add(nameLabel);
        myDetails.setVgrow(nameLabel, Priority.ALWAYS);
        updateWager(bankroll);
    }

    public VBox getView() {
        return myDetails;
    }

    public void updateWager(double amount) {
        if (amount >= 0) {
            if (myDetails.getChildren().size() == 2) myDetails.getChildren().remove(BANK_INDEX);
             myDetails.getChildren().add(new Label(myResources.getString(BANK_KEY) + amount));
        }
    }
}
