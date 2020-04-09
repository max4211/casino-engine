package actionFactory;

import engine.bet.Bet;

public interface ActionInterface {

    /**
     * Actions execute on a specific bet
     * @param target bet that the action executes on
     */
    void execute(Bet target);
}
