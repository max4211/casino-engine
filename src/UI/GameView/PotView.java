package UI.GameView;

import UI.Interfaces.StylizedNode;
import UI.LobbyView.Icon;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PotView implements StylizedNode {

    private VBox myVBox;
    private Label myPotLabel;

    public PotView(double initialAmount, String iconFile) {
        myVBox = new VBox();
        myVBox.setAlignment(Pos.CENTER);

        Icon potIcon = new Icon(iconFile);
        myVBox.getChildren().add(potIcon.getView());

        myPotLabel = new Label(String.valueOf(initialAmount));
        myVBox.getChildren().addAll(myPotLabel);
        setPot(initialAmount);
    }

    public void setPot(double newAmount) {
        myPotLabel.setText(String.valueOf(newAmount));
    }

    @Override
    public VBox getView() {
        return myVBox;
    }
}
