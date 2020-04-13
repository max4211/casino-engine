package engine.player;

import Utility.Pair;

import java.util.*;

public class PlayerList implements ListInterface, PlayerListInterface, Iterator {

    private static final int DEFAULT_INDEX = 0;

    private Collection<Player> myPlayerCollection;
    private int myPlayerIndex;

    public PlayerList(Collection<Pair> playerCollection) {
        this.myPlayerCollection = new ArrayList<>();
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

    @Override
    public void add(Object o) {
        Player p = castToPlayer(o);
        this.myPlayerCollection.add(p);
    }

    @Override
    public void remove(Object o) {
        Player p = castToPlayer(o);
        this.myPlayerCollection.remove(p);
    }

    @Override
    public int length() {
        return this.myPlayerCollection.size();
    }

    private Player castToPlayer(Object o) {
        return (Player) o;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Object next() {
        return null;
    }

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

    @Override
    public Collection<Player> getPlayers() {
        return this.myPlayerCollection;
    }
}
