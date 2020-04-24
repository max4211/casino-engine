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
                new Card("hearts", 4),
                new Card("spades", 8),
                new Card("diamonds", 12),
                new Card("hearts", -1),
                new Card("hearts", 3),
                new Card("spades", 6),
                new Card("clubs", 9)
        ));
        int cardsInHand = 5;
        Combinations combinations = new Combinations(cards, cardsInHand);
        int expected = 21;
        assertEquals(expected, combinations.size());
    }

}