package UI.GameView;

import UI.Icons.Icon;
import UI.Interfaces.StylizedNode;
import UI.Utilities.Formatter;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Visual representation of a Pot used in poker-like games (money held in escrow that is afforded to the winner).
 * The Pot is held in a VBox which contains an image of a pot and the amount held in it.
 * Implements the StylizedNode interface, returning the VBox with a CSS ID of PotView.
 * @author Eric Doppelt
 */
public class PotView implements StylizedNode {

    private VBox myPotBox;
    private Label myPotLabel;

    private static final String PATH_TO_ICON = "/iconImages/gameIcons/";

    /**
     * Basic constructor that initializes the VBox.
     * Image of the VBox is set to the image with the name given. It is assumed that this is in the gameIcons subpackage.
     * @param initialAmount is the amount in the pot at the beginning of the game. This could be nonzero in customized games.
     * Formatter object is called to format the VBox and set its CSS ID to PotView.
     * @param iconName is the name of the image that displays a pot.
     */
    public PotView(double initialAmount, String iconName) {
        myPotBox = new VBox();
        Formatter.formatPot(myPotBox);
        StylizedNode.setStyleID(myPotBox, this.getClass());

        String pathToIcon = formatIconPath(iconName);
        Icon potIcon = new Icon(pathToIcon);
        myPotBox.getChildren().add(potIcon.getView());

        myPotLabel = new Label(String.valueOf(initialAmount));
        myPotBox.getChildren().addAll(myPotLabel);
        setPot(initialAmount);
    }

    /**
     * Method which simply returns the JavaFX representation of the pot.
     * @return VBox containing the image and amount of money held in the pot, whose CSS ID is PotView.
     */
    @Override
    public VBox getView() {
        return myPotBox;
    }

    /**
     * Basic setter method which allows one to update the amount of money displayed by the pot.
     * @param newAmount is a double representing a new amount of money held by the pot in escrow.
     */
    public void setPot(double newAmount) {
        myPotLabel.setText(String.valueOf(newAmount));
    }

    private String formatIconPath(String iconName) {
        return PATH_TO_ICON.concat(iconName);
    }
}
