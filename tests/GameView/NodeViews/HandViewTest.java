package GameView.NodeViews;

import Utility.CardTriplet;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HandViewTest {

    @Test
    void getView() {
        JFXPanel jfxPanel;
        jfxPanel = new JFXPanel();

        CardTriplet testCardTriplet = new CardTriplet(11, "hearts", 1);
        CardView testCard = new CardView(testCardTriplet);
        List<CardTriplet> testCards = new ArrayList<>();
        testCards.add(testCardTriplet);
        HandView testHandView = new HandView(testCards);

        HBox handView = testHandView.getView();
        VBox cardView = (VBox)handView.getChildren().get(0);
        System.out.println(cardView.getChildren().size());

        testHandView.showCard(1);

        Label valueLabel = (Label)cardView.getChildren().get(0);
        String valueText = valueLabel.getText();
        assertEquals(valueText, "11.0");
    }
}