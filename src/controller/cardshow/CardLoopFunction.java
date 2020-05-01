package controller.cardshow;

/**
 * Funcitonal interface used simplify nested logic in cardshow policy (lots of for each loops)
 */
@FunctionalInterface
public interface CardLoopFunction {

    /**
     * Operation performed on specific parameters to mirror view
     * @param pID player id (hash)
     * @param bID bet id (hash)
     * @param cID card id (hash)
     */
    void operate(int pID, int bID, int cID);
}
