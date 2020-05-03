package UI.Validation;

/**
 * Enumerated type representing the type of file icon to use in the validator display.
 * The name of the file type corresponds to the name of an XML file and a key in the iconBundle and LanguageBundle.
 * @author Eric Doppelt
 */
public enum XMLFileType {
    DECK("Deck"),
    VIEW("View"),
    PLAYERS("Players"),
    GAME("Game"),
    HANDS("Hands");

    private final String myType;

    /**
     * Basic constructor that sets the type of the enumeration to its name.
     * @param type is a String representing the file type in lowercase to match the keys in the bundles.
     */
    XMLFileType(String type) {
        this.myType = type;
    }

    /**
     * Basic toString method allowing easy access to the type of the enumeration.
     * @return the lowercase representation of the enumeration, stored in the myType instance variable.
     */
    @Override
    public String toString() {return myType;}
}
