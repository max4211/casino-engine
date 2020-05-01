package xml.xmlreader.interfaces;

import java.util.List;

/**
 * Interface of all getters for view reader XML object
 */
public interface ViewReaderInterface {

    /**
     * Get dimensions of the screen
     * @return width
     */
    int getScreenWidth();

    /**
     * Get dimensions of the screen
     * @return height
     */
    int getScreenHeight();

    /**
     * Get string type parameters governing the game
     * @return game title
     */
    String getTitle();

    /**
     * Get string type parameters governing the game
     * @return game authors
     */
    String getAuthors();

    /**
     * Get game view construct params
     * @return list of languages
     */
    List<String> getLanguages();

    /**
     * Get game view construct params
     * @return list of stylesheets
     */
    List<String> getStylesheets();

    /**
     * Return icon bundle tag
     * @return
     */
    String getIconBundle();

    /**
     * Reteurn error stylesheet tag
     * @return
     */
    String getErrorStylesheet();

}
