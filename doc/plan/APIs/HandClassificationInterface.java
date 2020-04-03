/**
 * Small module that simply assigns classifiactions to undetermined Hand Objects.
 * Called by the Table to "Garbage Collect" hands that are classified as losing hands and then pass classified hands into the BetEvaluator
 */
public interface HandClassificationInterface {

    /**
     * Method takes the Hand given to it and classifies it as the winningest hand that it can be based on the winning heirarcy given in the XML.
     * unclassfiedHand its then determinted classified form in an instance variable in the Hand object.
     * @param unclassfiedHand is the Hand to classify and save its classification in itself
     */
    public void classifyHand(Hand unclassfiedHand);