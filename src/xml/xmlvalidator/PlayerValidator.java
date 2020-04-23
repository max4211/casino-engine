package xml.xmlvalidator;

public class PlayerValidator extends Validator {

    private static final String VALIDATION_FILE = "data/schema/playerschema.xsd";

    public PlayerValidator() {
        super(VALIDATION_FILE);
    }
}
