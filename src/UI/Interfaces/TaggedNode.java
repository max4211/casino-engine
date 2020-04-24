package UI.Interfaces;

import javafx.scene.Node;

public interface TaggedNode {

    static void setIdCSS(Node node) {
        node.setId(node.getClass().toString());
    }

    Node getView();

}
