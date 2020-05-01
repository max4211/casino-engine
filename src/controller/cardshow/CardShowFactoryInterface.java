package controller.cardshow;

import engine.player.Player;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

/**
 * Implemented by CardShowFactory
 * Single method to create CardShow objects
 */
public interface CardShowFactoryInterface {

    /**
     * Called to return single card show policy
     * @param show is the type of cardshow policy
     * @return the abstract card show object
     */
    CardShow create(String show, Supplier<Collection<Player>> getPlayers, ShowCardInView showCard, HideCardInView hideCard, AddCardToView addCard);
}
