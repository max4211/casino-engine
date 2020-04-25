package UI.Validation;

import UI.DukeApplicationTest;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileDisplayTest extends DukeApplicationTest {

    @BeforeEach
    void setUp() throws Exception {
        launch(FileDisplayTester.class);
    }

    // HAPPY PATH :)
    @Test
    void testStandardLabel() {
        VBox fileDisplayView = lookup("#starting-pane #standard").query();
        Label fileTag = (Label)fileDisplayView.getChildren().get(0);
        assertEquals("Deck", fileTag.getText());
    }

    // HAPPY PATH :)
    @Test
    void testLanguageChange() {
        VBox fileDisplayView = lookup("#starting-pane #language-change").query();
        Label fileTag = (Label)fileDisplayView.getChildren().get(0);
        assertEquals("La Baraja", fileTag.getText());
    }

    // HAPPY PATH :)
    @Test
    void testGoodFileIcon() {
        VBox fileDisplayView = lookup("#starting-pane #language-change").query();
        HBox icons = (HBox)fileDisplayView.getChildren().get(1);
        ImageView fileIcon = (ImageView)icons.getChildren().get(0);
        String imagefromResources = fileIcon.getImage().getUrl();
        imagefromResources = imagefromResources.substring(imagefromResources.indexOf("iconImages"));
        assertEquals("iconImages/fileValidatorIcons/whiteDeck.png", imagefromResources);
    }

    // HAPPY PATH :)
    @Test
    void testGoodEqualIcon() {
        VBox fileDisplayView = lookup("#starting-pane #language-change").query();
        HBox icons = (HBox)fileDisplayView.getChildren().get(1);
        ImageView fileIcon = (ImageView)icons.getChildren().get(1);
        String imagefromResources = fileIcon.getImage().getUrl();
        imagefromResources = imagefromResources.substring(imagefromResources.indexOf("iconImages"));
        assertEquals("iconImages/fileValidatorIcons/blueEquals.png", imagefromResources);
    }

    // HAPPY PATH :)
    @Test
    void testGoodStatusIcon() {
        VBox fileDisplayView = lookup("#starting-pane #language-change").query();
        HBox icons = (HBox)fileDisplayView.getChildren().get(1);
        ImageView fileIcon = (ImageView)icons.getChildren().get(2);
        String imagefromResources = fileIcon.getImage().getUrl();
        imagefromResources = imagefromResources.substring(imagefromResources.indexOf("iconImages"));
        assertEquals("iconImages/fileStatusIcons/emptyBlue.png", imagefromResources);
    }

    // SAD PATH :(
    @Test
    void testForInvalidStatus() {
        VBox fileDisplayView = lookup("#starting-pane #invalid").query();
        HBox icons = (HBox)fileDisplayView.getChildren().get(1);
        ImageView fileIcon = (ImageView)icons.getChildren().get(2);
        String imagefromResources = fileIcon.getImage().getUrl();
        imagefromResources = imagefromResources.substring(imagefromResources.indexOf("iconImages"));
        assertEquals("iconImages/fileStatusIcons/invalidRed.png", imagefromResources);
    }


    // SAD PATH
    @Test
    void testforInvalidBundle()  {
        assertThrows(RuntimeException.class, () -> launch(FileDisplayErrorTester.class));
    }
}