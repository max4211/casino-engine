package UI.GameView;

import UI.Interfaces.StylizedNode;
import UI.Interfaces.TaggableNode;
import UI.Utilities.CardTriplet;
import UI.Utilities.Formatter;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CardView implements StylizedNode, TaggableNode {

    private Pane myCardView;
    private VBox myShownCard;
    private ImageView myHiddenCard;
    private int myID;

    private static final String SHOWN_CSS_ID = "shown-card";
    private static final String HIDDEN_CSS_ID = "hidden-card";

    private static final String PATH_TO_CARD_IMAGE = "iconImages/gameIcons/";

    public CardView(CardTriplet cardInfo, String cardImage) {
        myCardView = new Pane();
        StylizedNode.setStyleID(myCardView, this.getClass());
        myID = cardInfo.getID();

        createHiddenCard(cardImage);
        createShownCard(cardInfo.getValue(), cardInfo.getSuit());
        hideCard();
    }

    @Override
    public Pane getView() {
        return myCardView;
    }

    @Override
    public boolean hasSameID(int id) { return myID == id;}

    public void hideCard() {
        myCardView.getChildren().clear();
        myCardView.getChildren().add(myHiddenCard);
    }

    public void showCard() {
        myCardView.getChildren().clear();
        myCardView.getChildren().add(myShownCard);
    }

    private void createHiddenCard(String cardImage) {
        String pathToCard = getPathToCardImage(cardImage);
        Image hiddenCardImage = new Image(pathToCard);
        myHiddenCard = new ImageView(hiddenCardImage);
        myHiddenCard.setId(HIDDEN_CSS_ID);
        Formatter.formatHiddenCardView(myHiddenCard);
    }

    private void createShownCard(double value, String suit) {
        myShownCard = new VBox();
        Label valueLabel = new Label(String.valueOf(value));
        myShownCard.getChildren().add(valueLabel);
        Label suitLabel = new Label(suit);
        myShownCard.getChildren().add(suitLabel);
        myShownCard.setId(SHOWN_CSS_ID);
        Formatter.formatShownCardView(myShownCard, valueLabel, suitLabel);
    }

    private String getPathToCardImage(String cardImageName) {
        return PATH_TO_CARD_IMAGE.concat(cardImageName);
    }
}
