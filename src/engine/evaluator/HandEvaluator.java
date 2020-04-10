package engine.evaluator;

import engine.hand.ClassifiedHand;
import engine.hand.Hand;

public class HandEvaluator implements HandEvaluatorInterface {

    public HandEvaluator() {

    }

    @Override
    public int compare(Hand hand1, Hand hand2) {
        if (hand1.isLoser() && !hand2.isLoser()) {
            return 1;
        } else if (hand2.isLoser() && !hand1.isLoser()) {
            return -1;
        } else {
            return compareClassification(hand1.getClassification(), hand2.getClassification());
        }
    }

    private int compareClassification(ClassifiedHand h1, ClassifiedHand h2) {
        int rankDiff = h2.getRank() - h1.getRank();
        if (rankDiff != 0) {
            return rankDiff;
        } else {
            return (int) (h1.getPower() - h2.getPower());
        }
    }

}
