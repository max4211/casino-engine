package UI.Validation;

/**
 * Enumerated type representing the status of a file in the Validation process.
 * Options are valid (passed schema), empty (not selected), or invalid (failed schema).
 * @author Eric Doppelt
 */
public enum FileStatus {
    VALID("Valid"),
    EMPTY("Empty"),
    INVALID("Invalid");

    private String myStatus;

    /**
     * Basic constructor that sets the status of the enumeration equal to its name in lowercase.
     * This is used to match keys in bundles.
     * @param status is the status of the enumeration in lowercase.
     */
    FileStatus(String status) {
        myStatus = status;
    }

    /**
     * Method to access the status of the enumeration in lowercase.
     * @return myStatus instance variable that corresponds to this information that matches the ResourceBundle.
     */
    @Override
    public String toString() {return myStatus;}
}
