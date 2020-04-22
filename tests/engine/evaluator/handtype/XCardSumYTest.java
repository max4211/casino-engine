package engine.evaluator.handtype;

import engine.dealer.Card;
import engine.evaluator.handtype.Hand;
import engine.evaluator.handtype.XCardSumY;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class XCardSumYTest {

    @Test
    void testXCardSumY() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card("hearts", 10),
                new Card("hearts", 11)));;
        List<Double> params = Arrays.asList(2., 21.);
        Hand hand = new XCardSumY(cards, params);
        boolean expected = true;
        boolean result = hand.evaluate();
        assertEquals(expected, result);
    }

    @Test
    void testXCardSumYFalse() {
        List<Card> cards = new ArrayList<>(List.of(
                new Card("hearts", 10),
                new Card("hearts", 12)));;
        List<Double> params = Arrays.asList(2., 21.);
        Hand hand = new XCardSumY(cards, params);
        boolean expected = false;
        boolean result = hand.evaluate();
        assertEquals(expected, result);
    }

}