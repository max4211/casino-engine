package ooga;

import data.xmlreader.XMLReader;
import engine.table.Table;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class GameConstructor {

    private static final String testFile = "src/data/game/blackjackGame.xml";

    public static void main (String[] args) throws ParserConfigurationException, SAXException, IOException {
        XMLReader myReader = new XMLReader(testFile);
        myReader.getDeck();
        System.out.println("HELLO DOPP!!");
    }
}
