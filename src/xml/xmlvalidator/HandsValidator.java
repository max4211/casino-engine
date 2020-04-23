package xml.xmlvalidator;

public class HandsValidator extends Validator {

    private static final String VALIDATION_FILE = "data/schema/handschema.xsd";

    public HandsValidator() {
        super(VALIDATION_FILE);
    }
}
