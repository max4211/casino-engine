package xml.xmlvalidator;

import exceptions.ValidatorException;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class LobbyViewValidatorTest {

    @Test
    void validate() {
        Validator lobbyViewValidator = new LobbyViewValidator();
//        String lobbyViewPath = "data/xml/lobbyViews/standard.xml";
        String lobbyViewPath = "data/xml/lobbyview/lobbyview_v1.xml";
        File lobbyViewFile = new File(lobbyViewPath);
        boolean result = lobbyViewValidator.validate(lobbyViewFile);
        boolean expected = true;
        assertEquals(expected, result);
    }

    @Test
    void validateError() {
        Validator lobbyViewValidator = new LobbyViewValidator();
//        String lobbyViewPath = "data/xml/lobbyViews/standard.xml";
        String lobbyViewPath = "data/xml/lobbyview/lobbyview_bad.xml";
        File lobbyViewFile = new File(lobbyViewPath);
        try {
            lobbyViewValidator.validate(lobbyViewFile);
        } catch (Exception e) {
            assertEquals(e.getClass(), ValidatorException.class);
            System.out.println(e.getMessage());
        }
    }

}