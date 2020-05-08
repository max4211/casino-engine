package engine.evaluator.handclassifier;

import engine.dealer.Card;
import engine.hand.PlayerHand;

import java.util.List;

/**
 * Single publicly available service porvided by hand classifier (classify hands)
 * @author Max Smith
 */
public interface HandClassifierInterface {

    /**
     * Method takes the Hand given to it and classifies it as the winningest hand that it can be based on the winning heirarcy given in the XML.
     * unclassfiedHand its then determinted classified form in an instance variable in the Hand object.
     *
     * @param cards is the Hand to classify and save its classification in itself
     */
    void classifyHand(List<Card> cards, PlayerHand h);

}
