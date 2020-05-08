package controller.enums;

/**
 * Enumerated type for possible goal policies
 * Now depracated due to xml validation process
 * @author Max Smith
 */
@Deprecated
public enum Goal {

    CASHGAME("Cashgame"),
    TOURNAMENT("Tournament"),
    TARGET("Target");

    private final String myType;

    private Goal(String type) {
        this.myType = type;
    }

    @Override
    public String toString() {
        return this.myType;
    }

}
