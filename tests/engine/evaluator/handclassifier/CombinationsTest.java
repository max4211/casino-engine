package engine.evaluator.handclassifier;

import engine.dealer.Card;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CombinationsTest {

    @Test
    void testCombos() {
        List<Card> cards = new ArrayList<Card>(List.of(
                new Card("hearts", 1),
                new Card("spades", 2),
                new Card("diamonds", 3),
                new Card("hearts", 4),
                new Card("hearts", 5),
                new Card("spades", 6),
                new Card("clubs", 7)
        ));
        int cardsInHand = 5;
        Combinations combinations = new Combinations(cards, cardsInHand);
        int expected = 21;
        assertEquals(expected, combinations.size());
        while (combinations.hasNext()) {
            List<Card> list = combinations.next();
            System.out.println("SIZE: " + list.size() + " " + list);
            assertEquals(cardsInHand, list.size());
        }

    }

}