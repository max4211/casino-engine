package xml.xmlvalidator;

public class DeckValidator extends Validator {

    private static final String VALIDATION_FILE = "data/schema/deckschema.xsd";

    public DeckValidator() {
        super(VALIDATION_FILE);
    }
}
