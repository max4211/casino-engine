package UI.Interfaces;

import Utility.CardTriplet;

import java.util.List;

public interface GameViewInterface {

    void renderAdversary(List<CardTriplet> hand);

    void showAdversaryCard(int cardId);

    void addAdversaryCard(CardTriplet cardID);

    void clearAdversary();

    void addCardIfAbsent(CardTriplet cardInfo, int playerID, int betID);

    void showCard(int playerID, int betID, int cardID);

    void hideCard(int playerID, int betID, int cardID);

    void addBet(List<CardTriplet> handInfo, double wager, int playerID, int betID);

    void removeBet(int playerId, int betId);

    void renderCommonCards(List<CardTriplet> hand);

    void showCommonCard(int cardID);

    void addPlayer(String name, int playerId, double bankroll);

    void setMainPlayer(int playerId);

    double selectWager(double minBet, double maxBet);

    void setWager(double newWager, int playerID, int BetID);

    void setBankRoll(double newBankroll, int playerID);

    String selectAction(List<String> allActions);

    void clearAllBets();

    void displayException(Exception ex);

    void promptNewGame(GameCaller startNewGame);

    void displayText(String s);

    void setLoser(int betID);

    void renderPot(double initialPot);

    void setPot(double newPot);
    
    }