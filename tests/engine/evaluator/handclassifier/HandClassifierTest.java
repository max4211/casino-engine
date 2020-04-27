package engine.evaluator.handclassifier;

import Utility.handbundle.HandBundle;
import engine.dealer.Card;
import engine.hand.PlayerHand;
import exceptions.GeneralXMLException;
import org.junit.jupiter.api.Test;
import xml.xmlreader.readers.HandReader;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandClassifierTest {

    private HandClassifier createClassifier(String file) throws GeneralXMLException {
        HandReader handReader = new HandReader(file);
        List<HandBundle> myWinningHands = handReader.getWinningHands();
        List<HandBundle> myLosingHands = handReader.getLosingHands();
        return new HandClassifier(myWinningHands, myLosingHands, handReader.getCardsInHand());
    }

    @Test
    void testBust() throws GeneralXMLException {
        final String file = "data/xml/good/HANDS_blackjack.xml";
        HandClassifier myHandClassifier = createClassifier(file);
        PlayerHand playerHand = new PlayerHand();
        List<Card> cards = new ArrayList<Card>(List.of(
                new Card("Hearts", 15),
                new Card("Hearts", 12)
        ));
        for (Card c: cards)
            playerHand.acceptCard(c);
        myHandClassifier.classifyHand(playerHand.getCards(), playerHand);
    }

    @Test
    void testBJ() throws GeneralXMLException {
        final String file = "data/xml/good/HANDS_blackjack.xml";
        HandClassifier myHandClassifier = createClassifier(file);
        PlayerHand playerHand = new PlayerHand();
        List<Card> cards = new ArrayList<Card>(List.of(
                new Card("Hearts", 15),
                new Card("Hearts", 6)
        ));
        for (Card c: cards)
            playerHand.acceptCard(c);
        myHandClassifier.classifyHand(playerHand.getCards(), playerHand);
        String result = playerHand.getClassification().getName();
        String expected = "Blackjack";
        assertEquals(result, expected);
    }

    @Test
    void testSum() throws GeneralXMLException {
        final String file = "data/xml/good/HANDS_blackjack.xml";
        HandClassifier myHandClassifier = createClassifier(file);
        PlayerHand playerHand = new PlayerHand();
        List<Card> cards = new ArrayList<Card>(List.of(
                new Card("Hearts", 15),
                new Card("Hearts", 4)
        ));
        for (Card c: cards)
            playerHand.acceptCard(c);
        myHandClassifier.classifyHand(playerHand.getCards(), playerHand);
        String result = playerHand.getClassification().getName();
        String expected = "Sum Hand";
        assertEquals(expected, result);
    }

    @Test
    void testFourOfAKind() throws GeneralXMLException {
        final String file = "data/xml/good/HANDS_poker.xml";
        HandClassifier myHandClassifier = createClassifier(file);
        PlayerHand playerHand = new PlayerHand();
        List<Card> cards = new ArrayList<Card>(List.of(
                new Card("Hearts", 15),
                new Card("spades", 4),
                new Card("diamonds", 5),
                new Card("checks", 3),
                new Card("guys", 4),
                new Card("compsci", 4),
                new Card("lambda", 4)
        ));
        for (Card c: cards)
            playerHand.acceptCard(c);
        myHandClassifier.classifyHand(playerHand.getCards(), playerHand);
        String result = playerHand.getClassification().getName();
        String expected = "4 of a Kind";
        assertEquals(expected, result);
    }
}