package GameView;

import Utility.Formatter;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.util.ResourceBundle;

public class WagerView implements ViewInterface {

    private HBox myWager;

    private static final double CORNER_RADIUS = 5;
    private static final Color backgroundColor = Color.web("2FC436");

    private static final Formatter myFormatter = new Formatter();

    //TODO: make a language option that changes this, keeping it static final to draw attention
    private static final String RESOURCE_LANGUAGE = "English";
    private static final String HEADER_KEY = "WagerHeader";
    private ResourceBundle myResources = ResourceBundle.getBundle(RESOURCE_LANGUAGE);

    public WagerView(double wager) {
        myWager = new HBox();
        myFormatter.formatUnfixedHBox(myWager);
        myWager.setAlignment(Pos.CENTER);
        myWager.setBackground(new Background(new BackgroundFill(backgroundColor, new CornerRadii(CORNER_RADIUS), null)));
        updateWager(wager);
    }

    public void updateWager(double newWager) {
        if (checkWager(newWager)) {
            myWager.getChildren().clear();
            Label updatedWager = new Label(myResources.getString(HEADER_KEY) + String.valueOf(newWager));
            updatedWager.setTextAlignment(TextAlignment.CENTER);
            myWager.getChildren().add(updatedWager);
            myWager.setHgrow(updatedWager, Priority.ALWAYS);
        } else {
            //TODO: throw an error popup
        }
    }

    public HBox getView() {
        return myWager;
    }

    private boolean checkWager(double amount) {
        return amount > 0;
    }
}

