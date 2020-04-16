package UI.GameView;

import UI.Interfaces.NodeViewInterface;
import Utility.Formatter;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ResourceBundle;

public class PlayerInfoView implements NodeViewInterface {

    private VBox myDetails;
    private Formatter myFormatter;

    private static final double CORNER_RADIUS = 5;
    private static final Color backgroundColor = Color.web("F3B6FF");

    private static final String NAME_KEY = "Name";
    private static final String BANK_KEY = "Bank";
    private static final int BANK_INDEX = 1;

    // TODO: MAKE A UTILITY CLASS WITH THESE VALUES
    private static final double CARD_HEIGHT = 100;
    private static final double WAGER_HEIGHT = 20;
    private static final double VIEW_WIDTH = 100;

    private static final String RESOURCE_LANGUAGE = "English";
    private ResourceBundle myResources = ResourceBundle.getBundle(RESOURCE_LANGUAGE);
    private static final String PLAYER_INFO_CSS_ID = "player-info";

    // TODO: noticing some similarities with WagerView, overlap
    public PlayerInfoView(String name, double bankroll) {
        myDetails = new VBox();
        myDetails.setId(PLAYER_INFO_CSS_ID);
        myFormatter = new Formatter();
        myFormatter.formatFixedVBox(myDetails, CARD_HEIGHT + WAGER_HEIGHT, VIEW_WIDTH);
        myDetails.setBackground(new Background(new BackgroundFill(backgroundColor, new CornerRadii(CORNER_RADIUS), null)));


        Label nameLabel = new Label(myResources.getString(NAME_KEY).concat(name));
        myDetails.getChildren().add(nameLabel);
        myDetails.setVgrow(nameLabel, Priority.ALWAYS);
        updateBankroll(bankroll);
    }

    public VBox getView() {
        return myDetails;
    }

    public void updateBankroll(double amount) {
        if (myDetails.getChildren().size() == 2) myDetails.getChildren().remove(BANK_INDEX);
        myDetails.getChildren().add(new Label(myResources.getString(BANK_KEY) + amount));
    }
}

