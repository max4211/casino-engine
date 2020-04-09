package actionFactory;

import engine.bet.Bet;

public class Split extends Action {

    public Split() {
        super();
        System.out.println("Created a hit action");
    }

    // TODO implement split method
    @Override
    public void execute(Bet target) {
        target.setActive(false);
    }
}
