package xml.xmlvalidator;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ViewValidatorTest {

    @Test
    void validate() {
        Validator viewValidator = new ViewValidator();
//        String viewPath = "data/xml/views/views.xml";
        String viewPath = "data/xml/good/VIEW_basicview.xml";
        File viewFile = new File(viewPath);
        boolean result = viewValidator.validate(viewFile);
        boolean expected = true;
        assertEquals(expected, result);
    }
}