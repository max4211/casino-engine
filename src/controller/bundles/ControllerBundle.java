package controller.bundles;

import UI.GameView.GameView;
import Utility.StringPair;
import actionFactory.ActionFactory;
import controller.enums.Cardshow;
import controller.enums.EntryBet;
import controller.enums.Goal;
import controller.interfaces.ControllerBundleInterface;
import engine.evaluator.bet.BetEvaluator;
import engine.evaluator.handclassifier.HandClassifier;
import engine.table.Table;

import java.util.Collection;
import java.util.List;

public class ControllerBundle implements ControllerBundleInterface {

    private Table myTable;
    private GameView myGameView;
    private final Collection<String> myPlayerActions;
    private final List<StringPair> myDealerAction;
    private final HandClassifier myHandClassifier;
    private final BetEvaluator myBetEvaluator;

    private final EntryBet myEntryBet;
    private Cardshow myCardshow;
    private Goal myGoal;

    public ControllerBundle(Table table, GameView gameView, EntryBet entryBet, Collection<String> playerActions, List<StringPair> dealerAction,
                      HandClassifier handClassifier, BetEvaluator betEvaluator,
                      Cardshow cardshow, Goal goal) {
        this.myTable = table;
        this.myGameView = gameView;
        this.myEntryBet = entryBet;
        this.myPlayerActions = playerActions;
        this.myDealerAction = dealerAction;
        this.myHandClassifier =  handClassifier;
        this.myBetEvaluator = betEvaluator;
        this.myCardshow = cardshow;
        this.myGoal = goal;

    }

    @Override
    public Table getTable() {
        return this.myTable;
    }

    @Override
    public GameView getGameView() {
        return this.myGameView;
    }

    @Override
    public Collection<String> getPlayerActions() {
        return this.myPlayerActions;
    }

    @Override
    public List<StringPair> getDealerAction() {
        return this.myDealerAction;
    }

    @Override
    public HandClassifier getHandClassifier() {
        return this.myHandClassifier;
    }

    @Override
    public BetEvaluator getBetEvaluator() {
        return this.myBetEvaluator;
    }

    @Override
    public EntryBet getEntryBet() {
        return this.myEntryBet;
    }

    @Override
    public Cardshow getCardShow() {
        return this.myCardshow;
    }

    @Override
    public Goal getGoal() {
        return this.myGoal;
    }
}
