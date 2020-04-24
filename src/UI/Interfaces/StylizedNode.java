package UI.Interfaces;

import javafx.scene.Node;

public interface StylizedNode {

    public static void setStyleID(Node node, Class nodeClass) {
        node.setId(nodeClass.getSimpleName());
    }

    Node getView();

}
