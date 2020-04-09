package engine.table;

import engine.bet.Bet;
import engine.dealer.Dealer;
import engine.evaluator.BetEvaluator;
import engine.evaluator.HandClassifier;
import engine.player.Player;

import java.util.List;
import java.util.function.Consumer;

public class Table implements TableInterface {

    private List<Player> myPlayers;
    private Dealer myDealer;

    private BetEvaluator myBetEvaluator;
    private HandClassifier myHandClassifier;

    public Table(List<Player> players, Dealer dealer, BetEvaluator betEvaluator, HandClassifier handClassifier) {
        this.myPlayers = players;
        this.myDealer = dealer;
        this.myBetEvaluator = betEvaluator;
        this.myHandClassifier = handClassifier;
    }

    /**
     * SHELL
     */
    @Override
    public void acceptString(String s) {
        System.out.println(s);
    }

    @Override
    public void placeEntryBet(String s, Consumer<Bet> betConsumer) {
        reflectOnMethod(s);
    }

    private void reflectOnMethod(String s) {

    }


}
