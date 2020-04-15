package engine.player;

import java.util.Collection;
import java.util.List;

public interface PlayerListInterface {

    /**
     * Set the starting position for iteration within the list
     * @param index index of the player to point to
     * @return true if that player exists in the list
     */
    boolean pointTo(int index);

    /**
     * Fetch all players in the list, using for initial debug
     * @return all players in the collection (aka at the table)
     */
    Collection<Player> getPlayers();

}
