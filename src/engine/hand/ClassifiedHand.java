package engine.hand;

import Utility.handbundle.HandBundle;

public class ClassifiedHand implements ClassifiedHandInterface {

    private static final double DEFAULT_MULTIPLIER = 1;

    private final String myName;
    private final int myRank;
    private final double myPower;
    private final double myMultiplier;

    public ClassifiedHand(String name, int rank, double power) {
        this.myName = name;
        this.myRank = rank;
        this.myPower = power;
        this.myMultiplier = DEFAULT_MULTIPLIER;
    }

    public ClassifiedHand(HandBundle bundle, int rank, double power) {
        this.myName = String.format("%s (%1.1f)", bundle.getViewName(), power);
        this.myRank = rank;
        this.myPower = power;
        this.myMultiplier = bundle.getMultiplier();
    }

    @Override
    public String getName() {
        return this.myName;
    }

    @Override
    public int getRank() {
        return this.myRank;
    }

    @Override
    public double getPower() {
        return this.myPower;
    }

    @Override
    public double getMultiplier() {
        return this.myMultiplier;
    }
}
