package engine.evaluator.handevaluator;

import engine.hand.ClassifiedHand;
import engine.hand.PlayerHand;

/**
 * Effectively a static class which evalutes two hands based on set of criteria
 */
public class HandEvaluator implements HandEvaluatorInterface {

    /**
     * Compares two Hands and determines a winner based on the hierarchy given via an XML file.
     * This method essentially forms the implementation of said hierarchy by determining winners based off of it.
     * @param playerHand1 is the first hand to compare
     * @param playerHand2 is the second hand to compare
     * @return either -1 (first hand wins), 0 (push), or 1 (second hand wins).
     */
    @Override
    public int compare(PlayerHand playerHand1, PlayerHand playerHand2) {
        if (playerHand1.isLoser() && !playerHand2.isLoser()) {
            return -1;
        } else if (playerHand2.isLoser() && !playerHand1.isLoser()) {
            return 1;
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
