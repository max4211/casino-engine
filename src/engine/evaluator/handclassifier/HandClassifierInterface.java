package engine.evaluator.handclassifier;

import engine.hand.PlayerHand;

public interface HandClassifierInterface {

    /**
     * Method takes the Hand given to it and classifies it as the winningest hand that it can be based on the winning heirarcy given in the XML.
     * unclassfiedHand its then determinted classified form in an instance variable in the Hand object.
     *
     * @param unclassfiedPlayerHand is the Hand to classify and save its classification in itself
     */
    void classifyHand(PlayerHand unclassfiedPlayerHand);

}
