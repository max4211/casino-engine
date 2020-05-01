package engine.player;

import Utility.HashNoise;
import engine.bet.Bet;
import engine.hand.HandOutcome;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates a list of bets, name, and bankroll
 */
public class Player implements PlayerInterface {

    private final int myID;
    private final String myName;
    private double myBankroll;
    private List<Bet> myBets;

    /**
     * Construct a player from XML parameters
     * @param name the name of the player
     * @param bankroll the starting bankroll of the player
     */
    public Player(String name, double bankroll) {
        this.myName = name;
        this.myBankroll = bankroll;
        this.myBets = new ArrayList<Bet>();
        this.myID = HashNoise.addNoise(this);
    }

    /**
     * get name of current player
     * @return name of the player
     */
    @Override
    public String getName() {
        return this.myName;
    }

    /**
     * get bankroll of current player
     * @return current bankroll
     */
    @Override
    public double getBankroll() {
        return this.myBankroll;
    }

    /**
     * get ID of current player
     * @return id (hashed at construction0
     */
    @Override
    public int getID() {
        return this.myID;
    }

    /**
     * return a list of the players current bets for card distribution
     * @return all total bets
     */
    @Override
    public List<Bet> getBets() {
        return this.myBets;
    }

    /**
     * Return current active bets (non losers)
     * Called by garbage collection to avoid null pointers
     * @return list of active bets
     */
    public List<Bet> getActiveBets() {
        List<Bet> activeBets = new ArrayList<Bet>();
        for (Bet b: this.myBets) {
            if (!b.getHand().isLoser()) {
                activeBets.add(b);
            }
        }
        return activeBets;
    }

    /**
     * Place bet (of size wager)
     * @param wager size of bet
     * @return the bet that was created
     */
    @Override
    public Bet placeBet(double wager) {
        Bet bet = new Bet(wager);
        this.myBets.add(bet);
        this.myBankroll -= wager;
        return bet;
    }

    /**
     * Place a preformatted bet
     * @param bet is placed, added to players list of bets
     */
    @Override
    public void placeBet(Bet bet) {
        this.myBankroll -= bet.getWager();
        this.myBets.add(bet);
    }

    /**
     * Update player bankroll in response to placing wager in view
     * @param wager is the amount bet in the view
     */
    public void updateBankroll(double wager) {
        this.myBankroll -= wager;
    }

    /**
     * Finds next active bet inside of the player
     * @return next bet in list of bets
     */
    @Override
    public Bet getNextBet() {
        for (Bet b: this.myBets) {
            if (b.isGameActive()) {
                return b;
            }
        }
        return null;
    }

    /**
     * Signalled by the controller to cash bets (update bankroll)
     */
    @Override
    public void cashBets() {
        for (Bet b: this.myBets) {
            HandOutcome outcome = b.getHand().getOutcome();
            if (outcome.equals(HandOutcome.WIN)) {
                this.myBankroll += b.getPayoff();
            } else if (outcome.equals(HandOutcome.LOSS)) {
                ;
            } else if (outcome.equals(HandOutcome.TIE)) {
                this.myBankroll += b.getWager();
            }
        }
        this.myBets = new ArrayList<>();
    }
}
