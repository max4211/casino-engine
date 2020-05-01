package engine.evaluator.goal;

import engine.player.Player;

/**
 * Implemented by all goal objects to evaluate goals
 */
public interface GoalInterface {

    /**
     * Called to determine if the game goal has been achieved
     * @return boolean of whether or not goal is achieved
     */
    boolean goalAchieved();

    /**
     * Called after it has been determined a specific player won the game
     * @return the best player as far as goal is concerned (largest bankroll)
     */
    Player gameWinner();
}
