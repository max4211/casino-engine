package engine.evaluator.handtype;

import Utility.handbundle.HandBundle;
import engine.dealer.Card;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HandFactoryTest {

    @Test
    void createHand() {
        HandFactory factory = new HandFactory();
        HandBundle b = new HandBundle("SumOverX", "21", "1.5", "Bust");
        List<Card> cards = new ArrayList<Card>(List.of(
                new Card("Hearts", 3)
        ));
        Hand hand = factory.createHand(b, cards);
        assertEquals(hand.getClass(), SumOverX.class);
        assertEquals(false, hand.evaluate(3));
    }
}