package controller.cardshow;

import engine.player.Player;

import java.util.Collection;
import java.util.function.Supplier;

public class All extends CardShow {
    public All(Supplier<Collection<Player>> getPlayers, ShowCardInView showCard, HideCardInView hideCard, AddCardToView addCard) {
        super(getPlayers, showCard, hideCard, addCard);
    }

    @Override
    public void show(Player p) {
        showAllCards();
    }
}
