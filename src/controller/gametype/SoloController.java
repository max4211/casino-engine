package controller.gametype;

import controller.bundles.ControllerBundle;
import engine.bet.Bet;

public class SoloController extends Controller {

    public SoloController(ControllerBundle bundle, String actionType) {
        super(bundle, actionType);
    }

    @Override
    protected void promptForEntryBet() {

    }

    @Override
    protected void promptForActions() {

    }

    @Override
    protected void computePayoffs() {

    }

    @Override
    protected void classifyHand(Bet b) {

    }

    @Override
    public void startGame() {

    }
}
