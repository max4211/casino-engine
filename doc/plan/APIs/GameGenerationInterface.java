import java.io.File;
import java.io.FileNotFoundException;

/**
 * Module that handles game initalization, but little more. The sole method works with the XML and Table modules to construct a MVC model
 * with user inputted preferences via the XML.
 */
public interface GameGenerationInterface {

    /**
     * GameGeneration only has one method, to create a new game in a new window.
     * The file given is in an XML format and is later given to an XML Reader to generate preferences to load the game.
     * This method creates the MVC model which then runs by itself.
     * The FileNotFoundException occurs if the specificied file does not exist.
     * @param file is an XML file containing preferences for a full Casino simulation.
     */
    public void createGame(File file) throws FileNotFoundException;
}