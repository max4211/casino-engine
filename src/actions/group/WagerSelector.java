package actions.group;

/**
 * Functional interface for backend objects to interface with view funcitonality
 * Lambda is passed in for the action to select a wager from the game view
 * @author Max Smith
 */
@FunctionalInterface
public interface WagerSelector {

    /**
     * Linked to lambda in the front end to select a wager
     * @param min is the table minimum
     * @param max is the table maximum
     * @return the selected bet (in the frontend)
     */
    double getBet(double min, double max);

}
