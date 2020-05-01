package engine.player;

import engine.bet.Bet;

import java.util.List;

public interface PlayerInterface {

    /**
     * get name of current player
     * @return name of the player
     */
    String getName();

    /**
     * get bankroll of current player
     * @return current bankroll
     */
    double getBankroll();

    /**
     * get ID of current player
     * @return id (hashed at construction0
     */
    int getID();

    /**
     * return a list of the players current bets for card distribution
     * @return all total bets
     */
    List<Bet> getBets();

    /**
     * Place bet (of size wager)
     * @param wager size of bet
     * @return the bet that was created
     */
    Bet placeBet(double wager);

    /**
     * Place a preformatted bet
     * @param bet is placed, added to players list of bets
     */
    void placeBet(Bet bet);

    /**
     * Finds next active bet inside of the player
     * @return next bet in list of bets
     */
    Bet getNextBet();

    /**
     * Signalled by the controller to cash bets (update bankroll)
     */
    void cashBets();
}
