package xml.xmlvalidator;

import exceptions.ValidatorException;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class DeckValidatorTest {

    @Test
    void validate() {
        Validator deckValidator = new DeckValidator();
//        String deckPath = "data/xml/decks/standard.xml";
        String deckPath = "data/xml/deck/cs308.xml";
        File deckFile = new File(deckPath);
        boolean result = deckValidator.validate(deckFile);
        boolean expected = true;
        assertEquals(expected, result);
    }

    @Test
    void validateError() {
        Validator deckValidator = new DeckValidator();
//        String deckPath = "data/xml/decks/standard.xml";
        String deckPath = "data/xml/deck/bad_deck.xml";
        File deckFile = new File(deckPath);
        try {
            deckValidator.validate(deckFile);
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
        }
    }

}