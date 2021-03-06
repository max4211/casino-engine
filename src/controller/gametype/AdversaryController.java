package controller.gametype;

import UI.Utilities.CardTriplet;
import Utility.Generator;
import Utility.StringPair;
import actions.individual.IndividualAction;
import controller.bundles.ControllerBundle;
import engine.adversary.Adversary;
import engine.bet.Bet;
import engine.dealer.Card;
import engine.hand.ClassifiedHand;
import engine.hand.PlayerHand;
import engine.player.Player;
import exceptions.ActionException;
import exceptions.ReflectionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Adversary controller is a type of game when it is players against the dealer
 * Bets are evaluated strictly against the dealer
 * @author Max Smith
 */
public class AdversaryController extends Controller {

    private Adversary myAdversary;

    private static final String ACTION_TYPE = "individual";
    private static final String MINIMUM_TAG = "Minimum";
    private double ADVERSARY_MIN;

    /**
     * Constructor for an adversary game
     * @param bundle is the bundle of parameters, assigned to protected objects in controller
     * @param params are the parameters for this specific game type
     */
    public AdversaryController(ControllerBundle bundle, Map<String, String> params) {
        super(bundle, ACTION_TYPE);
        assignParams(params);
    }

    private void assignParams(Map<String, String> params) {
        this.ADVERSARY_MIN = Double.parseDouble(params.get(MINIMUM_TAG));
    }

    /**
     * Begins the game, with specific modifications for an adversary game (render adversary)
     */
    @Override
    public void startGame() {
        promptForEntryBet();
        renderAdversary();
        for (StringPair s: this.myDealerAction) {
            inRoundLoop(s);
        }
        postRoundLoop();
    }

    @Override
    protected void inRoundLoop(StringPair dealerAction) {
        performDealerAction(dealerAction);
        updatePlayerHands();
        preClassifyHands();
        promptForActions();
        garbageCollect();
    }

    @Override
    protected void postRoundLoop() {
        invokeCompetition();
        showAllAdversaryCards();
        evaluateBets();
        computePayoffs();
        updateWinnersLoser();
        updateBankrolls();
        showGoals();
        showGameViewRestart();
    }

    @Override
    protected void promptForEntryBet() {
        for (Player p: this.myTable.getPlayers()) {
            this.myGameView.setMainPlayer(p.getID());
            double min = this.myTable.getTableMin();
            double max = Math.min(this.myTable.getTableMax(), p.getBankroll());
            double wager = this.myGameView.selectWager(min, max);
            Bet b = this.myTable.placeEntryBet(p.getID(), this.myEntryBet, wager);
            this.myGameView.addBet(wager, p.getID(), b.getID());
            this.myGameView.setBankRoll(p.getBankroll(), p.getID());
        }
    }

    protected void renderAdversary() {
        this.myAdversary = this.myTable.createAdversary(ADVERSARY_MIN);
        List<CardTriplet> list = Generator.createTripletList(this.myAdversary.getHand());
        this.myGameView.renderAdversary(list);
        showAdversaryCard(this.myAdversary.getCard());
    }

    private void showAdversaryCard(Card c) {
        this.myGameView.showAdversaryCard(c.getID());
    }

    private void showAllAdversaryCards() {
        this.myGameView.renderAdversary(Generator.createTripletList(this.myAdversary.getHand()));
        List<Card> list = this.myAdversary.getHand().getCards();
        for (Card c: list)
            showAdversaryCard(c);
    }

    @Override
    protected void promptForActions() {
        while (this.myTable.hasActivePlayers()) {
            Player p = this.myTable.getNextPlayer();
            setActingPlayer(p);
            try {
                Bet b = p.getNextBet();
                IndividualAction a = this.myFactory.createIndividualAction(this.myGameView.selectAction((ArrayList<String>) this.myPlayerActions));
                a.execute(p, b, this.myTable.getDealCardMethod());
                addCardToPlayer(p);
                classifyHand(b);
                postActionUpdates(p.getID(), p.getBankroll(), b);
            } catch (ReflectionException | ActionException e) {
                this.myGameView.displayException(e);
            }
        }
    }

    @Override
    protected void computePayoffs() {
        invokeCompetition();
    }

    @Override
    protected void classifyHand(Bet b) {
        this.myHandClassifier.classifyHand(b.getHand().getCards(), b.getHand());
        if (b.getHand().isLoser())
            b.setGameActive(false);
        ClassifiedHand ch = b.getHand().getClassification();
        ch.setName(Double.toString(ch.getPower()));
    }

    @Override
    protected void evaluateBets() {
        this.myHandClassifier.classifyHand(this.myAdversary.getHand().getCards(), this.myAdversary.getHand());
        for (Player p: this.myTable.getPlayers()) {
            for (Bet b: p.getBets()) {
                this.myBetEvaluator.evaluateHands(b.getHand(), this.myAdversary.getHand());
            }
        }
    }

    private void invokeCompetition() {
        this.myAdversary.playHand(
                (card) -> this.myGameView.showAdversaryCard(card),
                (triplet) -> this.myGameView.addAdversaryCard(triplet),
                this.myTable.getDealCardMethod());
    }

}
