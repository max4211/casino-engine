package engine.player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PlayerList implements ListInterface, PlayerListInterface, Iterator {

    private static final int DEFAULT_INDEX = 0;

    private List<Player> myPlayerList;
    private int myPlayerIndex;

    public PlayerList(Map<String, Double> playerMap) {
        this.myPlayerList = new ArrayList<>();
        for (String key: playerMap.keySet()) {
            this.myPlayerList.add(new Player(key, playerMap.get(key)));
        }
    }

    @Override
    public void add(Object o) {
        Player p = castToPlayer(o);
        this.myPlayerList.add(p);
    }

    @Override
    public void remove(Object o) {
        Player p = castToPlayer(o);
        this.myPlayerList.remove(p);
    }

    @Override
    public int length() {
        return this.myPlayerList.size();
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
        if (this.myPlayerList.size() < index) {
            this.myPlayerIndex = index;
            return true;
        } else {
            this.myPlayerIndex = DEFAULT_INDEX;
            return false;
        }
    }

    @Override
    public List<Player> getPlayers() {
        return this.myPlayerList;
    }
}
