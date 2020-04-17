package controller.gametype;

import UI.GameView.GameView;
import Utility.CardTriplet;
import Utility.Generator;
import Utility.StringPair;
import actionFactory.ActionFactory;
import controller.bundles.ControllerBundle;
import controller.enums.Cardshow;
import controller.enums.EntryBet;
import controller.enums.Goal;
import controller.interfaces.ControllerInterface;
import controller.interfaces.GarbageCollect;
import engine.bet.Bet;
import engine.dealer.Card;
import engine.evaluator.bet.BetEvaluator;
import engine.evaluator.handclassifier.HandClassifier;
import engine.player.Player;
import engine.table.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Controller implements ControllerInterface {

    protected Table myTable;
    protected GameView myGameView;
    protected final Collection<String> myPlayerActions;
    protected final List<StringPair> myDealerAction;
    protected final ActionFactory myFactory;
    protected final HandClassifier myHandClassifier;
    protected final BetEvaluator myBetEvaluator;

    protected final EntryBet myEntryBet;
    protected Cardshow myCardshow;
    protected Goal myGoal;

    public Controller(ControllerBundle bundle) {
        this.myTable = bundle.getTable();
        this.myGameView = bundle.getGameView();
        this.myEntryBet = bundle.getEntryBet();
        this.myPlayerActions = bundle.getPlayerActions();
        this.myDealerAction = bundle.getDealerAction();
        this.myFactory = new ActionFactory();
        this.myHandClassifier =  bundle.getHandClassifier();
        this.myBetEvaluator = bundle.getBetEvaluator();
        this.myCardshow = bundle.getCardShow();
        this.myGoal = bundle.getGoal();
        renderPlayers();
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

    protected void performDealerAction(StringPair dealerAction) {
        this.myTable.performDealerAction(dealerAction);
    }

    protected abstract void promptForActions();

    // TODO - clear out losers
    protected void garbageCollect() {
        GarbageCollect.clearLosers(this.myTable.getPlayers(), (pid, bid) -> this.myGameView.removeBet(pid, bid));
    }

    protected abstract void computePayoffs();

    // TODO - refactor gameview to validating elements as they are received
    protected void addCardToPlayer(Player p) {
        for (Bet b: p.getBets()) {
            for (Card c: b.getHand().getCards()) {
//                this.myGameView.removeBet(p.getID(), b.getID());
//                this.myGameView.addBet(createTripletList(b.getHand()), b.getWager(), p.getID(), b.getID());
//                this.myGameView.removeCard(p.getID(), b.getID(), c.getID());
                this.myGameView.addCardIfAbsent(Generator.createCardTriplet(c), p.getID(), b.getID());
            }
        }
    }

    // TODO - refactor cardshow and competition to reflection
    protected void cardShow(Player p) {
        if (isAllCardshow()) {
            showAllCards();
        } else if (isActiveCardshow()) {
            showActiveCards(p);
        }
    }

    protected boolean isAllCardshow() {
        return this.myCardshow.equals(Cardshow.ALL);
    }
    protected boolean isActiveCardshow() {
        return this.myCardshow.equals(Cardshow.ACTIVE);
    }

    protected void showAllCards() {
        for (Player p: this.myTable.getPlayers()) {
            for (Bet b: p.getBets()) {
                for (Card c: b.getHand().getCards()) {
                    this.myGameView.showCard(p.getID(), b.getID(), c.getID());
                }
            }
        }
    }

    protected void showActiveCards(Player p) {
        hideCards(p);
        for (Bet b: p.getBets()) {
            for (Card c: b.getHand().getCards()) {
                this.myGameView.showCard(p.getID(), b.getID(), c.getID());
            }
        }
    }

    protected void hideCards(Player p) {
        for (Player player: this.myTable.getPlayers()) {
            if (!(player.getID() == p.getID())) {
                System.out.printf("%s is active, hiding %s's cards\n", p.getName(), player.getName());
                for (Bet b: player.getBets()) {
                    for (Card c: b.getHand().getCards()) {
                        this.myGameView.addCardIfAbsent(Generator.createCardTriplet(c), player.getID(), b.getID());
                        this.myGameView.hideCard(player.getID(), b.getID(), c.getID());
                    }
                }
            }
        }
    }

    protected void classifyHand(Bet b) {
        this.myHandClassifier.classifyHand(b.getHand());
        if (b.getHand().isLoser()) {
            b.setActive(false);
        }
    }

    protected void updatePlayerHands() {
        for (Player p: this.myTable.getPlayers()) {
            int playerID = p.getID();
            for (Bet b: p.getBets()) {
                int betID = b.getID();
                for (Card c: b.getHand().getCards()) {
                    CardTriplet cardTriplet = Generator.createCardTriplet(c);
                    this.myGameView.addCardIfAbsent(cardTriplet, playerID, betID);
                }
            }
        }
    }

    //TODO add in gameview method to show communal cards
    protected void updateCommunalCards() {
        List<Card> communalCards = this.myTable.getCommunalLCards();
        List<CardTriplet> tripletList = Generator.createTripletList(communalCards);
        for (Card c: communalCards)
            System.out.printf("adding common card: %s\n", c.toString());
        this.myGameView.renderCommonCards(tripletList);
    }

    protected void updateBankrolls() {
        for (Player p: this.myTable.getPlayers()) {
            p.cashBets();
            this.myGameView.setBankRoll(p.getBankroll(), p.getID());
        }
    }

}
