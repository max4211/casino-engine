package controller.cardshow;

import engine.player.Player;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * Shows all cards all the time in the game
 * @author Max Smith
 */
public class All extends CardShow {
    public All(Supplier<Collection<Player>> getPlayers, ShowCardInView showCard, HideCardInView hideCard, AddCardToView addCard) {
        super(getPlayers, showCard, hideCard, addCard);
    }

    /**
     * Naively shows all cards in the game
     * @param p current active player
     */
    @Override
    public void show(Player p) {
        showAllCards();
    }
}
