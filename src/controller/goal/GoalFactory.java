package controller.goal;

import engine.player.Player;
import exceptions.ReflectionException;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.function.Supplier;

public class GoalFactory implements GoalFactoryInterface {

    private static final String GOAL_PATH = "controller.goal";

    public GoalFactory() {

    }

    private String createGoalPath(String goal) {
        return String.format("%s.%s", GOAL_PATH, goal);
    }

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
