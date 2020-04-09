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
        this.myHandClassifier=  handClassifier;
        this.myBetEvaluator = betEvaluator;
        this.myCompetition = competition;
        System.out.println("competition: " + competition);
    }

    public void startGame() {
        renderPlayers();
        promptForEntryBet();
        performDealerAction();
        promptForActions();
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

    private void promptForActions() {
        while (this.myTable.hasActivePlayers()) {
            Player p = this.myTable.getNextPlayer();
            System.out.printf("prompting player (%s) for an action --> ", p.getName());
            this.myGameView.updateMainPlayer(p.getID());
            Action a = this.myFactory.createAction(this.myGameView.selectAction(this.myPlayerActions));
            a.execute(p, p.getNextBet());
            this.myTable.updateBets(p);
            garbageCollect(p);
        }
    }

    private void garbageCollect(Player p) {
        for (Bet b: p.getBets()) {
            this.myHandClassifier.classifyHand(b.getHand());
            if (b.getHand().isLoser()) {
                b.setActive(false);
                this.myGameView.removeBet(p.getID(), b.getID());
            }
        }
    }

    private void updatePlayerHands() {
        for (Player p: this.myTable.getPlayers()) {
            int playerID = p.getID();
            this.myGameView.updateMainPlayer(playerID);
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



    @Override
    public void acceptAction(String action) {

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
