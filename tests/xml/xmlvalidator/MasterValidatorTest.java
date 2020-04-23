package xml.xmlvalidator;

import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MasterValidatorTest {

    @Test
    void testValidation() {
        List<File> fileList = new ArrayList<>();
        fileList.add(new File("data/xml/deck/standard.xml"));
        fileList.add(new File("data/xml/game/poker.xml"));
        fileList.add(new File("data/xml/hands/pokerHands.xml"));
        fileList.add(new File("data/xml/players/3players.xml"));
        fileList.add(new File("data/xml/view/view.xml"));
        MasterValidator masterValidator = new MasterValidator(fileList);
    }

}