package engine.player;

/**
 * Interface for lists of objects (simplified generic list)
 */
public interface ListInterface {

    /**
     * Add an object to the list
     * @param o object to add to the interface
     */
    void add(Object o);

    /**
     * Remove an object from the list
     * @param o object to add to the interface
     */
    void remove(Object o);

    /**
     * Determine the length (total players at the table)
     * @return length of implemented list
     */
    int length();
}
