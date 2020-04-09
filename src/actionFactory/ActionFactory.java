package actionFactory;

import java.util.List;

/**
 * Attempting to mimic a "static class" behavior
 */
public class ActionFactory {

    private List<String> myPossibleActions;

    public ActionFactory(List<String> possibleActions) {
        this.myPossibleActions = possibleActions;
    }

}
