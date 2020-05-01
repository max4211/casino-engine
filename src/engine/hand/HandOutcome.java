package engine.hand;

/**
 * Enumerated type assigned in BetEvaluation to determine winners, losers, and ties
 */
public enum HandOutcome {

    WIN("Win"),
    LOSS("Loss"),
    TIE("Tie");

    private final String myString;

    private HandOutcome(String outcome) {
        this.myString = outcome;
    }
}
