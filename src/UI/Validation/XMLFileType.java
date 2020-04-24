package UI.Validation;

public enum XMLFileType {
    DECK("Deck"),
    VIEW("View"),
    PLAYERS("Players"),
    GAME("Game"),
    HANDS("Hands");

    private final String myType;

    private XMLFileType(String type) {
        this.myType = type;
    }

    @Override
    public String toString() {return myType;}
}
