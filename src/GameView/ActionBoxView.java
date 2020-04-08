package GameView;

import Utility.Formatter;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.List;
import java.util.function.Consumer;

public class ActionBoxView implements ViewInterface {

    private HBox myActions;
    private Formatter myFormatter;



    public ActionBoxView(List<String> allActions, Consumer<String> actionReciever) {
        myActions = new HBox();
        myFormatter = new Formatter();
        myFormatter.formatUnfixedHBox(myActions);
        for (String action : allActions) {
            Button addedButton = new Button(action);
            addedButton.setOnAction(e -> selectAction(action, actionReciever));
            myActions.getChildren().add(addedButton);
        }
    }

    public HBox getView() {
        return myActions;
    }

    private void selectAction(String chosenAction, Consumer<String> actionReciever) {
        actionReciever.accept(chosenAction);
    }
}
