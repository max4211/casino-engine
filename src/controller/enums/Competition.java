package controller.enums;

/**
 * Enumerated type for possible Competition policies
 * Now depracated due to xml validation process
 */
@Deprecated
public enum Competition {

    ADVERSARY("Adversary"),
    GROUP("Group");

    private final String myType;

    private Competition(String type) {
        this.myType = type;
    }

}
