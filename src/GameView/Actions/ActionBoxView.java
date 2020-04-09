package GameView.Actions;

import GameView.ViewInterface;
import Utility.Formatter;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.List;
import java.util.function.Consumer;

public class ActionBoxView implements ViewInterface {

    private HBox myActions;
    private Formatter myFormatter;


    public ActionBoxView(List<String> allActions, Pane parent) {
        myActions = new HBox();
        myFormatter = new Formatter();
        myFormatter.formatUnfixedHBox(myActions);
        for (String action : allActions) {
            Button addedButton = new Button(action);
            addedButton.setOnAction(e -> selectAction(action, parent));
            myActions.getChildren().add(addedButton);
        }
    }

    public HBox getView() {
        return myActions;
    }

    private void selectAction(String chosenAction, Pane parent) {

    }
}
