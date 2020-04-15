package UI.LobbyView;

import UI.Interfaces.NodeViewInterface;
import javafx.scene.layout.FlowPane;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LobbyView implements NodeViewInterface {

    FlowPane myFlowPane;

    public LobbyView() {
        myFlowPane = new FlowPane();
        List<String> myXMLs = new ArrayList<>();
        myXMLs.add("game");
        XMLButton myButton = new XMLButton(myXMLs, c -> createGame(c));
        myFlowPane.getChildren().add(myButton.getView());
    }

    @Override
    public FlowPane getView() {
        return myFlowPane;
    }

    private void createGame(List<File> myXMLFiles) {
    }
 }
