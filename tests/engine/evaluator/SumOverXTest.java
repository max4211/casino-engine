package engine.evaluator;

import engine.dealer.Card;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SumOverXTest {

    @Test
    void testSumOverX() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card("hearts", 10),
                new Card("hearts", 6),
                new Card("hearts", 5)));
        List<Double> params = Arrays.asList(20.);
        Hand hand = new SumOverX(cards, params);
        boolean expected = true;
        boolean result = hand.evaluate();
        assertEquals(expected, result);
    }

}