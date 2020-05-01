package controller.interfaces;

/**
 * Single publicly available method call on controller (to start the game)
 * @author Max Smith
 */
public interface ControllerInterface {

    /**
     * Signal from the game constructor to enter the game loop
     * Called once all objects have successfully been created
     */
    void startGame();
}
