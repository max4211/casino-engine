package xmlreader.interfaces;

public interface LobbyReaderInterface {

    /**
     * Get XML String elements for creating the mini view in lobby view
     * @return
     */
    String getName();
    String getIcon();

    /**
     * Get XML String elements for all file pointers
     * @return
     */
    String getDeckFile();
    String getGameFile();
    String getHandFile();
    String getPlayerFile();
    String getViewFile();

}
