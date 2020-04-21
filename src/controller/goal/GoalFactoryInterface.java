package controller.goal;

import engine.player.Player;

import java.util.Collection;
import java.util.function.Supplier;

public interface GoalFactoryInterface {

    /**
     * Called by controller to create goal type
     * @param goal
     * @return
     */
    Goal create(String goal, Supplier<Collection<Player>> getPlayers);
}
