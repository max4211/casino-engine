package actions.factory;

import actions.group.GroupAction;
import actions.individual.IndividualAction;

/**
 * Iimplemented by action factory
 * @author Max Smith
 */
public interface ActionFactoryInterface {

    /**
     * Uses reflection to create the desired action
     * @param action is the string name denoting the appropriate action to create
     * @return the appropriate action (specified by String input
     */
    IndividualAction createIndividualAction(String action);

    /**
     * Uses reflection to create the desired action
     * @param action is the string name denoting the appropriate action to create
     * @return the appropriate action (specified by String input
     */
    GroupAction createGroupAction(String action);
}
