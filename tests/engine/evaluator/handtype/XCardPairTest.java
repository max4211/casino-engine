package engine.evaluator.handtype;

import engine.dealer.Card;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class XCardPairTest {

    @Test
    void testPair() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card("hearts", 1),
                new Card("hearts", 1),
                new Card("hearts", 1),
                new Card("hearts", 1)));
        List<Double> params = new ArrayList<>(List.of(4.));
        Hand hand = new XCardPair(cards, params);
        boolean expected = true;
        boolean result = hand.evaluate(4);
        assertEquals(expected, result);
    }

    @Test
    void testNoPair() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card("hearts", 1),
                new Card("hearts", 1),
                new Card("hearts", 3),
                new Card("hearts", 2)));
        List<Double> params = new ArrayList<>(List.of(4.));
        Hand hand = new XCardPair(cards, params);
        boolean expected = false;
        boolean result = hand.evaluate(4);
        assertEquals(expected, result);
    }
}