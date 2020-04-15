package controller;

import GameView.NodeViews.GameView;
import Utility.CardTriplet;
import actionFactory.Action;
import actionFactory.ActionFactory;
import Utility.StringPair;
import engine.adversary.Adversary;
import engine.bet.Bet;
import engine.dealer.Card;
import engine.evaluator.bet.BetEvaluator;
import engine.evaluator.handclassifier.HandClassifier;
import engine.hand.PlayerHand;
import engine.player.Player;
import engine.table.Table;
import exceptions.ReflectionException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Controller implements ControllerInterface {

    private Table myTable;
    private GameView myGameView;
    private final Collection<String> myPlayerActions;
    private final StringPair myDealerAction;
    private final ActionFactory myFactory;
    private final HandClassifier myHandClassifier;
    private final BetEvaluator myBetEvaluator;
    private Adversary myAdversary;

    private final EntryBet myEntryBet;
    private final Competition myCompetition;
    private Cardshow myCardshow;
    private Goal myGoal;

    private static final int SLEEP_TIME = 2000;

    // TODO - refactor into data files (in adversary construction?)
    private static final int ADVERSARY_MIN = 17;

    // TODO refactor items into a map of objects
    public Controller(Table table, GameView gameView, EntryBet entryBet, Collection<String> playerActions, StringPair dealerAction,
                      HandClassifier handClassifier, BetEvaluator betEvaluator,
                      Competition competition, Cardshow cardshow, Goal goal) {
        this.myTable = table;
        this.myGameView = gameView;
        this.myEntryBet = entryBet;
        this.myPlayerActions = playerActions;
        this.myDealerAction = dealerAction;
        this.myFactory = new ActionFactory();
        this.myHandClassifier =  handClassifier;
        this.myBetEvaluator = betEvaluator;
        this.myCompetition = competition;
        this.myCardshow = cardshow;
        this.myGoal = goal;
        renderPlayers();
    }

    @Override
    public void startGame() {
        promptForEntryBet();
        performDealerAction();
        updatePlayerHands();
        renderAdversary();
        promptForActions();
        garbageCollect();
        invokeCompetition();
        computePayoffs();
        restartGame();
    }

    private void restartGame() {
        try {
            Thread.sleep(SLEEP_TIME);
            this.myGameView.clearAllBets();
            this.myGameView.clearAdversary();
            this.startGame();
        } catch (Exception e) {
            System.exit(0);
        }
    }

    private void renderPlayers() {
        for (Player p: this.myTable.getPlayers()) {
            this.myGameView.addPlayer(p.getName(), p.getID(), p.getBankroll());
        }
    }

    private void promptForEntryBet() {
        for (Player p: this.myTable.getPlayers()) {
            int playerHash = p.getID();
            this.myGameView.setMainPlayer(playerHash);
            double min = this.myTable.getTableMin();
            double max = Math.min(this.myTable.getTableMax(), p.getBankroll());
            double wager = this.myGameView.selectWager(min, max);
            int betID = this.myTable.placeEntryBet(playerHash, this.myEntryBet, wager);
            this.myGameView.addBet(new ArrayList<>(), wager, playerHash, betID);
        }
    }

    private void performDealerAction() {
        this.myTable.performDealerAction(this.myDealerAction);
    }

    private void renderAdversary() {
        if(isAdversaryGame()) {
            this.myAdversary = this.myTable.createAdversary(ADVERSARY_MIN);
            this.myGameView.renderAdversary(parseAdversary(this.myAdversary.getHand()));
            this.myGameView.showAdversaryCard(this.myAdversary.getCard().getID());
        }
    }

    // TODO - only change action if changes occured?
    private void promptForActions() {
        while (this.myTable.hasActivePlayers()) {
            Player p = this.myTable.getNextPlayer();
            cardShow(p);
            System.out.printf("prompting player (%s) for an action --> ", p.getName());
            this.myGameView.setMainPlayer(p.getID());
            try {
                Action a = this.myFactory.createAction(this.myGameView.selectAction((ArrayList<String>) this.myPlayerActions));
                Bet b = p.getNextBet();
                a.execute(p, b, this.myTable.getDealCardMethod());
                classifyHand(b);
                addCardToPlayer(p);
                this.myGameView.updateWager(b.getWager(), p.getID(), b.getID());
            } catch (ReflectionException e) {
                this.myGameView.displayError(e);
                System.out.println(e);
            }
        }
    }

    private void garbageCollect() {
//        GarbageCollect.clearLosers(this.myTable.getPlayers(), (pid, bid) -> this.myGameView.removeBet(pid, bid));
        for (Player p: this.myTable.getPlayers()) {
            for (Bet b: p.getBets()) {
                if (b.getHand().isLoser()) {
                    this.myGameView.removeBet(p.getID(), b.getID());
                }
            }
        }
    }

    private void invokeCompetition() {
        if (isAdversaryGame()) {
            adversaryActionLoop();
        } else {
            // TODO - group evaluation
        }
    }

    // TODO - use reflection for payoffs in adversary vs. group competition game
    private void computePayoffs() {
        if (isAdversaryGame()) {
            this.myHandClassifier.classifyHand(this.myAdversary.getHand());
            for (Player p: this.myTable.getPlayers()) {
                for (Bet b: p.getBets()) {
                    this.myBetEvaluator.evaluateHands(b.getHand(), this.myAdversary.getHand());
                    System.out.printf("%s's hand is a %s\n", p.getName(), b.getHand().getOutcome().toString());
                }
                p.cashBets();
                this.myGameView.updateBankRoll(p.getBankroll(), p.getID());
            }
        }
    }

    // TODO - refactor gameview to validating elements as they are received
    private void addCardToPlayer(Player p) {
        for (Bet b: p.getBets()) {
            for (Card c: b.getHand().getCards()) {
                this.myGameView.removeBet(p.getID(), b.getID());
                this.myGameView.addBet(createTripletList(b.getHand()), b.getWager(), p.getID(), b.getID());
                this.myGameView.removeCard(p.getID(), b.getID(), c.getID());
                this.myGameView.addCard(createCardTriplet(c), p.getID(), b.getID());
            }
        }
    }

    private List<CardTriplet> createTripletList(PlayerHand h) {
        List<CardTriplet> list = new ArrayList<>();
        for (Card c: h.getCards())
            list.add(createCardTriplet(c));
        return list;
    }

    // TODO - refactor cardshow and competition to reflection
    private void cardShow(Player p) {
        if (isAllCardshow()) {
            showAllCards();
        } else if (isActiveCardshow()) {
            showActiveCards(p);
        }
    }

    // TODO - refactor to lambdas in adversary
    private void adversaryActionLoop() {
        showAdversaryCards();
        while (this.myAdversary.wantsCards()) {
            this.myGameView.addAdversaryCard(createCardTriplet(this.myTable.giveAdversaryCard()));
            showAdversaryCards();
        }
    }

    private void showAdversaryCards() {
        for (Card c: this.myAdversary.getHand().getCards()) {
            System.out.println("Adversary card: " + c.toString());
            this.myGameView.showAdversaryCard(c.getID());
        }
    }

    private boolean isAdversaryGame() {
        return this.myCompetition.equals(Competition.ADVERSARY);
    }

    private boolean isAllCardshow() {
        return this.myCardshow.equals(Cardshow.ALL);
    }

    private boolean isActiveCardshow() {
        return this.myCardshow.equals(Cardshow.ACTIVE);
    }

    private void showAllCards() {
        for (Player p: this.myTable.getPlayers()) {
            for (Bet b: p.getBets()) {
                for (Card c: b.getHand().getCards()) {
                    this.myGameView.showCard(p.getID(), b.getID(), c.getID());
                }
            }
        }
    }

    private void showActiveCards(Player p) {
        hideCards(p);
        for (Bet b: p.getBets()) {
            for (Card c: b.getHand().getCards()) {
                this.myGameView.showCard(p.getID(), b.getID(), c.getID());
            }
        }
    }

    private void hideCards(Player p) {
        for (Player player: this.myTable.getPlayers()) {
            if (!player.equals(p)) {
                for (Bet b: p.getBets()) {
                    for (Card c: b.getHand().getCards()) {
                        this.myGameView.removeCard(p.getID(), b.getID(), c.getID());
                        this.myGameView.addCard(createCardTriplet(c), p.getID(), b.getID());
                        this.myGameView.hideCard(p.getID(), b.getID(), c.getID());
                    }
                }
            }
        }
    }

    private void classifyHand(Bet b) {
        this.myHandClassifier.classifyHand(b.getHand());
        if (b.getHand().isLoser()) {
            b.setActive(false);
        }
    }

    private void updatePlayerHands() {
        for (Player p: this.myTable.getPlayers()) {
            int playerID = p.getID();
            for (Bet b: p.getBets()) {
                int betID = b.getID();
                for (Card c: b.getHand().getCards()) {
                    CardTriplet cardTriplet = createCardTriplet(c);
                    this.myGameView.addCard(cardTriplet, playerID, betID);
                }
            }
        }
    }

    private CardTriplet createCardTriplet(Card c) {
        return new CardTriplet(c.getValue(), c.getSuit(), c.getID());
    }

    private List<CardTriplet> parseAdversary(PlayerHand h) {
        List<CardTriplet> list = new ArrayList<>();
        for (Card c: h.getCards()) {
            list.add(createCardTriplet(c));
        }
        return list;
    }

}
