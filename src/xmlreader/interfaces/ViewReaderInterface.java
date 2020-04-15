package xmlreader.interfaces;

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
    String getLanguage();

}
