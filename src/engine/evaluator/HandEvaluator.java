package engine.evaluator;

import engine.hand.ClassifiedHand;
import engine.hand.PlayerHand;

public class HandEvaluator implements HandEvaluatorInterface {

    public HandEvaluator() {

    }

    @Override
    public int compare(PlayerHand playerHand1, PlayerHand playerHand2) {
        if (playerHand1.isLoser() && !playerHand2.isLoser()) {
            return 1;
        } else if (playerHand2.isLoser() && !playerHand1.isLoser()) {
            return -1;
        } else {
            return compareClassification(playerHand1.getClassification(), playerHand2.getClassification());
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
