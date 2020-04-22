package xml.xmlvalidator;

public class HandValidator extends Validator {

    private static final String VALIDATION_FILE = "data/schema/handschema.xsd";

    public HandValidator() {
        super(VALIDATION_FILE);
    }
}
