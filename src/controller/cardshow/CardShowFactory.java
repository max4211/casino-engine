package controller.cardshow;

import actions.individual.IndividualAction;
import engine.player.Player;
import exceptions.ReflectionException;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class CardShowFactory implements CardShowFactoryInterface{

    private static final String CARDSHOW_PATH = "controller.cardshow";

    @Override
    public CardShow create(String show, Supplier<Collection<Player>> getPlayers, ShowCardInView showCard, HideCardInView hideCard, AddCardToView addCard) {
        try {
            Class clazz = Class.forName(createCardShowPath(show));
            Constructor ctor = clazz.getConstructor(Supplier.class, ShowCardInView.class, HideCardInView.class, AddCardToView.class);
            return (CardShow) ctor.newInstance(getPlayers, showCard, hideCard, addCard);
        } catch (Exception e) {
            throw new ReflectionException(e);
        }
    }

    private String createCardShowPath(String show) {
        return String.format("%s.%s", CARDSHOW_PATH, show);
    }
}
