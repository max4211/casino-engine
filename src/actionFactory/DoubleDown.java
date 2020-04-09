package actionFactory;

import engine.bet.Bet;

public class DoubleDown extends Action {

    public DoubleDown() {
        super();
        System.out.println("Created a double down action");
    }

    // TODO implement double down execute (right now does nothing)
    @Override
    public void execute(Bet target) {
        target.setActive(false);
    }
}
