/**
 * Module that is tasked with producing Action objects via reflection and then executing them on Bets.
 * Actions update the statuses of Bets by changing their wagers, creating new bets, or turning the active status off (folding).
 * An ActionFactory is used to create these Actions and encapsulate the reflection.
 * Actions are then executed to change Bets.
 */
public interface ActionFactory {

    /**
     * Creates an Action object based off a String through reflection
     * Throws a ClassNotFoundException if the Action type does not exist
     * @param actionType is the Action subclass to create
     * @return the new Action object created
     */
    public Action createAction(String actionType) throws ClassNotFoundException;

    /**
     * Applies the intended effect of an Action on a Bet object.
     * Method is called on a concrete Action subclasses from the module.
     * @param target is the Bet object to implement the changes on
     */
    public void executeAction(Bet target)
}
