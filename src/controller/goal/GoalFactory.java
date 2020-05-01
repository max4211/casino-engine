package controller.goal;

import engine.player.Player;
import exceptions.ReflectionException;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.function.Supplier;

/**
 * Abstract factory to generate goal objects, controller can use polymorphically
 * to monitor high level gameplay and generate splash screens
 * @author Max Smith
 */
public class GoalFactory implements GoalFactoryInterface {

    private static final String GOAL_PATH = "controller.goal";

    private String createGoalPath(String goal) {
        return String.format("%s.%s", GOAL_PATH, goal);
    }

    /**
     * Single create method to return abstract goal to controller
     * @param goal is the goal of the game (as validated and parsed from XML)
     * @param getPlayers is a functional interface to get all players from the table
     * @returns the correct goal policy
     */
    @Override
    public Goal create(String goal, Supplier<Collection<Player>> getPlayers) {
        try {
            Class clazz = Class.forName(createGoalPath(goal));
            Constructor ctor = clazz.getConstructor(Supplier.class);
            return (Goal) ctor.newInstance(getPlayers);
        } catch (Exception e) {
            throw new ReflectionException(e);
        }
    }
}
