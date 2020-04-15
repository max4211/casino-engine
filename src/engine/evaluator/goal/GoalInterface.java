package engine.evaluator.goal;

import engine.player.Player;

public interface GoalInterface {

    /**
     * Called to determine if the game goal has been achieved
     * @return
     */
    boolean goalAchieved();

    /**
     * Called after it has been determined a specific player won the game
     * @return
     */
    Player gameWinner();
}
