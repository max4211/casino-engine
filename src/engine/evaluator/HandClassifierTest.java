package engine.evaluator;

import engine.dealer.Card;
import engine.hand.Hand;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for HandClassifier class
 *
 * @author Max Smith
 */
class HandClassifierTest {

    @Test
    void testSumHandEvaluations() {
        List<String> winners = List.of("Blackjack", "SumUnder22");
        List<String> losers = List.of("SumOver21");
        Hand bjTest = new Hand(List.of(new Card("Heart",  11), new Card("Diamond", 10)));
        Hand h = new Hand(List.of(new Card("Heart", 11), new Card("Heart", 10)));
    }
}