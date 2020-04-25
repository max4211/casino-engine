package xml.xmlvalidator;

import exceptions.ValidatorException;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class HandValidatorTest {

    @Test
    void validate() {
        Validator handValidator = new HandsValidator();
//        String handPath = "data/xml/hands/standard.xml";
        String handPath = "data/xml/good/HANDS_blackjack.xml";
        File handFile = new File(handPath);
        boolean result = handValidator.validate(handFile);
        boolean expected = true;
        assertEquals(expected, result);
    }

    @Test
    void validateError() {
        Validator handValidator = new HandsValidator();
//        String handPath = "data/xml/hands/standard.xml";
        String handPath = "data/xml/bad/HANDS_missingentry.xml";
        File handFile = new File(handPath);
        try {
            handValidator.validate(handFile);
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
        }
    }

}