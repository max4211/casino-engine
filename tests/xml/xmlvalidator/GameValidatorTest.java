package xml.xmlvalidator;

import exceptions.ValidatorException;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class GameValidatorTest {

    @Test
    void validate() {
        Validator gameValidator = new GameValidator();
//        String gamePath = "data/xml/games/standard.xml";
        String gamePath = "data/xml/game/poker.xml";
        File gameFile = new File(gamePath);
        boolean result = gameValidator.validate(gameFile);
        boolean expected = true;
        assertEquals(expected, result);
    }

    @Test
    void validateError() {
        Validator gameValidator = new GameValidator();
//        String gamePath = "data/xml/games/standard.xml";
        String gamePath = "data/xml/game/bad_game.xml";
        File gameFile = new File(gamePath);
        try {
            boolean result = gameValidator.validate(gameFile);
        } catch (ValidatorException e) {
            System.out.print(e.getMessage());
        }
    }

}