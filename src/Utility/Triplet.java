package Utility;

/**
 * A simple example used in many tutorials.
 *
 * Replicates the functionality of Map.Entry, but is perhaps simpler to use.
 *
 * @author Robert C. Duvall, Max A. Smith
 */
public class Triplet<A, B, C> {
    // immutable instance variables
    // NOTE: these can be any two types, same or different
    private final A myFirst;
    private final B mySecond;
    private final C myThird;


    /**
     * Create a pair directly from the given values
     */
    public Triplet(A first, B second, C third) {
        this.myFirst = first;
        this.mySecond = second;
        this.myThird = third;
    }

    // NOTE: provides getters, but not setters

    /**
     * Returns first value in triplet
     */
    public A getFirst() {
        return this.myFirst;
    }

    /**
     * Returns second value in triplet
     */
    public B getSecond() {
        return this.mySecond;
    }

    /**
     * Returns third value in triplet
     */
    public C getThrd() {
        return this.myThird;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "(" + this.myFirst + ", " + this.mySecond + ", " + this.myThird + ")";
    }
}

