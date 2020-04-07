package GameView;

import engine.hand.Hand;
import javafx.scene.layout.HBox;

import java.util.Formatter;
import java.util.List;

public class HandView {

    private HBox myHand;
    private Formatter myFormatter = new Formatter();

    private List<CardView> myCards;

    public HandView(Hand hand) {
        myHand = new HBox();


    }
}
