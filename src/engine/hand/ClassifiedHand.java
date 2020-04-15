package engine.hand;

public class ClassifiedHand implements ClassifiedHandInterface {

    private final String myName;
    private final int myRank;
    private final double myPower;

    public ClassifiedHand(String name, int rank, double power) {
        this.myName = name;
        this.myRank = rank;
        this.myPower = power;
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
}
