package controller;

import GameView.GameView;
import data.xmlreader.Pair;
import engine.bet.Bet;
import engine.table.Table;

import java.util.List;

public class Controller implements ControllerInterface {

    private Table myTable;
    private GameView myGameView;
    private final String myEntryBet;
    private final List<String> myPlayerActions;
    private final Pair myDealerAction;

    // TODO - construct controller with a view object
    public Controller(Table table, GameView gameView, String entryBet, List<String> playerActions, Pair dealerAction) {
        this.myTable = table;
        this.myGameView = gameView;
        this.myEntryBet = entryBet;
        this.myPlayerActions = playerActions;
        this.myDealerAction = dealerAction;
    }

    // TODO - place entry bet and perform player action inside of the view, register inside the model
    public void startGame() {
        promptForEntryBet();
        performDealerAction();
        promptForActions();
    }

    private void performDealerAction() {
        this.myTable.performDealerAction(this.myDealerAction);
        // player number, bet number, card number (addCard in CardTriplet form)
        // Send a consumer to the back end to tell the front end a card has been distributed
        // this.myGameView.addCard(p.hash, b.hash, card.value)
        // this.myGameView.showNewCards();
    }

    private void promptForActions() {
        for (int i = 0; i < this.myTable.totalPlayers(); i ++) {
            // TODO - prompt action to be performed on front end
            // 0: Get next active Player from table (implement tags for bets of active in round)
            // 1: Controller tells front end who is up (player ID)
            // 2. Present view with action box and get string of action type
            // 3. Perform player action (getAction (List<String> s) - always called on main player
            // 4. Tell backend to do action
            // this.myTable.performPlayerAction(this.myPlayerActions, (action) -> this.acceptAction(action));
        }
    }

    private void promptForEntryBet() {
        // this.myTable.placeEntryBet(this.myEntryBet, (bet) -> this.acceptBet(bet));
        for (int i = 0; i < this.myTable.totalPlayers(); i ++) {
            // TODO - prompt frontend for bet
            // this.myGameView.promptPlayerBet(int playerId, int betMin, int betMax);
            // Player hashcode (general object hashcode is the id number)
            // 1. Set main with player ID
            // 2. Prompt for bet
            // How do I recieve this result? Lambda? Pause until another method is called?
            // this.myTable.placeEntryBet(this.myEntryBet, (bet) -> this.acceptBet(bet));
            // this.myTable.performPlayerAction(this.myPlayerActions, (action) -> this.acceptAction(action));
        }
    }

    @Override
    public void acceptBet(Bet bet) {

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
