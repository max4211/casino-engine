package controller;

import GameView.NodeViews.GameView;
import Utility.CardTriplet;
import actionFactory.Action;
import actionFactory.ActionFactory;
import data.xmlreader.Pair;
import engine.bet.Bet;
import engine.dealer.Card;
import engine.evaluator.BetEvaluator;
import engine.evaluator.HandClassifier;
import engine.player.Player;
import engine.table.Table;

import java.util.ArrayList;
import java.util.List;

public class Controller implements ControllerInterface {

    private Table myTable;
    private GameView myGameView;
    private final String myEntryBet;
    private final List<String> myPlayerActions;
    private final Pair myDealerAction;
    private final ActionFactory myFactory;
    private final HandClassifier myHandClassifier;
    private final BetEvaluator myBetEvaluator;
    private final String myCompetition;

    public Controller(Table table, GameView gameView, String entryBet, List<String> playerActions, Pair dealerAction,
                      HandClassifier handClassifier, BetEvaluator betEvaluator, String competition) {
        this.myTable = table;
        this.myGameView = gameView;
        this.myEntryBet = entryBet;
        this.myPlayerActions = playerActions;
        this.myDealerAction = dealerAction;
        this.myFactory = new ActionFactory(playerActions);
        this.myHandClassifier =  handClassifier;
        this.myBetEvaluator = betEvaluator;
        this.myCompetition = competition;
    }

    @Override
    public void startGame() {
        renderPlayers();
        promptForEntryBet();
        performDealerAction();
        renderAdversary();
        promptForActions();
        garbageCollect();
        invokeCompetition();
    }

    private void renderPlayers() {
        for (Player p: this.myTable.getPlayers()) {
            this.myGameView.addPlayer(p.getName(), p.getID(), p.getBankroll());
        }
    }

    private void promptForEntryBet() {
        System.out.printf("prompting players for entry bet...\n");
        for (Player p: this.myTable.getPlayers()) {
            int playerHash = p.getID();
            this.myGameView.updateMainPlayer(playerHash);
            double min = this.myTable.getTableMin();
            double max = Math.min(this.myTable.getTableMax(), p.getBankroll());
            System.out.printf("min: %.1f, max: %.1f\n", min, max);
            double wager = this.myGameView.selectWager(min, max);
            int betID = this.myTable.placeEntryBet(playerHash, this.myEntryBet, wager);
            this.myGameView.addBet(new ArrayList<>(), wager, playerHash, betID);
        }
    }

    private void performDealerAction() {
        this.myTable.performDealerAction(this.myDealerAction);
        updatePlayerHands();
    }

    // TODO - render adversary
    private void renderAdversary() {
        if(this.myCompetition.equals(Competition.ADVERSARY)) {
            System.out.println("Rendering adversary ...");
        }
    }

    private void promptForActions() {
        while (this.myTable.hasActivePlayers()) {
            Player p = this.myTable.getNextPlayer();
            System.out.printf("prompting player (%s) for an action --> ", p.getName());
            this.myGameView.updateMainPlayer(p.getID());
            Action a = this.myFactory.createAction(this.myGameView.selectAction(this.myPlayerActions));
            Bet b = p.getNextBet();
            a.execute(p, b);
            Card c = this.myTable.updateBets(p);
            classifyHand(b);
            if (c != null) {
                this.myGameView.addCard(createCardTriplet(c), p.getID(), b.getID());
                this.myGameView.showCard(p.getID(), b.getID(), c.getID());
            }
            this.myGameView.updateWager(b.getWager(), p.getID(), b.getID());
        }
    }

    private void invokeCompetition() {

    }

    private void classifyHand(Bet b) {
        this.myHandClassifier.classifyHand(b.getHand());
        if (b.getHand().isLoser()) {
            b.setActive(false);
        }
    }

    private void garbageCollect() {
        for (Player p: this.myTable.getPlayers()) {
            for (Bet b: p.getBets()) {
                if (b.getHand().isLoser()) {
                    this.myGameView.removeBet(p.getID(), b.getID());
                }
            }
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
                    this.myGameView.showCard(playerID, betID, c.getID());
                }
            }
        }
    }

    private CardTriplet createCardTriplet(Card c) {
        return new CardTriplet(c.getValue(), c.getSuit(), c.getID());
    }

    /** Max's team code for reflection example within their execution (shows how to invoke a method)
     *
     *     private List<String> executeCommand(Command command) {
     *         try {
     *             Class superclazz = command.getClass().getSuperclass();
     *             String name = EXECUTE + superclazz.getSimpleName();
     *             Method method = this.getClass().getDeclaredMethod(name, superclazz); //Command.class
     *             Object o = method.invoke(this, command);
     *             return (List<String>) o;
     *         } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException | NullPointerException e) {
     *             throw new ReflectionException("Unable to apply Reflection in parser");
     *         }
     *     }
     */


}
