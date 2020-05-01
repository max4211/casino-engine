package engine.hand;

import Utility.handbundle.HandBundle;

/**
 * Encapsulates parameters for bet evaluation, assigned in HandEvaluator
 * @author Max Smith
 */
public class ClassifiedHand implements ClassifiedHandInterface {

    private static final double DEFAULT_MULTIPLIER = 1;

    private String myName;
    private final int myRank;
    private final double myPower;
    private final double myMultiplier;

    public ClassifiedHand(String name, int rank, double power) {
        this.myName = name;
        this.myRank = rank;
        this.myPower = power;
        this.myMultiplier = DEFAULT_MULTIPLIER;
    }

    /**
     * Constructor for classified hand, using bundles as read in from XML
     * @param bundle is the bundle with name (backend/reflection0, parameters, multiplier, viewname
     * @param rank is the rank of the hand (position in winning stack)
     * @param power is the getPower return from a hand type
     */
    public ClassifiedHand(HandBundle bundle, int rank, double power) {
        this.myName = bundle.getViewName();
        this.myRank = rank;
        this.myPower = power;
        this.myMultiplier = bundle.getMultiplier();
    }

    /**
     * Called by hand evaluator to get name of classified hand
     * @return name of hand
     */
    @Override
    public String getName() {
        return this.myName;
    }

    /**
     * Called by hand evaluator to getrank of hand (first comparison)
     * @return the rank of the hand
     */
    @Override
    public int getRank() {
        return this.myRank;
    }

    /**
     * Called by hand evaluator to get power (second comparison)
     * @return power of the hand
     */
    @Override
    public double getPower() {
        return this.myPower;
    }

    /**
     * Called by bet eavluator to determine hand multipllier
     * @return multiplier of the hand (e.g. blackjack is x1.5)
     */
    @Override
    public double getMultiplier() {
        return this.myMultiplier;
    }

    /**
     * Setter on name to override in certain instances where xml is not complete
     * @param name of the classified hand (configured from bundle or defaults)
     */
    @Override
    public void setName(String name) {
        this.myName = name;
    }
}
