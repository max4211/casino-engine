package engine.table;

public interface TableInterface {

    /**
     * Producer for the view to accept strings in response to bet creation
     * @param s is the string that corresponds to the action
     */
    void acceptString(String s);
}
