package actions.group;

@FunctionalInterface
public interface WagerSelector {

    int getBet(int min, int max);

}
