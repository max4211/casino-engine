package UI.Interfaces;

import UI.GameView.BetView;
import UI.Utilities.CardTriplet;
import engine.dealer.Card;

import java.io.File;
import java.util.List;

public interface GameViewInterface {

    void renderAdversary(List<CardTriplet> hand);

    void showAdversaryCard(int cardId);

    void addAdversaryCard(CardTriplet cardID);

    void clearAdversary();

    void addCardIfAbsent(CardTriplet cardInfo, int playerID, int betID);

    void showCard(int playerID, int betID, int cardID);

    void hideCard(int playerID, int betID, int cardID);

    void addBet(List<CardTriplet> handInfo, double wager, String classification, int playerID, int betID);

    void addBet(double wager, int playerID, int betID);

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

    void classifyHand(String classification, int playerID, int betID);

    void setLoser(int playerID, int betID);

    void setWinner(int playeriD, int betID);

    void renderPot(double initialPot);

    void setPot(double newPot);

    @Deprecated
    void renderGame(File image) throws UnsupportedOperationException;

    @Deprecated
    void giveCard(BetView b, Card c) throws UnsupportedOperationException;

    @Deprecated
    void showCard(BetView b, Card c) throws UnsupportedOperationException;

    @Deprecated
    void showCards(BetView b) throws UnsupportedOperationException;
}