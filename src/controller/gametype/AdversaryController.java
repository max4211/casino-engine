package controller.gametype;

import UI.GameView.GameView;
import Utility.CardTriplet;
import Utility.Generator;
import Utility.StringPair;
import actionFactory.Action;
import actionFactory.ActionFactory;
import controller.bundles.ControllerBundle;
import controller.enums.Cardshow;
import controller.enums.Competition;
import controller.enums.EntryBet;
import controller.enums.Goal;
import controller.interfaces.ControllerInterface;
import controller.interfaces.GarbageCollect;
import engine.adversary.Adversary;
import engine.bet.Bet;
import engine.dealer.Card;
import engine.evaluator.bet.BetEvaluator;
import engine.evaluator.handclassifier.HandClassifier;
import engine.player.Player;
import engine.table.Table;
import exceptions.ReflectionException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class AdversaryController extends Controller {

    private Adversary myAdversary;

    // TODO - refactor into data files (in adversary construction?)
    private static final String MINIMUM_TAG = "Minimum";
    private int ADVERSARY_MIN = 17;

    @Deprecated
    public AdversaryController(Table table, GameView gameView, EntryBet entryBet, Collection<String> playerActions, StringPair dealerAction,
                      HandClassifier handClassifier, BetEvaluator betEvaluator,
                      Cardshow cardshow, Goal goal) {
        super(table, gameView, entryBet, playerActions, dealerAction,
                handClassifier, betEvaluator, cardshow, goal);
    }

    public AdversaryController(ControllerBundle bundle) {
        super(bundle);
    }

    public AdversaryController(ControllerBundle bundle, Map<String, String> params) {
        super(bundle);
        assignParams(params);
    }

    private void assignParams(Map<String, String> params) {

    }

    @Override
    public void startGame() {
        promptForEntryBet();
        performDealerAction(this.myDealerAction);
        updatePlayerHands();
        renderAdversary();
        promptForActions();
        garbageCollect();
        computePayoffs();
        updateBankrolls();
        showGameViewRestart();
        restartGame();
    }

    @Override
    protected void promptForEntryBet() {
        for (Player p: this.myTable.getPlayers()) {
            this.myGameView.setMainPlayer(p.getID());
            double min = this.myTable.getTableMin();
            double max = Math.min(this.myTable.getTableMax(), p.getBankroll());
            double wager = this.myGameView.selectWager(min, max);
            Bet b = this.myTable.placeEntryBet(p.getID(), this.myEntryBet, wager);
            this.myGameView.addBet(new ArrayList<>(), wager, p.getID(), b.getID());
            this.myGameView.setBankRoll(p.getBankroll(), p.getID());
        }
    }

    protected void renderAdversary() {
        this.myAdversary = this.myTable.createAdversary(ADVERSARY_MIN);
        List<CardTriplet> list = Generator.createTripletList(this.myAdversary.getHand());
        this.myGameView.renderAdversary(list);
        this.myGameView.showAdversaryCard(this.myAdversary.getCard().getID());
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
                addCardToPlayer(p);
                this.myGameView.setWager(b.getWager(), p.getID(), b.getID());
                this.myGameView.setBankRoll(p.getBankroll(), p.getID());
            } catch (ReflectionException e) {
                this.myGameView.displayException(e);
                System.out.println(e);
            }
        }
    }

    @Override
    protected void computePayoffs() {
        invokeCompetition();
        this.myHandClassifier.classifyHand(this.myAdversary.getHand());
        for (Player p: this.myTable.getPlayers()) {
            for (Bet b: p.getBets()) {
                this.myBetEvaluator.evaluateHands(b.getHand(), this.myAdversary.getHand());
                System.out.printf("%s's hand is a %s\n", p.getName(), b.getHand().getOutcome().toString());
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
