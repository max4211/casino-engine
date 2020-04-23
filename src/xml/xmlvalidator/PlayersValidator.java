package xml.xmlvalidator;

public class PlayersValidator extends Validator {

    private static final String VALIDATION_FILE = "data/schema/playerschema.xsd";

    public PlayersValidator() {
        super(VALIDATION_FILE);
    }
}
