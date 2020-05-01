package engine.evaluator.handtype;

import Utility.handbundle.HandBundle;
import actions.individual.IndividualAction;
import engine.dealer.Card;
import exceptions.ReflectionException;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * Single purpose, create abstract hand objects for hand classification in
 * the HandClassifier module
 * @author Max Smith
 */
public class HandFactory implements HandFactoryInterface {

    private static final String HAND_PATH = "engine.evaluator.handtype.";

    /**
     * Creates a hand according to bundled parameters
     * @return the appropriate hand as configured by bundle and cards
     */
    @Override
    public Hand createHand(HandBundle b, List<Card> cards) {
        try {
            String handName = b.getName();
            List<Double> params = b.getParams();
            Class clazz = Class.forName(createHandPath(handName));
            Constructor ctor = clazz.getConstructor(List.class, List.class);
            return (Hand) ctor.newInstance(cards, params);
        } catch (Exception e) {
            throw new ReflectionException(e);
        }
    }

    private String createHandPath(String handName) {
        return String.format("%s%s", HAND_PATH, handName);
    }
}
