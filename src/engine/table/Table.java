package engine.table;

import engine.dealer.Dealer;
import engine.evaluator.BetEvaluator;
import engine.evaluator.HandClassifier;
import engine.player.Player;

import java.util.List;

public class Table {

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

}
