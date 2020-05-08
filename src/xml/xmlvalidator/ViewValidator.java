package xml.xmlvalidator;

/**
 * View validation object, extends validator
 * Sole purpose is to super construct with proper reference to schema
 * @author Max Smith
 */
public class ViewValidator extends Validator {

    private static final String VALIDATION_FILE = "data/schema/viewschema.xsd";

    public ViewValidator() {
        super(VALIDATION_FILE);
    }
}
