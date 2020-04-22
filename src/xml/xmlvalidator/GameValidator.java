package xml.xmlvalidator;

public class GameValidator extends Validator {

    private static final String VALIDATION_FILE = "data/schema/gameschema.xsd";

    public GameValidator() {
        super(VALIDATION_FILE);
    }
}
