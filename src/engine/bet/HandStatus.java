package engine.bet;

@Deprecated
/**
 * HandStatus object, now refactored into HandOutcome
 */
public enum HandStatus {
    WON("WON"),
    LOSS("LOSS");

    private String myString;

    HandStatus(String s) {
        this.myString = s;
    }

}
