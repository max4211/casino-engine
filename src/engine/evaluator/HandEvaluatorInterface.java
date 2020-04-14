package engine.evaluator;

import engine.hand.PlayerHand;

public interface HandEvaluatorInterface {

    /**
     * Compares two Hands and determines a winner based on the hierarchy given via an XML file.
     * This method essentially forms the implementation of said hierarchy by determining winners based off of it.
     * @param playerHand1 is the first hand to compare
     * @param playerHand2 is the second hand to compare
     * @return either -1 (first hand wins), 0 (push), or 1 (second hand wins).
     */
    int compare(PlayerHand playerHand1, PlayerHand playerHand2);
}
