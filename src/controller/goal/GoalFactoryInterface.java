package controller.goal;

import engine.player.Player;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * Interface implemented by goal factory, explicitly defines single purpose, generate Goal obejcts
 * @author Max Smith
 */
public interface GoalFactoryInterface {

    /**
     * Called by controller to create goal type
     * @param goal is the goal type of the game
     * @returns the concrete (abstractly casted) goal
     */
    Goal create(String goal, Supplier<Collection<Player>> getPlayers);
}
