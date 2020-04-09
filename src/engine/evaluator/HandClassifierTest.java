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
        HandClassifier hc = new HandClassifier(List.of("SumUnder22"), List.of("SumOver21"));
        Hand h = new Hand(List.of(new Card("Heart", 11), new Card("Heart", 10)));

    }
}