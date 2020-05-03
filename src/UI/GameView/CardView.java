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

/**
 * Class that represents a single card in the user interface by means of a JavaFX Pane.
 * The card is toggled between two states, hidden and shown. The view of each state is constructed at initialization, and then calls toggle which state to show at any given time.
 * A hidden card simply shows an image passed as a parameter (assumed to be of a card face down).
 * A shown card displays the value and suit of the card via text.
 * Implements the StylizedNode, returning a Pane containing the card information. The CSS ID iss CardView.
 * Implements the TaggableNode interface and has a hasSameID() method which returns true if the parameter is equal to the Card's tracking ID given at construction.
 */
public class CardView implements StylizedNode, TaggableNode {

    private Pane myCardView;
    private VBox myShownCard;
    private ImageView myHiddenCard;
    private int myID;

    private static final String SHOWN_CSS_ID = "shown-card";
    private static final String HIDDEN_CSS_ID = "hidden-card";

    private static final String PATH_TO_CARD_IMAGE = "iconImages/gameIcons/";

    /**
     * Constructor that initializes a Pane containing all of the information relevant to a single card.
     * The card has two states that are hidden or shown. A hidden card shows an image (assumed to be of a face down card). A shown card displays the suit and value via text.
     * The card is created and set to be hidden at first. It must later be called with a showCard() method to show it.
     * @param cardInfo is the information regarding the single card, containing suit, value, and id.
     * @param cardImage is the image to show if the card is face down (hidden).
     */
    public CardView(CardTriplet cardInfo, String cardImage) {
        myCardView = new Pane();
        StylizedNode.setStyleID(myCardView, this.getClass());
        myID = cardInfo.getID();

        createHiddenCard(cardImage);
        createShownCard(cardInfo.getValue(), cardInfo.getSuit());
        hideCard();
    }

    /**
     * Specified by the StylizedNode interface, this returns the Pane object that the Card is represented with.
     * @return a Pane which represents the Card in the user interface.
     */
    @Override
    public Pane getView() {
        return myCardView;
    }

    /**
     * Specified by the TaggableNode interface, this returns true if the parameter is equal to the Card's tracking ID.
     * @param id is the ID to check to see if the CardView holds.
     * @return true if card holds the tracking ID parameter in its myID instance variable, and false otherwise.
     */
    @Override
    public boolean hasSameID(int id) { return myID == id;}

    /**
     * Hides the card from a visual standpoint. This displays the image given at construction in the pane.
     */
    public void hideCard() {
        myCardView.getChildren().clear();
        myCardView.getChildren().add(myHiddenCard);
    }

    /**
     * Shows the card from a visual standpoint. This displays the suit and value given at construction via text in the pane.
     */
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
