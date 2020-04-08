package GameView;

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
    public void renderBet(List<Pair<Double, String>> deckInfo, double wager, int id);

    public void addCard(int betId, Pair<Double, String> cardInfo);

    public void showCard(int betId, int cardId);

    public void renderActionBox(List<String> actions, Consumer<String> actionReciever);

    /**
     * optional call needed for blackjack
     */
    public void renderAdversary(List<String> actions);

    public void showAdversaryCard(int cardId);

    public void deleteBet(int betId);

    public void addPlayer(int playerId, double bankroll);

    public void removePlayer(int playerId);

    public void updateMainPlayer(int playerId);


}
