package xml.xmlvalidator;

/**
 * Players validation object, extends validator
 * Sole purpose is to super construct with proper reference to schema
 * @author Max Smith
 */
public class PlayersValidator extends Validator {

    private static final String VALIDATION_FILE = "data/schema/playerschema.xsd";

    public PlayersValidator() {
        super(VALIDATION_FILE);
    }
}
