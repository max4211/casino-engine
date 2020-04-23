package engine.pot;

import engine.bet.Bet;

import java.util.List;

public interface PotInterface {

    /**
     * Update pot to reflect wager
     * @param wager
     */
    void add(double wager);

    /**
     * Returns the total wager amount in the pot
     * @return
     */
    double getPot();

    /**
     * At the end of a round with a pot, the pot is called to distrbute funds to bets
     * @param bets
     */
    void distributePot(List<Bet> bets);

    /**
     * Reset pot to zero
     */
    void reset();
}
