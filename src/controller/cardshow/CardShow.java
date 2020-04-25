package controller.cardshow;

import Utility.Generator;
import engine.bet.Bet;
import engine.dealer.Card;
import engine.player.Player;

import java.util.Collection;
import java.util.function.Supplier;

public abstract class CardShow implements CardShowInterface {

    private Supplier<Collection<Player>> myGetPlayers;
    private ShowCardInView myShowCard;
    private HideCardInView myHideCard;
    private AddCardToView myAddCard;

    public CardShow(Supplier<Collection<Player>> getPlayers, ShowCardInView showCard, HideCardInView hideCard, AddCardToView addCard) {
        this.myGetPlayers = getPlayers;
        this.myShowCard = showCard;
        this.myHideCard = hideCard;
        this.myAddCard = addCard;
    }

    @Override
    public void showAllCards() {
        for (Player p: this.myGetPlayers.get()) {
            for (Bet b: p.getActiveBets()) {
                if (b.isGameActive()) {
                    for (Card c: b.getHand().getCards())
                        this.myShowCard.showCard(p.getID(), b.getID(), c.getID());
                }
            }
        }
    }

    protected void hideMyCards(Player p) {
        for (Bet b: p.getActiveBets()) {
            for (Card c: b.getHand().getCards())
                this.myHideCard.hideCard(p.getID(), b.getID(), c.getID());
        }
    }

    protected void showActiveCards(Player p) {
        hideCards(p);
        for (Bet b: p.getActiveBets()) {
            for (Card c: b.getHand().getCards()) {
                this.myShowCard.showCard(p.getID(), b.getID(), c.getID());
            }
        }
    }

    protected void hideCards(Player p) {
        for (Player player: this.myGetPlayers.get()) {
            if (!(player.getID() == p.getID())) {
                for (Bet b: player.getActiveBets()) {
                    for (Card c: b.getHand().getCards()) {
                        this.myAddCard.addCardIfAbsent(Generator.createCardTriplet(c), player.getID(), b.getID());
                        this.myHideCard.hideCard(player.getID(), b.getID(), c.getID());
                    }
                }
            }
        }
    }
}
