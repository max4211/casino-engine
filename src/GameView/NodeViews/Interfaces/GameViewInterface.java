package GameView.NodeViews.Interfaces;

import Utility.CardTriplet;

import java.util.List;

public interface GameViewInterface {

    public void renderTable(String file);

    public void addCard(CardTriplet cardInfo, int playerID, int betID);

    public void removeCard(int playerID, int betID, int cardID);

    public void showCard(int betId, int cardId);

    public void renderAdversary(List<CardTriplet> hand);

    public void showAdversaryCard(int cardId);

    public void addAdversaryCard(CardTriplet cardID);

    public void addBet(List<CardTriplet> handInfo, double wager, int playerID, int betID);

    public void removeBet(int playerId, int betId);

    public void addPlayer(int playerId, double bankroll);

    public void removePlayer(int playerId);

    public void updateMainPlayer(int playerId);

    /**
     * only works on main player
     * @param minBet
     * @param maxBet
     */
    public double selectWager(double minBet, double maxBet);

    public String selectAction(List<String> actions);
}
