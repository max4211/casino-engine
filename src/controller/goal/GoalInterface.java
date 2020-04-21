package controller.goal;

public interface GoalInterface {

    /**
     * Evaluate the current state of the goal being reached
     * @return a string representing the status of the goal
     */
    String evaluate();
}
