package engine.hand;

public enum HandOutcome {

    WIN("Win"),
    LOSS("Loss"),
    TIE("Tie");

    private final String myString;

    private HandOutcome(String outcome) {
        this.myString = outcome;
    }
}
