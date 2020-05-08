package engine.evaluator.handtype;

import Utility.handbundle.HandBundle;
import engine.dealer.Card;

import java.util.List;

/**
 * Implemented by hand factory to show single service, creating hands
 * @author Max Smith
 */
public interface HandFactoryInterface {

    /**
     * Creates a hand according to bundled parameters
     * @return the appropriate hand as configured by bundle and cards
     */
    Hand createHand(HandBundle b, List<Card> cards);
}
