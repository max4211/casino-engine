package xml.xmlvalidator;

import com.sun.java.accessibility.util.Translator;
import exceptions.ValidatorException;
import javafx.scene.transform.Translate;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class DeckValidatorTest {

    @Test
    void validate() {
        Validator deckValidator = new DeckValidator();
//        String deckPath = "data/xml/decks/standard.xml";
        String deckPath = "data/xml/good/DECK_cs308.xml";
        File deckFile = new File(deckPath);
        boolean result = deckValidator.validate(deckFile);
        boolean expected = true;
        assertEquals(expected, result);
    }

    @Test
    void validateError() {
        Validator deckValidator = new DeckValidator();
//        String deckPath = "data/xml/decks/standard.xml";
        String deckPath = "data/xml/bad/DECK_missingentry.xml";
        File deckFile = new File(deckPath);
        try {
            deckValidator.validate(deckFile);
        } catch (Exception e) {
            assertEquals(e.getClass(), ValidatorException.class);
            System.out.println(e.getMessage());
        }
    }

}