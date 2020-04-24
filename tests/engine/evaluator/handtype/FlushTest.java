package engine.evaluator.handtype;

import engine.dealer.Card;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlushTest {

    @Test
    void testFlush() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card("hearts", 10),
                new Card("hearts", 6),
                new Card("hearts", 5)));
        List<Double> params = new ArrayList<>();
        Hand hand = new Flush(cards, params);
        boolean expected = true;
        boolean result = hand.evaluate(3);
        assertEquals(expected, result);
    }

    @Test
    void testNotFlush() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card("hearts", 10),
                new Card("spades", 6),
                new Card("hearts", 5)));
        List<Double> params = new ArrayList<>();
        Hand hand = new Flush(cards, params);
        boolean expected = false;
        boolean result = hand.evaluate(3);
        assertEquals(expected, result);
    }
}