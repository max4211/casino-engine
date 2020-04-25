package controller.gametype;

import Utility.StringPair;
import actions.group.GroupAction;
import controller.bundles.ControllerBundle;
import engine.bet.Bet;
import engine.dealer.Card;
import engine.hand.ClassifiedHand;
import engine.player.Player;
import engine.pot.Pot;
import exceptions.ActionException;
import exceptions.ReflectionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GroupController extends Controller {

    private static final String ACTION_TYPE = "group";
    private static final String ANTE_TAG = "Ante";
    private static final int ZERO = 0;
    private Pot myPot;
    private double myAnte;

    public GroupController(ControllerBundle bundle, Map<String, String> params) {
        super(bundle, ACTION_TYPE);
        assignParams(params);
        createPot();
    }

    private void createPot() {
        this.myPot = new Pot();
        this.myGameView.renderPot(this.myPot.getPot());
    }

    private void assignParams(Map<String, String> params) {
        this.myAnte = Double.parseDouble(params.get(ANTE_TAG));
    }

    @Override
    public void startGame() {
        promptForEntryBet();
        for (StringPair s: this.myDealerAction) {
            if (this.myTable.totalActivePlayers() <= 1)
                return;
            inRoundLoop(s);
        }
        postRoundLoop();
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
    protected void inRoundLoop(StringPair dealerAction) {
        resetRound();
        performDealerAction(dealerAction);
        preClassifyHands();
        updatePlayerHands();
        updateCommunalCards();
        showCommonCards();
        promptForActions();
    }

    @Override
    protected void postRoundLoop() {
        this.myCardshow.showAllCards();
        evaluateBets();
        updateWinnersLoser();
        computePayoffs();
        updateBankrolls();
        showGoals();
        showGameViewRestart();
    }

    @Override
    protected void promptForEntryBet() {
        for (Player p: this.myTable.getPlayers()) {
            Bet b = new Bet(myAnte);
            p.placeBet(b);
            this.myGameView.addBet(myAnte, p.getID(), b.getID());
            this.myGameView.setBankRoll(p.getBankroll(), p.getID());
        }
    }

    @Override
    protected void promptForActions() {
        while (this.myTable.hasActivePlayers()) {
            Player p = this.myTable.getNextPlayer();
            setActingPlayer(p);
            try {
                GroupAction a = this.myFactory.createGroupAction(this.myGameView.selectAction((ArrayList<String>) this.myPlayerActions));
                Bet b = p.getNextBet();
                a.execute(p, b,
                        (min, max) -> this.myGameView.selectWager(min, max), (wager) -> this.myTable.setCurrentBet(wager), this::setBetsActive,
                        this.myTable.getTableMin(), this.myTable.getTableMax(), this.myTable.getCurrentBet());
                classifyHand(b);
                postActionUpdates(p.getID(), p.getBankroll(), b);
                updatePot();
                garbageCollect(p, b);
            } catch (ReflectionException | ActionException e) {
                this.myGameView.displayException(e);
            }
        }
    }

    private void updatePot() {
        this.myPot.reset();
        List<Bet> allBets = createListOfBets();
        for (Bet b: allBets)
            this.myPot.add(b.getWager());
        this.myGameView.setPot(this.myPot.getPot());
    }

    @Override
    protected void computePayoffs() {
        List<Bet> allBets = createListOfBets();
        this.myPot.distributePot(allBets);
    }

    @Override
    protected void classifyHand(Bet b) {
        List<Card> fullHand = new ArrayList<Card>();
        fullHand.addAll(b.getHand().getCards());
        fullHand.addAll(this.myTable.getCommunalCards());
        this.myHandClassifier.classifyHand(fullHand, b.getHand());
        if (b.getHand().isLoser())
            b.setGameActive(false);
    }

    @Override
    protected void evaluateBets() {
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
