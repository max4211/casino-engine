package engine.adversary;

import engine.hand.Hand;

public interface AdversaryInterface {

    /**
     * Fetch the hand internal to the adversary
     * @return
     */
    Hand getHand();
}
