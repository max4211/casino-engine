package controller.interfaces;

import UI.GameView.GameView;
import Utility.StringPair;
import controller.enums.Cardshow;
import controller.enums.EntryBet;
import controller.enums.Goal;
import engine.evaluator.bet.BetEvaluator;
import engine.evaluator.handclassifier.HandClassifier;
import engine.table.Table;

import java.util.Collection;
import java.util.List;

public interface ControllerBundleInterface {

    /**
     * All methods are getters for appropriately bundled information
     * @return
     */
    Table getTable();

    GameView getGameView();

    Collection<String> getPlayerActions();

    List<StringPair> getDealerAction();

    HandClassifier getHandClassifier();

    BetEvaluator getBetEvaluator();

    String getEntryBet();

    String getCardShow();

    String getGoal();

}
