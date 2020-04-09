package GameView;

import Utility.CardTriplet;
import javafx.util.Pair;

import java.util.List;
import java.util.function.Consumer;

public interface GameViewInterface {

    /**
     * always call this
     * @param file
     */
    public void renderTable(String file);

    /**
     * make sure ids are unique for perfect functionality
     * @param deckInfo
     * @param wager
     * @param id
     */
    public void renderBet(List<CardTriplet> deckInfo, double wager, int id);

    public void addCard(CardTriplet cardInfo);

    public void showCard(int betId, int cardId);

    public String getAction(List<String> actions);

    /**
     * optional call needed for blackjack
     */
    public void renderAdversary(List<CardTriplet> actions);

    public void showAdversaryCard(int cardId);

    public void deleteBet(int betId);

    public void addPlayer(int playerId, double bankroll);

    public void removePlayer(int playerId);

    public void updateMainPlayer(int playerId);

    /**
     * only works on main player
     * @param minBet
     * @param maxBet
     */
    double promptPlayerBet(double minBet, double maxBet);
}
