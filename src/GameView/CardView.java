package GameView;

import Formatting.Formatter;
import engine.dealer.Card;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collection;

public class CardView implements ViewInterface {

    private VBox myCard;
    private Collection myCardNodes;

    private static final double CARD_HEIGHT = 88.9;
    private static final double CARD_WIDTH = 56;
    private static final double CORNER_RADIUS = 5;
    private static final Formatter myFormatter = new Formatter();


    // TODO: make this data driven!
    private String cardFilePath = "././data/cardImages/fancyCardDown.png";
    private Image cardImage = new Image(cardFilePath);
    private static final BackgroundSize FULL_BACKGROUND_SIZE = new BackgroundSize(1, 1, true, true, false, false);
    private BackgroundImage hiddenBackgroundImage = new BackgroundImage(cardImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, FULL_BACKGROUND_SIZE);

    // TODO: make this data driven!
    private static final Color showingColor = Color.web("FF6464");

    public CardView(Card modelCard) {
        myCard = new VBox();
        myFormatter.formatVBox(myCard, CARD_HEIGHT, CARD_WIDTH);

        createCardNodes(modelCard.getValue(), modelCard.getSuit());
        hideCard();
    }

    //TODO: way to make this unmodifiable?
    public VBox getView() {
        return myCard;
    }

    //TODO: could remove duplication here in a refactoring step
    public void hideCard() {
        updateCardBackground(new Background(hiddenBackgroundImage));
    }

    public void showCard() {
        updateCardBackground(new Background(new BackgroundFill(showingColor, new CornerRadii(CORNER_RADIUS), null)));
        myCard.getChildren().addAll(myCardNodes);
    }

    private void updateCardBackground(Background newBackground) {
        myCard.getChildren().clear();
        myCard.setBackground(newBackground);
    }

    private void createCardNodes(double value, String suit) {
        myCardNodes = new ArrayList();
        Label valueLabel = new Label(String.valueOf(value));
        Label suitLabel = new Label(suit);
        myCardNodes.add(valueLabel);
        myCardNodes.add(suitLabel);
    }
}
