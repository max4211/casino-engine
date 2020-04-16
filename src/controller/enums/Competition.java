package controller.enums;

public enum Competition {

    ADVERSARY("Adversary"),
    GROUP("Group");

    private final String myType;

    private Competition(String type) {
        this.myType = type;
    }

}
