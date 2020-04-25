package engine.evaluator.handtype;

import engine.dealer.Card;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StraightTest {

    @Test
    void testStraight() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card("hearts", 1),
                new Card("hearts", 2),
                new Card("hearts", 3)));
        List<Double> params = new ArrayList<>(List.of(1.));
        Hand hand = new Straight(cards, params);
        boolean expected = true;
        boolean result = hand.evaluate(3);
        assertEquals(expected, result);
    }

    @Test
    void testCustomStraight() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card("hearts", 1),
                new Card("hearts", 5),
                new Card("hearts", 3)));
        List<Double> params = new ArrayList<>(List.of(2.));
        Hand hand = new Straight(cards, params);
        boolean expected = true;
        boolean result = hand.evaluate(3);
        assertEquals(expected, result);
    }

    @Test
    void testNotStraight() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card("hearts", 1),
                new Card("hearts", 2),
                new Card("hearts", 5)));
        List<Double> params = new ArrayList<>(List.of(1.));
        Hand hand = new Straight(cards, params);
        boolean expected = false;
        boolean result = hand.evaluate(3);
        assertEquals(expected, result);
    }
}