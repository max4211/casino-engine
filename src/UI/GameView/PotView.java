package UI.GameView;

import UI.Interfaces.NodeViewInterface;
import UI.LanguageBundle;
import UI.LobbyView.Icon;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PotView implements NodeViewInterface {

    private VBox myVBox;
    private HBox myHBox;
    private LanguageBundle myLanguageBundle;
    private static final int NEW_POT_INDEX = 1;
    private static final String POT_KEY = "Pot";

    public PotView(LanguageBundle languageBundle, double initialAmount, String iconFile) {
        myVBox = new VBox();
        myVBox.setAlignment(Pos.CENTER);

        myHBox = new HBox();
        myHBox.setAlignment(Pos.CENTER);

        myLanguageBundle = languageBundle;

        Icon potIcon = new Icon(iconFile);
        myVBox.getChildren().add(potIcon.getView());

        Label myPotAmount = new Label(languageBundle.getBundle().getString(POT_KEY));
        myVBox.getChildren().add(myPotAmount);
        setPot(initialAmount);
    }

    public void setPot(double newAmount) {
        if (myHBox.getChildren().size() > 1) {
            myHBox.getChildren().remove(NEW_POT_INDEX);
        }
        myHBox.getChildren().add(NEW_POT_INDEX, new Label(String.valueOf(newAmount)));
    }

    @Override
    public VBox getView() {
        return myVBox;
    }
}
