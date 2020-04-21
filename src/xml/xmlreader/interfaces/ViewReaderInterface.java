package xml.xmlreader.interfaces;

import java.util.List;

public interface ViewReaderInterface {

    /**
     * Get dimensions of the screen
     * @return
     */
    int getScreenWidth();
    int getScreenHeight();

    /**
     * Get string type parameters governing the game
     * @return
     */
    String getTitle();
    String getAuthors();

    /**
     * Get game view construct params, two lists of lang and stylesheets
     * @return
     */
    List<String> getLanguages();
    List<String> getStylesheets();

}
