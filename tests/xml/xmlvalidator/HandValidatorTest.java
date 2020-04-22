package xml.xmlvalidator;

import exceptions.ValidatorException;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class HandValidatorTest {

    @Test
    void validate() {
        Validator handValidator = new HandValidator();
//        String handPath = "data/xml/hands/standard.xml";
        String handPath = "data/xml/hands/hands.xml";
        File handFile = new File(handPath);
        boolean result = handValidator.validate(handFile);
        boolean expected = true;
        assertEquals(expected, result);
    }

    @Test
    void validateError() {
        Validator handValidator = new HandValidator();
//        String handPath = "data/xml/hands/standard.xml";
        String handPath = "data/xml/hands/bad_hand.xml";
        File handFile = new File(handPath);
        try {
            handValidator.validate(handFile);
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
        }
    }

}