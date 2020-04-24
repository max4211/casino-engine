package UI.GameView;

import UI.Interfaces.StylizedNode;
import UI.LobbyView.Icon;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PotView implements StylizedNode {

    private VBox myVBox;
    private Label myPotLabel;

    private static final String PATH_TO_ICON = "/iconImages/gameIcons/";

    public PotView(double initialAmount, String iconName) {
        myVBox = new VBox();
        myVBox.setAlignment(Pos.CENTER);

        String pathToIcon = formatIconPath(iconName);
        Icon potIcon = new Icon(pathToIcon);
        myVBox.getChildren().add(potIcon.getView());

        myPotLabel = new Label(String.valueOf(initialAmount));
        myVBox.getChildren().addAll(myPotLabel);
        setPot(initialAmount);
    }

    public void setPot(double newAmount) {
        myPotLabel.setText(String.valueOf(newAmount));
    }

    private String formatIconPath(String iconName) {
        return PATH_TO_ICON.concat(iconName);
    }

    @Override
    public VBox getView() {
        return myVBox;
    }
}
