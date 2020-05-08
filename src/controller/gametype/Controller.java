package controller.gametype;

import UI.GameView.GameView;
import UI.Utilities.CardTriplet;
import Utility.Generator;
import Utility.StringPair;
import actions.factory.ActionFactory;
import controller.bundles.ControllerBundle;
import controller.cardshow.CardShow;
import controller.cardshow.CardShowFactory;
import controller.enums.Cardshow;
import controller.enums.EntryBet;
import controller.goal.Goal;
import controller.goal.GoalFactory;
import controller.interfaces.ControllerInterface;
import controller.interfaces.GarbageCollect;
import engine.bet.Bet;
import engine.dealer.Card;
import engine.evaluator.bet.BetEvaluator;
import engine.evaluator.handclassifier.HandClassifier;
import engine.hand.HandOutcome;
import engine.player.Player;
import engine.table.Table;
import exceptions.ReflectionException;

import java.util.Collection;
import java.util.List;

/**
 * Abstract controller object, drives the flow within the game
 * and facilitates information exchange between the model and view
 * Uses services of both sides to update model information and get input from the user in the view
 * @author Max Smith
 */
public abstract class Controller implements ControllerInterface {

    protected Table myTable;
    protected GameView myGameView;
    protected final Collection<String> myPlayerActions;
    protected final List<StringPair> myDealerAction;
    protected final ActionFactory myFactory;
    protected final HandClassifier myHandClassifier;
    protected final BetEvaluator myBetEvaluator;

    protected final String myEntryBet;
    protected CardShow myCardshow;
    protected Goal myGoal;

    /**
     * Construct a controller from bundle of parameters and action type
     * @param bundle is the Contrllerbundle object, has all of controller parameters with appropriate getters
     * @param actionType is the type of action (for action factory)
     */
    public Controller(ControllerBundle bundle, String actionType) {
        this.myTable = bundle.getTable();
        this.myGameView = bundle.getGameView();
        this.myEntryBet = bundle.getEntryBet();
        this.myPlayerActions = bundle.getPlayerActions();
        this.myDealerAction = bundle.getDealerAction();
        this.myFactory = new ActionFactory(actionType);
        this.myHandClassifier =  bundle.getHandClassifier();
        this.myBetEvaluator = bundle.getBetEvaluator();
        this.myCardshow = createCardShow(bundle.getCardShow());
        this.myGoal = createGoal(bundle.getGoal());
        renderPlayers();
    }

    protected void preClassifyHands() {
        for (Player p: this.myTable.getPlayers()) {
            for (Bet b: p.getActiveBets()) {
                classifyHand(b);
                this.myGameView.classifyHand(b.getHand().getClassification().getName(), p.getID(), b.getID());
            }
        }
    }

    protected abstract void inRoundLoop(StringPair dealerAction);

    protected abstract void postRoundLoop();

    private controller.goal.Goal createGoal(String goal) {
        try {
            GoalFactory factory = new GoalFactory();
            return factory.create(goal, () -> this.myTable.getPlayers());
        } catch (Exception e) {
            throw new ReflectionException();
        }
    }

    private CardShow createCardShow(String cardShow) {
        try {
            CardShowFactory factory = new CardShowFactory();
            return factory.create(cardShow,
                    () -> this.myTable.getPlayers(),
                    (pid, bid, cid) -> this.myGameView.showCard(pid, bid, cid),
                    (pid, bid, cid) -> this.myGameView.hideCard(pid, bid, cid),
                    (triplet, pid, bid) -> this.myGameView.addCardIfAbsent(triplet, pid, bid));
        } catch (Exception e) {
            throw new ReflectionException();
        }
    }

    protected void showGameViewRestart() {
        this.myGameView.promptNewGame(this::restartGame);
    }

    protected void restartGame() {
        this.myTable.restartGame();
        this.myGameView.clearAllBets();
        this.myGameView.clearAdversary();
        this.startGame();
    }

    protected void renderPlayers() {
        for (Player p: this.myTable.getPlayers())
            this.myGameView.addPlayer(p.getName(), p.getID(), p.getBankroll());
    }

    protected abstract void promptForEntryBet();

    protected void performDealerAction(StringPair dealerAction) {
        this.myTable.performDealerAction(dealerAction);
    }

    protected abstract void promptForActions();

    protected void garbageCollect() {
        GarbageCollect.clearLosers(this.myTable.getPlayers(),
                (pid, bid) -> this.myGameView.removeBet(pid, bid),
                (pid, bid) -> this.myGameView.setLoser(pid, bid));
    }

    protected void garbageCollect(Player p, Bet b) {
        if (b.getHand().isLoser() || !b.isGameActive())
            this.myGameView.removeBet(p.getID(), b.getID());
    }

    protected abstract void computePayoffs();

    protected void addCardToPlayer(Player p) {
        for (Bet b: p.getBets()) {
            for (Card c: b.getHand().getCards()) {
                this.myGameView.addCardIfAbsent(Generator.createCardTriplet(c), p.getID(), b.getID());
                this.myCardshow.show(p);
            }
        }
    }

    protected abstract void classifyHand(Bet b);

    protected void updatePlayerHands() {
        for (Player p: this.myTable.getPlayers()) {
            int playerID = p.getID();
            for (Bet b: p.getActiveBets()) {
                int betID = b.getID();
                for (Card c: b.getHand().getCards()) {
                    CardTriplet cardTriplet = Generator.createCardTriplet(c);
                    this.myGameView.addCardIfAbsent(cardTriplet, playerID, betID);
                }
            }
        }
    }

    protected void updateCommunalCards() {
        List<Card> communalCards = this.myTable.getCommunalCards();
        List<CardTriplet> tripletList = Generator.createTripletList(communalCards);
        this.myGameView.renderCommonCards(tripletList);
    }

    protected void showCommonCards() {
        List<Card> communalCards = this.myTable.getCommunalCards();
        for (Card c: communalCards)
            this.myGameView.showCommonCard(c.getID());
    }

    protected void updateBankrolls() {
        for (Player p: this.myTable.getPlayers()) {
            p.cashBets();
            this.myGameView.setBankRoll(p.getBankroll(), p.getID());
        }
    }

    protected void postActionUpdates(int pID, double pBankroll, Bet b) {
        this.myGameView.setWager(b.getWager(), pID, b.getID());
        this.myGameView.setBankRoll(pBankroll, pID);
        this.myGameView.classifyHand(b.getHand().getClassification().getName(), pID, b.getID());
        if (b.getHand().getOutcome().equals(HandOutcome.LOSS))
            this.myGameView.setLoser(pID, b.getID());
    }

    protected abstract void evaluateBets();

    protected void showGoals() {
        this.myGameView.displayText(this.myGoal.evaluate());
    }

    protected void updateWinnersLoser() {
        for (Player p: this.myTable.getPlayers()) {
            for (Bet b: p.getBets()) {
                HandOutcome outcome = b.getHand().getOutcome();
                updateVisualOutcome(p.getID(), b.getID(), outcome, p.getName());
            }
        }
    }

    private void updateVisualOutcome(int pID, int bID, HandOutcome outcome, String name) {
        if (outcome.equals(HandOutcome.WIN))
            this.myGameView.setWinner(pID, bID);
        else if (outcome.equals(HandOutcome.LOSS)) {
            try {
                this.myGameView.setLoser(pID, bID);
            } catch (NullPointerException ignored) {
                ;
            }
        }

    }

    protected void setActingPlayer(Player p) {
        this.myGameView.setMainPlayer(p.getID());
        this.myCardshow.show(p);
    }

}
