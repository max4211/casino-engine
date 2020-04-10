package GameView.NodeViews.Interfaces;

import Utility.CardTriplet;

import java.util.List;

public interface GameViewInterface {

    public void renderTable(String file);

    public void addCard(CardTriplet cardInfo, int playerID, int betID);

    public void removeCard(int playerID, int betID, int cardID);

    public void showCard(int playerID, int betID, int cardID);

    public void renderAdversary(List<CardTriplet> hand);

    public void showAdversaryCard(int cardId);

    public void addAdversaryCard(CardTriplet cardID);

    public void addBet(List<CardTriplet> handInfo, double wager, int playerID, int betID);

    public void removeBet(int playerId, int betId);

    public void addPlayer(String name, int playerId, double bankroll);

    public void removePlayer(int playerId);

    public void setMainPlayer(int playerId);

    /**
     * only works on main player
     * @param minBet
     * @param maxBet
     */
    public double selectWager(double minBet, double maxBet);

    public void updateWager(double newWager, int playerID, int BetID);

    public String selectAction(List<String> actions);
}
