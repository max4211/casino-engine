package controller.cardshow;

import actions.individual.IndividualAction;
import engine.player.Player;
import exceptions.ReflectionException;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

/**
 * Factory for cardshow policies with a single purpose, to generate the concrete objects
 * @author Max Smith
 */
public class CardShowFactory implements CardShowFactoryInterface{

    private static final String CARDSHOW_PATH = "controller.cardshow";

    /**
     * creation method, returns abstract CardShow objects for controller to use polymorphically
     * @param show is the type of cardshow policy
     * @param getPlayers is a functional interface to allow the policy to get players from the table
     * @param showCard functional interface to modify card show in view
     * @param hideCard functional interface to modify hide card in view
     * @param addCard functional interface to modify add card in view
     * @return
     */
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
