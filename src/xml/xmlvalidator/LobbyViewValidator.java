package xml.xmlvalidator;

/**
 * LobbyView validation object, extends validator
 * Sole purpose is to super construct with proper reference to schema
 * @author Max Smith
 */
public class LobbyViewValidator extends Validator {

    private static final String VALIDATION_FILE = "data/schema/lobbyview.xsd";

    public LobbyViewValidator() {
        super(VALIDATION_FILE);
    }
}
