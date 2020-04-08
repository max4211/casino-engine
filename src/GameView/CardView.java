package GameView;

import Formatting.Formatter;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collection;

public class CardView implements ViewInterface {

    private VBox myCard;
    private Collection myCardNodes;

    private static final double CARD_HEIGHT = 88.9;
    // TODO: bind the width to the BetView, does simple math with it
    private static final double CARD_WIDTH = 56;
    private static final double CORNER_RADIUS = 5;
    private static final Formatter myFormatter = new Formatter();

    private static final int FULL_BACKGROUND_FILL = 1;
    private static final boolean FILL_AS_PERCENT = true;
    private static final boolean BACKGROUNDFILL_CONTAIN = false;
    private static final boolean BACKGROUNDFILL_COVER = false;

    // TODO: make this data driven!
    private String cardFilePath = "././data/cardImages/fancyCardDown.png";
    private Image cardImage = new Image(cardFilePath);

    private static final BackgroundSize FULL_BACKGROUND_SIZE = new BackgroundSize(FULL_BACKGROUND_FILL, FULL_BACKGROUND_FILL, FILL_AS_PERCENT, FILL_AS_PERCENT, BACKGROUNDFILL_CONTAIN, BACKGROUNDFILL_COVER);
    private BackgroundImage hiddenBackgroundImage = new BackgroundImage(cardImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, FULL_BACKGROUND_SIZE);

    // TODO: make this data driven!
    private static final Color showingColor = Color.web("FF6464");

    public CardView(Pair<Double, String> cardInfo) {
        myCard = new VBox();
        myFormatter.formatFixedVBox(myCard, CARD_HEIGHT, CARD_WIDTH);
        createCardNodes(cardInfo.getKey(), cardInfo.getValue());
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
