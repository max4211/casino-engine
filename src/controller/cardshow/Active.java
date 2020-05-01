package controller.cardshow;

import engine.player.Player;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * Active card show policy always shows all cards in the game
 * @author Max Smith
 */
public class Active extends CardShow {

    public Active(Supplier<Collection<Player>> getPlayers, ShowCardInView showCard, HideCardInView hideCard, AddCardToView addCard) {
        super(getPlayers, showCard, hideCard, addCard);
    }

    /**
     * Shows all active cards in the game
     * @param p is the current player acting in the game
     */
    @Override
    public void show(Player p) {
        showActiveCards(p);
    }
}
