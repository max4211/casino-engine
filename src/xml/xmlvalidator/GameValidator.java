package xml.xmlvalidator;

/**
 * Game validation object, extends validator
 * Sole purpose is to super construct with proper reference to schema
 * @author Max Smith
 */
public class GameValidator extends Validator {

    private static final String VALIDATION_FILE = "data/schema/gameschema.xsd";

    public GameValidator() {
        super(VALIDATION_FILE);
    }
}
