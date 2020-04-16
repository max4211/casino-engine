package controller.gametype;

import UI.GameView.GameView;
import Utility.CardTriplet;
import Utility.Generator;
import Utility.StringPair;
import actionFactory.Action;
import actionFactory.ActionFactory;
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

public class GroupController extends Controller {

    // TODO refactor items into a map of objects
    public GroupController(Table table, GameView gameView, EntryBet entryBet, Collection<String> playerActions, StringPair dealerAction,
                               HandClassifier handClassifier, BetEvaluator betEvaluator,
                               Cardshow cardshow, Goal goal) {
        super(table, gameView, entryBet, playerActions, dealerAction,
                handClassifier, betEvaluator, cardshow, goal);
    }

    @Override
    public void startGame() {
        promptForEntryBet();
        performDealerAction();
        updatePlayerHands();
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

    protected void performDealerAction() {
        this.myTable.performDealerAction(this.myDealerAction);
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
