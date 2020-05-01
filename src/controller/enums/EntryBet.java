package controller.enums;

/**
 * Enumerated type for possible Entrybet policies
 * Now depracated due to xml validation process
 * @author Max Smith
 */
@Deprecated
public enum EntryBet {

    GENERIC("Generic"),
    SPECIFIC("Specific");

    private final String myType;

    private EntryBet(String type) {
        this.myType = type;
    }

}
