package engine.evaluator;

import engine.dealer.Card;
import engine.hand.PlayerPlayerHand;
import engine.hand.HandOutcome;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for HandClassifier class
 *
 * @author Max Smith
 */
class PlayerHandClassifierTest {

    @Test
    void testBlackJack() {
        HandClassifier hc = createHandClassifier();
        PlayerPlayerHand bjTest = new PlayerPlayerHand(List.of(new Card("Heart",  11), new Card("Diamond", 10)));
        hc.classifyHand(bjTest);
        assertEquals(bjTest.getClassification().getName(), "Blackjack");
        assertEquals(bjTest.getClassification().getPower(), 21);
    }

    @Test
    void testBust() {
        HandClassifier hc = createHandClassifier();
        PlayerPlayerHand bustTest = new PlayerPlayerHand(List.of(new Card("Heart",  11), new Card("Diamond", 10), new Card("Spade", 11)));
        hc.classifyHand(bustTest);
        assertEquals(bustTest.getOutcome(), HandOutcome.LOSS);
    }

    @Test
    void TestUnder21() {
        HandClassifier hc = createHandClassifier();
        PlayerPlayerHand under22Test = new PlayerPlayerHand(List.of(new Card("Spade", 11)));
        hc.classifyHand(under22Test);
        assertEquals(under22Test.getClassification().getName(), "SumUnder22");
        assertEquals(under22Test.getClassification().getRank(), 1);
    }

    private HandClassifier createHandClassifier() {
        List<String> winners = List.of("Blackjack", "SumUnder22");
        List<String> losers = List.of("SumOver21");
        return new HandClassifier(winners, losers);
    }
}