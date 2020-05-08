package engine.player;

import Utility.Pair;

import java.util.*;

@Deprecated
/**
 * Purported encapsulation of a list of players so the list is not passed around all the time
 * @author Max Smith
 */
public class PlayerList implements ListInterface, PlayerListInterface, Iterator {

    private static final int DEFAULT_INDEX = 0;

    private final Collection<Player> myPlayerCollection;
    private int myPlayerIndex;

    /**
     * Construct a list of players from a colection of players
     * @param playerCollection
     */
    public PlayerList(Collection<Pair> playerCollection) {
        this.myPlayerCollection = new ArrayList<>();
        this.myPlayerIndex = 0;
        for (Pair p: playerCollection) {
            String name = (String) p.getFirst();
            double bankroll = (double) p.getSecond();
            this.myPlayerCollection.add(new Player(name, bankroll));
        }
    }

    @Deprecated
    public PlayerList(Map<String, Double> playerMap) {
        this.myPlayerCollection = new ArrayList<>();
        for (String key: playerMap.keySet()) {
            this.myPlayerCollection.add(new Player(key, playerMap.get(key)));
        }
    }

    /**
     * Add an object to the list
     * @param o object to add to the interface
     */
    @Override
    public void add(Object o) {
        Player p = castToPlayer(o);
        this.myPlayerCollection.add(p);
    }

    /**
     * Remove an object from the list
     * @param o object to add to the interface
     */
    @Override
    public void remove(Object o) {
        Player p = castToPlayer(o);
        this.myPlayerCollection.remove(p);
    }

    /**
     * Determine the length (total players at the table)
     * @return length of implemented list
     */
    @Override
    public int length() {
        return this.myPlayerCollection.size();
    }

    private Player castToPlayer(Object o) {
        return (Player) o;
    }

    /**
     * Helper to iterate over player objects inside of the list
     * @return
     */
    @Override
    public boolean hasNext() {
        return false;
    }

    /**
     * Returns the next player in the list
     * @return
     */
    @Override
    public Object next() {
        return null;
    }

    /**
     * Set the starting position for iteration within the list
     * @param index index of the player to point to
     * @return true if that player exists in the list
     */
    @Override
    public boolean pointTo(int index) {
        if (this.myPlayerCollection.size() < index) {
            this.myPlayerIndex = index;
            return true;
        } else {
            this.myPlayerIndex = DEFAULT_INDEX;
            return false;
        }
    }

    /**
     * Fetch all players in the list, using for initial debug
     * @return all players in the collection (aka at the table)
     */
    @Override
    public Collection<Player> getPlayers() {
        return this.myPlayerCollection;
    }
}
