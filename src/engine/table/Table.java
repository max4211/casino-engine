package engine.table;

import engine.dealer.Dealer;
import engine.player.Player;

import java.util.List;

public class Table {

    private List<Player> myPlayers;
    private Dealer myDealer;

    public Table(List<Player> players, Dealer dealer) {
        this.myPlayers = players;
        this.myDealer = dealer;
    }

}