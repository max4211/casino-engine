package engine.hand;

public interface ClassifiedHandInterface {

    /**
     * Called by hand evaluator to get name of classified hand
     * @return name of hand
     */
    String getName();

    /**
     * Called by hand evaluator to getrank of hand (first comparison)
     * @return the rank of the hand
     */
    int getRank();

    /**
     * Called by hand evaluator to get power (second comparison)
     * @return power of the hand
     */
    double getPower();

    /**
     * Called by bet eavluator to determine hand multipllier
     * @return multiplier of the hand (e.g. blackjack is x1.5)
     */
    double getMultiplier();

    /**
     * Setter on name to override in certain instances where xml is not complete
     * @param name of the classified hand (configured from bundle or defaults)
     */
    void setName(String name);
}
