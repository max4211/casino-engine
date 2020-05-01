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
     * @return the table for the game
     */
    Table getTable();

    /**
     * @return the GameView (UI controller) for the game
     */
    GameView getGameView();

    /**
     * @return the collection of player actions (reflection facilitators) for the game
     */
    Collection<String> getPlayerActions();

    /**
     * @return the list of dealer actions (type - communal/individual and quantity - any double)
     */
    List<StringPair> getDealerAction();

    /**
     * @return the hand classfiier module (object) for the game
     */
    HandClassifier getHandClassifier();

    /**
     * @return the bet evaluator (used at the end of a round
     */
    BetEvaluator getBetEvaluator();

    /**
     * @return the entry bet for the game (specific or general)
     */
    String getEntryBet();

    /**
     * @return the card show policy (e.g. all, active or other)
     */
    String getCardShow();

    /**
     * @return the table goal for the game (e.g. tournament or cashgame)
     */
    String getGoal();

}
