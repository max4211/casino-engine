package engine.evaluator.handevaluator;

import engine.evaluator.handtype.Hand;
import engine.hand.ClassifiedHand;
import engine.hand.PlayerHand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandEvaluatorTest {

    /**
     * Results of winner test - winner is positive
     */
    @Test
    void compareDiffRank() {
        HandEvaluator handEvaluator = new HandEvaluator();
        PlayerHand h1 = new PlayerHand();
        ClassifiedHand c1 = new ClassifiedHand("Foo", 1, 2);
        h1.classifyHand(c1);
        boolean l1 = h1.isLoser();
        PlayerHand h2 = new PlayerHand();
        ClassifiedHand c2 = new ClassifiedHand("Foo", 2, 2);
        h2.classifyHand(c2);
        boolean l2 = h2.isLoser();
        int result = handEvaluator.compare(h1, h2);
        int expected = 1;
        assertEquals(false, l1);
        assertEquals(false, l2);
        assertEquals(expected, result);
    }

    @Test
    void compareSetLoser() {
        HandEvaluator handEvaluator = new HandEvaluator();
        PlayerHand h1 = new PlayerHand();
        ClassifiedHand c1 = new ClassifiedHand("Foo", 1, 2);
        h1.classifyHand(c1);
        h1.setLoser(true);
        PlayerHand h2 = new PlayerHand();
        ClassifiedHand c2 = new ClassifiedHand("Foo", 2, 2);
        h2.classifyHand(c2);
        int result = handEvaluator.compare(h1, h2);
        int expected = -1;
        assertEquals(expected, result);
    }

    @Test
    void compareSameRank() {
        HandEvaluator handEvaluator = new HandEvaluator();
        PlayerHand h1 = new PlayerHand();
        ClassifiedHand c1 = new ClassifiedHand("Foo", 2, 5);
        h1.classifyHand(c1);
        PlayerHand h2 = new PlayerHand();
        ClassifiedHand c2 = new ClassifiedHand("Foo", 2, 2);
        h2.classifyHand(c2);
        int result = handEvaluator.compare(h1, h2);
        boolean expected = true;
        assertEquals(expected, result > 0);
    }
}