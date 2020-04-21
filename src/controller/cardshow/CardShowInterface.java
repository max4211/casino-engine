package controller.cardshow;

import engine.player.Player;

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
