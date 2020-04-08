package ooga;

import data.xmlreader.Pair;
import data.xmlreader.XMLReader;
import engine.dealer.Dealer;
import engine.dealer.Deck;
import engine.player.Player;
import engine.player.PlayerList;
import engine.table.Table;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GameConstructor {

    private static final String testFile = "src/data/game/blackjackGame.xml";

    private static List<Player> createPlayerList(Map<String, Double> playerMap) {
        PlayerList myPlayers = new PlayerList(playerMap);
        return myPlayers.getPlayers();
    }

    // TODO - refactor game code into non static objects, currently running through main must be static
    public static void main (String[] args) throws ParserConfigurationException, SAXException, IOException {
        XMLReader myReader = new XMLReader(testFile);
        Map<String, Double> playerMap = myReader.getPlayers();
        List<Player> playerList = createPlayerList(playerMap);
        List<Pair> deckList = myReader.getDeck();
        Deck myDeck = new Deck(deckList);
        Dealer myDealer = new Dealer(myDeck);
        Table myTable = new Table(playerList, myDealer);
        System.out.println("HELLO DOPP!!\nWE CREATED THE TABLE :)");
    }
}
