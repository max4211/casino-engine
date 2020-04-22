package engine.evaluator.handtype;

import Utility.handbundle.HandBundle;
import engine.dealer.Card;

import java.util.List;

public interface HandFactoryInterface {

    /**
     * Creates a hand according to bundled parameters
     * @return
     */
    Hand createHand(HandBundle b, List<Card> cards);
}
