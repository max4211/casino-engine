package xml.xmlvalidator;

public class LobbyViewValidator extends Validator {

    private static final String VALIDATION_FILE = "data/schema/lobbyview.xsd";

    public LobbyViewValidator() {
        super(VALIDATION_FILE);
    }
}
