package controller;

public enum EntryBet {

    GENERIC("Generic"),
    SPECIFIC("Specific");

    private final String myType;

    private EntryBet(String type) {
        this.myType = type;
    }

}
