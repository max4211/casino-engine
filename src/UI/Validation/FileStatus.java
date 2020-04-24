package UI.Validation;

public enum FileStatus {
    VALID("Valid"),
    EMPTY("Empty"),
    INVALID("Invalid");

    private String myStatus;

    FileStatus(String status) {
        myStatus = status;
    }

    @Override
    public String toString() {return myStatus;}
}
