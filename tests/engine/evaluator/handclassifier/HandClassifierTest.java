package engine.evaluator.handclassifier;

import Utility.handbundle.HandBundle;
import engine.dealer.Card;
import engine.hand.PlayerHand;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import xml.xmlreader.readers.HandReader;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HandClassifierTest {

    private static final String HAND_FILE = "data/xml/hands/testgeneralhands.xml";

    private HandClassifier createClassifier() throws ParserConfigurationException, SAXException, IOException {
        HandReader handReader = new HandReader(HAND_FILE);
        List<HandBundle> myWinningHands = handReader.getWinningHands();
        List<HandBundle> myLosingHands = handReader.getLosingHands();
        return new HandClassifier(myWinningHands, myLosingHands, Integer.MAX_VALUE);
    }

    @Test
    void testBust() throws IOException, SAXException, ParserConfigurationException {
        HandClassifier myHandClassifier = createClassifier();
        PlayerHand playerHand = new PlayerHand();
        List<Card> cards = new ArrayList<Card>(List.of(
                new Card("Hearts", 15),
                new Card("Hearts", 12)
        ));
        for (Card c: cards)
            playerHand.acceptCard(c);
        myHandClassifier.classifyHand(playerHand.getCards(), playerHand);
        String result = playerHand.getClassification().getName();
        String expected = "Bust";
        assertEquals(result, expected);
    }

    @Test
    void testBJ() throws IOException, SAXException, ParserConfigurationException {
        HandClassifier myHandClassifier = createClassifier();
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
    void testSum() throws IOException, SAXException, ParserConfigurationException {
        HandClassifier myHandClassifier = createClassifier();
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
}