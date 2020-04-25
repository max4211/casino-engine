package controller.cardshow;

import Utility.Generator;
import engine.bet.Bet;
import engine.dealer.Card;
import engine.player.Player;

import java.util.Collection;
import java.util.List;
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
                    cardListOperator(
                            (pID, bID, cID) -> this.myShowCard.showCard(pID, bID, cID),
                            b.getHand().getCards(), p.getID(), b.getID());
                }
            }
        }
    }

    private void cardListOperator(CardLoopFunction function, List<Card> cards, int pID, int bID) {
        for (Card c: cards)
            function.operate(pID, bID, c.getID());
    }

    protected void hideMyCards(Player p) {
        for (Bet b: p.getActiveBets()) {
            cardListOperator(
                    (pID, bID, cID) -> this.myHideCard.hideCard(pID, bID, cID),
                    b.getHand().getCards(), p.getID(), b.getID());
        }
    }

    protected void showActiveCards(Player p) {
        hideCards(p);
        for (Bet b: p.getActiveBets()) {
            cardListOperator(
                    (pID, bID, cID) -> this.myShowCard.showCard(pID, bID, cID),
                    b.getHand().getCards(), p.getID(), b.getID());
        }
    }

    private void addAbsentCards(List<Card> cards, int bID, int pID) {
        for (Card c: cards)
            this.myAddCard.addCardIfAbsent(Generator.createCardTriplet(c), pID, bID);
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
