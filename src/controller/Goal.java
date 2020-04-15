package controller;

public enum Goal {

    CASHGAME("Cashgame"),
    TOURNAMENT("Tournament");

    private final String myType;

    private Goal(String type) {
        this.myType = type;
    }

}
