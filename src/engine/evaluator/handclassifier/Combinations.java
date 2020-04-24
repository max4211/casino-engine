package engine.evaluator.handclassifier;

import engine.dealer.Card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * href:https://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
 * @author Max Smith
 * @author Devesh Agrawal
 */
public class Combinations implements Iterator {

    private static final int ZERO = 0;

    private final int myCardsInHand;
    private final List<Card> myCards;
    private final List<List<Card>> myCombinations;

    private int myIndex = 0;

    public Combinations(List<Card> cards, int cardsInHand) {
        this.myCards = cards;
        this.myCardsInHand = Math.min(cardsInHand, cards.size());
        this.myCombinations = new ArrayList<>();
        List<Card> data = new ArrayList<>(this.myCardsInHand);
        generateCombinations(data, 0, cards.size(), ZERO);
    }

    private void generateCombinations(List<Card> data, int start, int end, int index) {
        List<Card> list = new ArrayList<>(data);
        if (index == this.myCardsInHand) {
            this.myCombinations.add(data);
            printCombo(data);
            return;
        }

        for (int i = start; i < end && end - i + 1 >= this.myCardsInHand - index; i++) {
            list.add(index, this.myCards.get(i));
            generateCombinations(list, i + 1, end, index + 1);
        }
    }

    private void printCombo(List<Card> data) {
        StringBuilder sb = new StringBuilder();
        for (Card c: data) {
            sb.append(c.toString());
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }

    @Override
    public boolean hasNext() {
        return myIndex < myCombinations.size();
    }

    @Override
    public List<Card> next() {
        List<Card> list = this.myCombinations.get(this.myIndex);
        this.myIndex ++;
        return list;
    }

    public int size() {
        return this.myCombinations.size();
    }

}
