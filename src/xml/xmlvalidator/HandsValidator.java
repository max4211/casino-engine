package xml.xmlvalidator;

/**
 * Hands validation object, extends validator
 * Sole purpose is to super construct with proper reference to schema
 * @author Max Smith
 */
public class HandsValidator extends Validator {

    private static final String VALIDATION_FILE = "data/schema/handschema.xsd";

    public HandsValidator() {
        super(VALIDATION_FILE);
    }
}
