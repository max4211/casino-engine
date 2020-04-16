package controller.enums;

public enum Goal {

    CASHGAME("Cashgame"),
    TOURNAMENT("Tournament"),
    TARGET("Target");

    private final String myType;

    private Goal(String type) {
        this.myType = type;
    }

}
