package xml.xmlvalidator;

/**
 * Deck validation object, extends validator
 * Sole purpose is to super construct with proper reference to schema
 * @author Max Smith
 */
public class DeckValidator extends Validator {

    private static final String VALIDATION_FILE = "data/schema/deckschema.xsd";

    public DeckValidator() {
        super(VALIDATION_FILE);
    }
}
