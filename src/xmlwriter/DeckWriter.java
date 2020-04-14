package xmlwriter;

import java.util.List;

public class DeckWriter {

    private static final String FILENAME = "test.xml";
    private static final List<String> SUITS = List.of("Hearts", "Spades", "Clubs", "Diamonds");
    private static final int MAX_VALUE = 11;

    public static void main (String[] args) {
        writeDeck();
    }

    private static void writeDeck() {
        for (String suit: SUITS) {
            for (int i = 1; i <= MAX_VALUE; i ++) {
                System.out.printf("<Card>\n" +
                        "\t<Value>%d</Value>\n" +
                        "\t<Suit>%s</Suit>\n" +
                        "</Card>\n", i, suit);
            }
        }
    }
}
