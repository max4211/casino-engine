package engine.pot;

import engine.bet.Bet;

import java.util.List;

/**
 * Implemented by a pot object, shows all publicly available method calls
 * @author Max Smith
 */
public interface PotInterface {

    /**
     * Update pot to reflect wager
     * @param wager is the wager to be added to the pots
     */
    void add(double wager);

    /**
     * Returns the total wager amount in the pot
     * @return total amount of monies in the pot
     */
    double getPot();

    /**
     * At the end of a round with a pot, the pot is called to distrbute funds to bets
     * @param bets bet objects to distribute funding to
     */
    void distributePot(List<Bet> bets);

    /**
     * Reset pot to zero
     */
    void reset();
}
