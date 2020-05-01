package controller.cardshow;

import engine.player.Player;

/**
 * Implemented by CardShow object, enables showing cards (polymorhically, overriden implementation in concrete classes)
 * @author Max Smith
 */
public interface CardShowInterface {

    /**
     * Called on card show to show cards based on specific policy
     * @param p
     */
    void show(Player p);

    /**
     * Show all cards (called at end game)
     */
    void showAllCards();
}
