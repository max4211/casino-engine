import UI.GameView.BetView;
import engine.dealer.Card;

import java.io.File;
import java.util.List;

/**
 * External frontend API used to render a given game.
 * Called after a XML file is chosen in the UI.LobbyView.
 * New games are created in New Windows.
 */
public interface GameViewInterface {

    /**
     * Renders a Pop Up Exception Warning that displays the message in the given Exception
     * @param e is the Exception containing a message to be dispalyed
     */
    public void displayExceptions(Exception e);

    /**
     * Renders the components of the entire Game that are always displayed, such as a TableView and MenuView
     * @param image is the file of the image of the table to be displayed.
     */
    public void renderGame(File image);

    /**
     * Renders the Adversary, if need be. This is called in BlackJack-like games with a "Dealer" object.
     * Creates a display showing the Adversaries cards.
     */
    public void renderAdversary();

    /**
     * Gives a Card object to be rendered by a player in his Hand collection. Note that this called on for the adversary as well.
     * @param b is the BetView that needs to add the Card to its displayed hand via a CardView
     * @param c
     */
    public void giveCard(BetView b, Card c);

    /**
     * All cards are assumed to not be shown, so the Table must tell the UI when to visualize cards in their CardView.
     * Does nothing if the card is already being shown
     * @param b is the BetView containing the card to be dispalyed
     * @param c is the card to be displayed
     */
    public void showCard(BetView b, Card c);

    /**
     * Shows all cards in the hand of a given BetView.
     * Differs from showCard by applying the same logic to every card in the hand, such as occurs when going all-in in Texas Hold'em.
     * @param b is the BetView whose cards should be shown.
     */
    public void showCards(BetView b);

    /**
     * Displays the actions given in the List parameter, prompting the user to select one.
     * After the user picks one in an ActionBox, that Action is returned back (to the Table, where it is called) to be executed.
     * @param allActions is a List of allActions Strings that the user can select.
     * @return the String chosen by the user corresponding to his action selection
     */
    public String selectAction(List<String> allActions);
}