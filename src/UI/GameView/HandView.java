package UI.GameView;

import UI.Interfaces.StylizedNode;
import UI.Utilities.CardTriplet;
import UI.Utilities.Formatter;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents the visual representation of a Hand of cards which are individually built with a CardView object.
 * The Cards are held in a List internally and visually displayed by means of an HBox.
 * This class exists as an instance variable composed into a BetView object.
 * Implements the StylizedNode interface, which allows access to the HBox instance variable myHand that has a CSS ID of HandView.
 * @author Eric Doppelt
 */
public class HandView implements StylizedNode {

    private HBox myHand;
    private List<CardView> myCards;

    /**
     * Constructor that initializes the hand. This converts a list of CardTriplets into an HBox holding CardViews.
     * An empty list can be passed in to represent an empty hand.
     * Formatter object is called to format the HBox and set its CSS ID to HandView.
     * @param allCards is the cards that are in the hand at construction, represented with CardViews. Cards can be added later on.
     * @param cardImage is the image to show when a card is hidden, assumed to be of a face down card. This is passed straight into the CardView and not stored as state.
     */
    public HandView(List<CardTriplet> allCards, String cardImage) {
        myHand = new HBox();
        StylizedNode.setStyleID(myHand, this.getClass());
        Formatter.formatHandView(myHand);
        myCards = new ArrayList<>();

        for (CardTriplet cardInfo : allCards) {
            CardView tempCardView = new CardView(cardInfo, cardImage);
            myCards.add(tempCardView);
            myHand.getChildren().add(tempCardView.getView());
        }
    }

    /**
     * Returns the HBox representing the Hand from a visual standpoint using JavaFX.
     * @return HBox that is the instance variable myHand, containing CardView objects.
     */
    @Override
    public HBox getView() {
        return myHand;
    }

    /**
     * Adds the card given as a parameter to the list of CardViews and in the HBox showing all cards.
     * This is added no matter what and is called from the addCard and addCardIfAbsent methods in BetView.
     * @param newCard is the information for the new card to add.
     * @param cardImage is the name of the image to show when the card is face down. This is assumed to be in iconImages.
     */
    public void addCard(CardTriplet newCard, String cardImage) {
        CardView addedCardView = new CardView(newCard, cardImage);
        myCards.add(addedCardView);
        myHand.getChildren().add(addedCardView.getView());
    }

    /**
     * Method which checks if a card is held and displayed in the HandView.
     * @param cardID is the tracking ID of the card to look for.
     * @return true if the card is being held in the myCards list of CardViews (and therefore displayed in the HBox).
     */
    public boolean hasCard(int cardID) {
        return getCard(cardID) != null;
    }

    /**
     * Prompts a CardView object with the ID given as a parameter to update its visual representation to the "hidden" state.
     * If no card has the ID, a null pointer exception will be thrown. This could be fixed by employing an implementation similar to showCard().
     * If the card is already hidden, no change will occur.
     * This method enacts the actual change my calling the hideCard() method of the same name on the CardView object specified.
     * @param cardID is the tracking id of the card to hide.
     */
    public void hideCard(int cardID) {
        getCard(cardID).hideCard();
    }

    /**
     * Prompts a CardView object with the ID given as a parameter to update its visual representation to the "showing" state.
     * If no card has the ID, no change will occur.
     * If the card is already showing, no change will occur.
     * This method enacts the actual change my calling the showCard() method of the same name on the CardView object specified.
     * @param id is the tracking id of the card to show.
     */
    public void showCard(int id) {
        for (CardView tempCard : myCards) {
            if (tempCard.hasSameID(id)) {
                tempCard.showCard();
            }
        }
    }

    /**
     * This removes all cards from the list of cards held and likewise removes all children of the displayed HBox.
     * This is usually called at the end of a turn.
     */
    public void clearHand() {
        myCards.clear();
        myHand.getChildren().clear();
    }

    private CardView getCard(int id) {
        for (CardView tempCardView : myCards) {
            if (tempCardView.hasSameID(id)) return tempCardView;
        }
        return null;
    }
 }
