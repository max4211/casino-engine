package UI.GameView;

import UI.Interfaces.NodeViewInterface;
import UI.LanguageBundle;
import Utility.Formatter;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.TextAlignment;

public class WagerView implements NodeViewInterface {

    private HBox myWager;

    //TODO: BIND THIS?
    private static final double VIEW_HEIGHT = 20;
    private static final double MIN_VIEW_WIDTH = 106;

    private static final Formatter myFormatter = new Formatter();

    //TODO: make a language option that changes this, keeping it static final to draw attention
    private static final String WAGER_KEY = "Wager";
    private static final String WAGER_VIEW_CSS_ID = "wager-view";
    private static final int LABEL_VALUE_INDEX = 0;
    private static final int WAGER_VALUE_INDEX = 1;

    private LanguageBundle myLanguageBundle;

    public WagerView(double wager, LanguageBundle languageBundle) {
        myWager = new HBox();
        myWager.setId(WAGER_VIEW_CSS_ID);
        myFormatter.formatGrowingHBox(myWager, VIEW_HEIGHT, MIN_VIEW_WIDTH);
        myLanguageBundle = languageBundle;
        myWager.getChildren().add(new Label(languageBundle.getBundle().getString(WAGER_KEY)));
        updateWager(wager);
    }

    public void updateWager(double newWager) {
        if (myWager.getChildren().size() == 2) myWager.getChildren().remove(WAGER_VALUE_INDEX);
        Label updatedWager = new Label(String.valueOf(newWager));
        updatedWager.setTextAlignment(TextAlignment.CENTER);
        myWager.getChildren().add(updatedWager);
        myWager.setHgrow(updatedWager, Priority.ALWAYS);
    }

    public void updateLanguage() {
        myWager.getChildren().remove(LABEL_VALUE_INDEX);
        myWager.getChildren().add(LABEL_VALUE_INDEX, new Label(myLanguageBundle.getBundle().getString(WAGER_KEY)));
    }

    public HBox getView() {
        return myWager;
    }
}

