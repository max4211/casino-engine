package xml.xmlvalidator;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class PlayerValidatorTest {

    @Test
    void validate() {
        Validator playerValidator = new PlayerValidator();
        String playerPath = "data/xml/players/players.xml";
        File playerFile = new File(playerPath);
        boolean result = playerValidator.validate(playerFile);
        boolean expected = true;
        assertEquals(expected, result);
    }
}