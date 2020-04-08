package GameView;

import Formatting.Formatter;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.List;

public class ActionBoxView implements ViewInterface {

    private HBox myActions;
    private Formatter myFormatter;
    private String myChosenAction;

    private boolean actionIsSelected;

    public ActionBoxView(List<String> allActions) {
        myActions = new HBox();
        myFormatter = new Formatter();
        myFormatter.formatUnfixedHBox(myActions);
        actionIsSelected = false;
        for (String action : allActions) {
            Button addedButton = new Button(action);
            addedButton.setOnAction(e -> selectAction(action));
            myActions.getChildren().add(addedButton);
        }
    }

    public HBox getView() {
        return myActions;
    }

    public boolean getActionIsSelected() {
        return actionIsSelected;
    }

    public String getChosenAction() {
        return myChosenAction;
    }

    private void selectAction(String chosenAction) {
        myChosenAction = chosenAction;
        actionIsSelected = true;
    }
}
