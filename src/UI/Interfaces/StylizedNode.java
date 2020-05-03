package UI.Interfaces;

import javafx.scene.Node;

/**
 * Important interface that is used throughout the GameView, LobbyView, and Validation modules.
 * Creates the idea of a customized mutable node, which references a JavaFX Object via a getView() method and manipulates it by means of other method calls.
 * Assumes that every node that is  "stylized" has been called with the StylizedNode.setStyleID() method that sets the CSS ID of the node to its classname.
 * @author Eric Doppelt
 */
public interface StylizedNode {

    static void setStyleID(Node node, Class nodeClass) {
        node.setId(nodeClass.getSimpleName());
    }

    /**
     * Method which returns a JavaFX object that is assumed to visually represent the class it is held in.
     * The object is either a Dialogue, HBox, VBox, ImageView, Pane, or BorderPane for the sake of this project.
     * @return a JavaFX Node that is added to the stage and modified as the game progresses.
     */
    Node getView();

}
