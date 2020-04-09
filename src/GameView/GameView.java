package GameView;

import Utility.CardTriplet;

import java.util.List;

public class GameView implements GameViewInterface {

    public GameView() {
        ;
    }

    @Override
    public void renderTable(String file) {
        
    }

    @Override
    public void renderBet(List<CardTriplet> deckInfo, double wager, int id) {

    }

    @Override
    public void addCard(CardTriplet cardInfo) {

    }

    @Override
    public void showCard(int betId, int cardId) {

    }

    @Override
    public String getAction(List<String> actions) {
        return null;
    }

    @Override
    public void renderAdversary(List<CardTriplet> actions) {

    }

    @Override
    public void showAdversaryCard(int cardId) {

    }

    @Override
    public void deleteBet(int betId) {

    }

    @Override
    public void addPlayer(int playerId, double bankroll) {

    }

    @Override
    public void removePlayer(int playerId) {

    }

    @Override
    public void updateMainPlayer(int playerId) {

    }

    @Override
    public Double promptPlayerBet(int minBet, int maxBet) {
        return null;
    }
}
