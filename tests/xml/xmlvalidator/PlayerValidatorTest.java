package xml.xmlvalidator;

import exceptions.ValidatorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class PlayerValidatorTest {

    Validator myPlayersValidator;
    private static final String PATH_TO_PLAYER_DATA = "data/xml/";
    @BeforeEach
    void setUp() {
        myPlayersValidator = new PlayersValidator();
    }

    // HAPPY PATH :)
    @Test
    void validateGoodData() {
        String playerPath = PATH_TO_PLAYER_DATA.concat("good/PLAYERS_five.xml");
        File playerFile = new File(playerPath);
        boolean result = myPlayersValidator.validate(playerFile);
        boolean expected = true;
        assertEquals(expected, result);
    }

    // SAD PATH :(
    @Test
    void validateBad() {
        String playerPath = PATH_TO_PLAYER_DATA.concat("bad/PLAYERS_emptyName.xml");
        File playerFile = new File(playerPath);
        assertThrows(ValidatorException.class, () -> myPlayersValidator.validate(playerFile));
    }

    // HAPPY PATH :)
    @Test
    void failEmptyFile() {
        String playerPath = PATH_TO_PLAYER_DATA.concat("bad/PLAYERS_empty.xml");
        File playerFile = new File(playerPath);
        assertThrows(ValidatorException.class, () -> myPlayersValidator.validate(playerFile));
    }
}