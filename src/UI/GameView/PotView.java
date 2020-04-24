package UI.GameView;

import UI.Interfaces.LanguageUpdater;
import UI.Interfaces.NodeViewInterface;
import UI.LanguageBundle;
import UI.LobbyView.Icon;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PotView implements NodeViewInterface, LanguageUpdater {

    private VBox myVBox;
    private Label myInfoLabel;
    private Label myPotLabel;

    private LanguageBundle myLanguageBundle;

    private static final String POT_KEY = "Pot";

    public PotView(LanguageBundle languageBundle, double initialAmount, String iconFile) {
        myVBox = new VBox();
        myVBox.setAlignment(Pos.CENTER);

        HBox myHBox = new HBox();
        myHBox.setAlignment(Pos.CENTER);

        myLanguageBundle = languageBundle;

        Icon potIcon = new Icon(iconFile);
        myVBox.getChildren().add(potIcon.getView());

        myInfoLabel = new Label(languageBundle.getBundle().getString(POT_KEY));
        myPotLabel = new Label(String.valueOf(initialAmount));
        myVBox.getChildren().addAll(myInfoLabel, myPotLabel);
        setPot(initialAmount);
    }

    public void setPot(double newAmount) {
        myPotLabel.setText(String.valueOf(newAmount));
    }

    @Override
    public VBox getView() {
        return myVBox;
    }

    @Override
    public void updateLanguage() {
        myInfoLabel.setText(myLanguageBundle.getBundle().getString(POT_KEY));
    }
}
