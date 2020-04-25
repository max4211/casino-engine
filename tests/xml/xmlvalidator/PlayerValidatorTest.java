package xml.xmlvalidator;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class PlayerValidatorTest {

    @Test
    void validate() {
        Validator playerValidator = new PlayersValidator();
//        String playerPath = "data/xml/players/players.xml";
        String playerPath = "data/xml/good/PLAYERS_three.xml";
        File playerFile = new File(playerPath);
        boolean result = playerValidator.validate(playerFile);
        boolean expected = true;
        assertEquals(expected, result);
    }
}