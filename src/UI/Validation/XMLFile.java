package UI.Validation;

public enum XMLFile {
    DECK("Deck"),
    VIEW("View"),
    PLAYERS("Players"),
    GAME("Game"),
    HANDS("Hands");

    private final String myType;

    private XMLFile(String type) {
        this.myType = type;
    }

    @Override
    public String toString() {return myType;}
}
