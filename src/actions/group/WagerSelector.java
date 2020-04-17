package actions.group;

@FunctionalInterface
public interface WagerSelector {

    double getBet(double min, double max);

}
