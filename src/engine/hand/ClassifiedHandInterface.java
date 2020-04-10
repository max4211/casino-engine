package engine.hand;

public interface ClassifiedHandInterface {

    /**
     * Called by hand evaluator to get name of classified hand
     * @return name of hand
     */
    String getName();

    /**
     * Called by hand evaluator to getrank of hand (first comparison)
     * @return
     */
    int getRank();

    /**
     * Called by hand evaluator to get power (second comparison)
     * @return
     */
    double getPower();
}
