package controller.interfaces;

import UI.GameView.GameView;
import Utility.StringPair;
import actionFactory.ActionFactory;
import controller.enums.Cardshow;
import controller.enums.EntryBet;
import controller.enums.Goal;
import engine.evaluator.bet.BetEvaluator;
import engine.evaluator.handclassifier.HandClassifier;
import engine.table.Table;

import java.util.Collection;

public interface ControllerBundleInterface {

    /**
     * All methods are getters for appropriately bundled information
     * @return
     */
    Table getTable();

    GameView getGameView();

    Collection<String> getPlayerActions();

    StringPair getDealerAction();

    HandClassifier getHandClassifier();

    BetEvaluator getBetEvaluator();

    EntryBet getEntryBet();

    Cardshow getCardShow();

    Goal getGoal();

}
