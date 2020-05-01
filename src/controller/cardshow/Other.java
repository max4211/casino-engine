package controller.cardshow;

import engine.player.Player;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * New (strange) cardshow policy, allows for all cards except your own to be shown within the game
 */
public class Other extends CardShow {

    public Other(Supplier<Collection<Player>> getPlayers, ShowCardInView showCard, HideCardInView hideCard, AddCardToView addCard) {
        super(getPlayers, showCard, hideCard, addCard);
    }

    /**
     * Shows all cards in the game, then hides your specific cards
     * @param p the current active player in the game
     */
    @Override
    public void show(Player p) {
        showAllCards();
        hideMyCards(p);
    }
}
