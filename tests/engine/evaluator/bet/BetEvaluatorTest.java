package engine.evaluator.bet;

import engine.bet.Bet;
import engine.dealer.Card;
import engine.hand.ClassifiedHand;
import engine.hand.HandOutcome;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BetEvaluatorTest {

    @Test
    void testWinnerLoser() {
        List<Bet> bets = new ArrayList<>();
        Bet b1 = new Bet(10);
        ClassifiedHand c1 = new ClassifiedHand("Foo", 1, 10);
        b1.getHand().classifyHand(c1);
        b1.getHand().setLoser(false);
        Bet b2 = new Bet(10);
        ClassifiedHand c2 = new ClassifiedHand("Foo", 5, 10);
        b2.getHand().setLoser(true);
        b2.getHand().classifyHand(c2);
        BetEvaluator myBetEvaluator = new BetEvaluator();
        myBetEvaluator.evaluateBets(List.of(b1,b2));
        assertEquals(b1.getHand().getOutcome(), HandOutcome.WIN);
        assertEquals(b2.getHand().getOutcome(), HandOutcome.LOSS);
    }

    @Test
    void testSameRankDiffPower() {
        List<Bet> bets = new ArrayList<>();
        Bet b1 = new Bet(10);
        ClassifiedHand c1 = new ClassifiedHand("Foo", 1, 10);
        b1.getHand().classifyHand(c1);
        b1.getHand().setLoser(false);
        Bet b2 = new Bet(10);
        ClassifiedHand c2 = new ClassifiedHand("Foo", 1, 6);
        b2.getHand().setLoser(false);
        b2.getHand().classifyHand(c2);
        BetEvaluator myBetEvaluator = new BetEvaluator();
        myBetEvaluator.evaluateBets(List.of(b1,b2));
        assertEquals(HandOutcome.WIN, b1.getHand().getOutcome());
        assertEquals( HandOutcome.LOSS, b2.getHand().getOutcome());
    }

    @Test
    void testSameRankSamePower() {
        List<Bet> bets = new ArrayList<>();
        Bet b1 = new Bet(10);
        ClassifiedHand c1 = new ClassifiedHand("Foo", 1, 6);
        b1.getHand().classifyHand(c1);
        b1.getHand().setLoser(false);
        Bet b2 = new Bet(10);
        ClassifiedHand c2 = new ClassifiedHand("Foo", 1, 6);
        b2.getHand().setLoser(false);
        b2.getHand().classifyHand(c2);
        BetEvaluator myBetEvaluator = new BetEvaluator();
        myBetEvaluator.evaluateBets(List.of(b1,b2));
        assertEquals(HandOutcome.TIE, b1.getHand().getOutcome());
        assertEquals(HandOutcome.TIE, b2.getHand().getOutcome());
    }

    @Test
    void testMultipleHandsOneWinner() {
        Bet b1 = new Bet(10);
        Bet b2 = new Bet(10);
        Bet b3 = new Bet(10);
        ClassifiedHand c1 = new ClassifiedHand("Foo", 1, 6);
        ClassifiedHand c2 = new ClassifiedHand("Foo", 2, 6);
        ClassifiedHand c3 = new ClassifiedHand("Foo", 3, 6);
        b1.getHand().classifyHand(c1);
        b2.getHand().classifyHand(c2);
        b3.getHand().classifyHand(c3);

        BetEvaluator myBetEvaluator = new BetEvaluator();
        myBetEvaluator.evaluateBets(List.of(b1,b2, b3));

        assertEquals(HandOutcome.WIN, b1.getHand().getOutcome());
        assertEquals(HandOutcome.LOSS, b2.getHand().getOutcome());
        assertEquals(HandOutcome.LOSS, b3.getHand().getOutcome());
    }

    @Test
    void testMultipleHandsMultipleWinners() {
        Bet b1 = new Bet(10);
        Bet b2 = new Bet(10);
        Bet b3 = new Bet(10);
        ClassifiedHand c1 = new ClassifiedHand("Foo", 1, 6);
        ClassifiedHand c2 = new ClassifiedHand("Foo", 1, 6);
        ClassifiedHand c3 = new ClassifiedHand("Foo", 3, 6);
        b1.getHand().classifyHand(c1);
        b2.getHand().classifyHand(c2);
        b3.getHand().classifyHand(c3);

        BetEvaluator myBetEvaluator = new BetEvaluator();
        myBetEvaluator.evaluateBets(List.of(b1,b2, b3));

        assertEquals(HandOutcome.TIE, b1.getHand().getOutcome());
        assertEquals(HandOutcome.TIE, b2.getHand().getOutcome());
        assertEquals(HandOutcome.LOSS, b3.getHand().getOutcome());
    }

    @Test
    void testMultipleHandsAllWinners() {
        Bet b1 = new Bet(10);
        Bet b2 = new Bet(10);
        Bet b3 = new Bet(10);
        Bet b4 = new Bet(10);
        ClassifiedHand c1 = new ClassifiedHand("Foo", 1, 6);
        b1.getHand().classifyHand(c1);
        b2.getHand().classifyHand(c1);
        b3.getHand().classifyHand(c1);
        b4.getHand().classifyHand(c1);

        BetEvaluator myBetEvaluator = new BetEvaluator();
        myBetEvaluator.evaluateBets(List.of(b1,b2, b3, b4));

        assertEquals(HandOutcome.TIE, b1.getHand().getOutcome());
        assertEquals(HandOutcome.TIE, b2.getHand().getOutcome());
        assertEquals(HandOutcome.TIE, b3.getHand().getOutcome());
        assertEquals(HandOutcome.TIE, b4.getHand().getOutcome());
    }
}