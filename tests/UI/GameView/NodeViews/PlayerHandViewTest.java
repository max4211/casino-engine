package UI.GameView.NodeViews;

import UI.GameView.CardView;
import UI.GameView.HandView;
import Utility.CardTriplet;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlayerHandViewTest {

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

        assertThrows(IndexOutOfBoundsException.class, () -> cardView.getChildren().get(0));
        testHandView.showCard(1);

        Label valueLabel = (Label)cardView.getChildren().get(0);
        String valueText = valueLabel.getText();

        Label suitLabel = (Label)cardView.getChildren().get(1);
        String suitText = suitLabel.getText();
        assertEquals(valueText, "11.0");
        assertEquals(suitText, "hearts");
    }
}