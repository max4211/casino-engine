package engine.evaluator.handclassifier;

import engine.dealer.Card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * href:https://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
 * @author Max Smith
 * @author Devesh Agrawal
 *
 * Service to HandClassifer, generates combinations of all possible cards given a specifci hand size
 * Implements Iterator to iterate through all possible combinations (hide List of List implementation)
 */
public class Combinations implements Iterator<List<Card>> {

    private static final int ZERO = 0;

    private final int myCardsInHand;
    private final List<Card> myCards;
    private final List<List<Card>> myCombinations;

    private int myIndex = 0;

    /**
     * Construct combinatinos object
     * @param cards are the cards within the players hand
     * @param cardsInHand is the max size of the combination
     */
    public Combinations(List<Card> cards, int cardsInHand) {
        this.myCards = cards;
        this.myCardsInHand = Math.min(cardsInHand, cards.size());
        this.myCombinations = new ArrayList<>();
        List<Card> data = new ArrayList<>(this.myCardsInHand);
        generateCombinations(data, 0, cards.size(), ZERO);
    }

    private void generateCombinations(List<Card> data, int start, int end, int index) {
        List<Card> list = new ArrayList<>(data);
        if (index == this.myCardsInHand || list.size() == this.myCardsInHand) {
            this.myCombinations.add(list);
//            printCombo(data);
            return;
        }

        for (int i = start; (i < end) && (end - i + 1 >= this.myCardsInHand - index); i++) {
            list.add(index, this.myCards.get(i));
            generateCombinations(list, i + 1, end, index + 1);
            list.remove(index);
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

    /**
     * Verify that the index is less than the size of all combinations
     * @return that there are more combinations to iterate over
     */
    @Override
    public boolean hasNext() {
        return myIndex < myCombinations.size();
    }

    /**
     * Adjust the index for scanning, and return the appropriate list
     * @return list of cards next in combination
     */
    @Override
    public List<Card> next() {
        List<Card> list = this.myCombinations.get(this.myIndex);
        this.myIndex ++;
        return list;
    }

    /**
     * Used for debugging combinations, verify the number of combinations (x choose y)
     * @return the total number of combiations
     */
    public int size() {
        return this.myCombinations.size();
    }

}
