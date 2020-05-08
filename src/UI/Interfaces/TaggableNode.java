package UI.Interfaces;

/**
 * Interface used to represent a node that is referenced by means of a tracking ID.
 * Every node's ID comes by means of a hashcode which is unique to it by factoring in HashNoise.
 * Single method described by the interface is used to identify whether the Node has the ID requested.
 * @author Eric Doppelt
 */
public interface TaggableNode {

    /**
     * Simple method that returns true if the node it is called upon has the same tracking ID as the parameter.
     * @param id is the tracking ID that is checked to see if it matches that of the Node's.
     * @return true if the tracking ID of the Node, which is given at construction, matches the parameter. False otherwise.
     */
    boolean hasSameID(int id);

}
