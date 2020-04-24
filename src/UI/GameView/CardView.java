package UI.GameView;

import UI.Interfaces.TaggableNode;
import UI.Interfaces.StylizedNode;
import Utility.CardTriplet;
import Utility.Formatter;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collection;

public class CardView implements StylizedNode, TaggableNode {

    private VBox myCard;
    private Collection myCardNodes;
    private int myID;

    private static final double CARD_HEIGHT = 100;
    // TODO: bind the width to the BetView, does simple math with it
    private static final double CARD_WIDTH = 70;
    private static final double CORNER_RADIUS = 5;
    private static final Formatter myFormatter = new Formatter();

    // TODO: make this data driven!
    private static final String PATH_TO_CARD_IMAGE = "cardImages/";
    private static final String DEFAULT_IMAGE = "fancyCardDown.png";
    private Image cardImage = new Image(PATH_TO_CARD_IMAGE.concat(DEFAULT_IMAGE));

    private static final int FULL_BACKGROUND_FILL = 1;
    private static final boolean FILL_AS_PERCENT = true;
    private static final boolean BACKGROUNDFILL_CONTAIN = false;
    private static final boolean BACKGROUNDFILL_COVER = false;
    private static final BackgroundSize FULL_BACKGROUND_SIZE = new BackgroundSize(FULL_BACKGROUND_FILL, FULL_BACKGROUND_FILL, FILL_AS_PERCENT, FILL_AS_PERCENT, BACKGROUNDFILL_CONTAIN, BACKGROUNDFILL_COVER);
    private BackgroundImage hiddenBackgroundImage = new BackgroundImage(cardImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, FULL_BACKGROUND_SIZE);

    // TODO: make this data driven!
    private static final Color showingColor = Color.web("FF6464");
    private static final String CARD_VIEW_CSS_ID = "card-view";

    public CardView(CardTriplet cardInfo) {
        myCard = new VBox();
        myCard.setId(CARD_VIEW_CSS_ID);
        myID = cardInfo.getID();
        myFormatter.formatFixedVBox(myCard, CARD_HEIGHT, CARD_WIDTH);
        createCardNodes(cardInfo.getValue(), cardInfo.getSuit());
        hideCard();
    }

    public CardView(CardTriplet cardInfo, String pathToCardImage) {
        this(cardInfo);
        System.out.println(hiddenBackgroundImage);
    }

    //TODO: way to make this unmodifiable?
    public VBox getView() {
        return myCard;
    }

    public boolean hasSameID(int id) { return myID == id;}

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
