package controller.gametype;

import Utility.StringPair;
import actionFactory.Action;
import controller.bundles.ControllerBundle;
import engine.bet.Bet;
import engine.player.Player;
import exceptions.ReflectionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GroupController extends Controller {

    private static final String ANTE_TAG = "Ante";
    private double myAnte;

    public GroupController(ControllerBundle bundle, Map<String, String> params) {
        super(bundle);
        assignParams(params);
    }

    private void assignParams(Map<String, String> params) {
        this.myAnte = Double.parseDouble(params.get(ANTE_TAG));
    }

    @Override
    public void startGame() {
        promptForEntryBet();
        for (StringPair s: this.myDealerAction) {
            performDealerAction(s);
            updatePlayerHands();
            updateCommunalCards();
            showCommonCards();
            promptForActions();
        }
        computePayoffs();
        updateBankrolls();
        showGameViewRestart();
    }

    @Override
    protected void promptForEntryBet() {
        for (Player p: this.myTable.getPlayers()) {
            Bet b = new Bet(myAnte);
            p.placeBet(b);
            this.myGameView.addBet(new ArrayList<>(), myAnte, p.getID(), b.getID());
            this.myGameView.setBankRoll(p.getBankroll(), p.getID());
        }
    }

    @Override
    protected void promptForActions() {
        while (this.myTable.hasActivePlayers()) {
            Player p = this.myTable.getNextPlayer();
            this.myGameView.setMainPlayer(p.getID());
            cardShow(p);
            try {
                Action a = this.myFactory.createAction(this.myGameView.selectAction((ArrayList<String>) this.myPlayerActions));
                Bet b = p.getNextBet();
                a.execute(p, b, this.myTable.getDealCardMethod());
                classifyHand(b);
                this.myGameView.setWager(b.getWager(), p.getID(), b.getID());
                this.myGameView.setBankRoll(p.getBankroll(), p.getID());
                garbageCollect();
            } catch (ReflectionException e) {
                this.myGameView.displayException(e);
                System.out.println(e);
            }
        }
    }

    @Override
    protected void computePayoffs() {
        List<Bet> allBets = createListOfBets();
        this.myBetEvaluator.evaluateBets(allBets);
    }

    private List<Bet> createListOfBets() {
        List<Bet> list = new ArrayList<>();
        for (Player p: this.myTable.getPlayers()) {
            list.addAll(p.getBets());
            this.myGameView.setBankRoll(p.getBankroll(), p.getID());
        }
        return list;
    }

}
