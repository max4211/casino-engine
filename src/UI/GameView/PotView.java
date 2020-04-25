package UI.GameView;

import UI.Icons.Icon;
import UI.Interfaces.StylizedNode;
import UI.Utilities.Formatter;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PotView implements StylizedNode {

    private VBox myPotBox;
    private Label myPotLabel;

    private static final String PATH_TO_ICON = "/iconImages/gameIcons/";

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

    @Override
    public VBox getView() {
        return myPotBox;
    }

    public void setPot(double newAmount) {
        myPotLabel.setText(String.valueOf(newAmount));
    }

    private String formatIconPath(String iconName) {
        return PATH_TO_ICON.concat(iconName);
    }
}
