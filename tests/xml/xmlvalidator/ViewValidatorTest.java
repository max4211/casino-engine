package xml.xmlvalidator;

import exceptions.ValidatorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ViewValidatorTest {

    private Validator myValidator;

    @BeforeEach
    void setUp() {
        myValidator = new ViewValidator();
    }

    // HAPPY PATH :)
     @Test
    void validateNormalData() {
        String viewPath = "data/xml/good/VIEW_basicview.xml";
        File viewFile = new File(viewPath);
        boolean result = myValidator.validate(viewFile);
        boolean expected = true;
        assertEquals(expected, result);
    }

    // SAD PATH :(
    @Test
    void validateNoCSSSheets() {
        String viewPath = "data/xml/good/VIEW_noCSSSheets.xml";
        File viewFile = new File(viewPath);
        Assertions.assertThrows(ValidatorException.class, () -> myValidator.validate(viewFile));
    }


    // SAD PATH :(
    @Test
    void validateNoCSSTag() {
        String viewPath = "data/xml/bad/VIEW_missingCSSTag.xml";
        File viewFile = new File(viewPath);
        Assertions.assertThrows(ValidatorException.class, () -> myValidator.validate(viewFile));
    }

    @Test
    void validateNoWidth() {
        String viewPath = "data/xml/bad/VIEW_missingHeight.xml";
        File viewFile = new File(viewPath);
        Assertions.assertThrows(ValidatorException.class, () -> myValidator.validate(viewFile));
    }

    // SAD PATH :(
    @Test
    void validateNoHeight() {
        String viewPath = "data/xml/bad/VIEW_missingWidth.xml";
        File viewFile = new File(viewPath);
        Assertions.assertThrows(ValidatorException.class, () -> myValidator.validate(viewFile));
    }
    
    // SAD PATH :(
    // Has the tag, no content
    @Test
    void validateNoIconBundle() {
        String viewPath = "data/xml/bad/VIEW_missingIconBundle.xml";
        File viewFile = new File(viewPath);
        Assertions.assertThrows(ValidatorException.class, () -> myValidator.validate(viewFile));
    }

}