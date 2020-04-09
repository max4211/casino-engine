package actionFactory;

import engine.bet.Bet;

public class Stay extends Action {

    public Stay() {
        super();
        System.out.println("Created a stay action");
    }

    @Override
    public void execute(Bet target) {
        target.setActive(false);
    }
}
