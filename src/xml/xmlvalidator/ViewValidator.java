package xml.xmlvalidator;

public class ViewValidator extends Validator {

    private static final String VALIDATION_FILE = "data/schema/viewschema.xsd";

    public ViewValidator() {
        super(VALIDATION_FILE);
    }
}
