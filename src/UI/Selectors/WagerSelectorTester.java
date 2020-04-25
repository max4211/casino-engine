package UI.Selectors;

import UI.Utilities.LanguageBundle;
import exceptions.IncompatibleBetRestrictionException;
import exceptions.InvalidBetException;
import exceptions.NoUserInputException;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WagerSelectorTester extends Application {
    @Override
    public void start(Stage primaryStage) throws IncompatibleBetRestrictionException, InvalidBetException, NoUserInputException {
        Pane startingPane = new Pane();
        startingPane.setId("starting-pane");
        LanguageBundle myLanguageBundle = new LanguageBundle("English");

        WagerSelector displayedSelector = new WagerSelector(myLanguageBundle);
        displayedSelector.selectWager(10, -10);
    }
}
