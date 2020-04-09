package engine.bet;

public enum HandStatus {
    WON("WON"),
    LOSS("LOSS");

    private String myString;

    HandStatus(String s) {
        this.myString = s;
    }

}
