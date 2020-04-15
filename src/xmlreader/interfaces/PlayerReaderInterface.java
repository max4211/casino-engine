package xmlreader.interfaces;

import Utility.Pair;

import java.util.Collection;

public interface PlayerReaderInterface {

    /**
     * Translates the XML tag for players in a game into a Map
     * The String keys are the names for the players, and the Doubles are their initial bankroll
     *
     * @return a collection of pairscontaining all the information for players mapping their names to their bankrolls
     */
    Collection<Pair> getPlayers();


}
