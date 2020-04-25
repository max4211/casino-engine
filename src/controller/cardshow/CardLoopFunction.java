package controller.cardshow;

@FunctionalInterface
public interface CardLoopFunction {

    void operate(int pID, int bID, int cID);
}
