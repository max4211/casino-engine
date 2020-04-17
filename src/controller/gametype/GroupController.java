package controller.gametype;

import Utility.StringPair;
import actions.group.GroupAction;
import controller.bundles.ControllerBundle;
import engine.bet.Bet;
import engine.player.Player;
import exceptions.ReflectionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GroupController extends Controller {

    private static final String ACTION_TYPE = "group";
    private static final String ANTE_TAG = "Ante";
    private static final int ZERO = 0;
    private double myAnte;

    public GroupController(ControllerBundle bundle, Map<String, String> params) {
        super(bundle, ACTION_TYPE);
        assignParams(params);
    }

    private void assignParams(Map<String, String> params) {
        this.myAnte = Double.parseDouble(params.get(ANTE_TAG));
    }

    @Override
    public void startGame() {
        promptForEntryBet();
        for (StringPair s: this.myDealerAction) {
            if (this.myTable.totalActivePlayers() <= 1)
                break;
            resetRound();
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

    private void resetRound() {
        for (Player p: this.myTable.getPlayers()) {
            for (Bet b: p.getBets()) {
                if (b.isGameActive())
                    b.setRoundActive(true);
            }
        }
        this.myTable.setCurrentBet(ZERO);
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
                GroupAction a = this.myFactory.createGroupAction(this.myGameView.selectAction((ArrayList<String>) this.myPlayerActions));
                Bet b = p.getNextBet();
                a.execute(p, b,
                        (min, max) -> this.myGameView.selectWager(min, max), (wager) -> this.myTable.setCurrentBet(wager), this::setBetsActive,
                        this.myTable.getTableMin(), this.myTable.getTableMax(), this.myTable.getCurrentBet());
                classifyHand(b);
                this.myGameView.setWager(b.getWager(), p.getID(), b.getID());
                this.myGameView.setBankRoll(p.getBankroll(), p.getID());
                garbageCollect(p, b);
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
            list.addAll(p.getActiveBets());
            this.myGameView.setBankRoll(p.getBankroll(), p.getID());
        }
        return list;
    }

    private void setBetsActive(Bet initiator) {
        for (Player p: this.myTable.getPlayers()) {
            for (Bet other: p.getBets()) {
                if (shouldActivePlayer(other, initiator))
                    other.setRoundActive(true);
            }
        }
    }

    private boolean shouldActivePlayer(Bet other, Bet initiator) {
        boolean notSameBet = other.getID() != initiator.getID();
        boolean otherBetActive = other.isGameActive();
        return notSameBet && otherBetActive;
    }

}
