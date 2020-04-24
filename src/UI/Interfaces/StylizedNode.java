package UI.Interfaces;

import javafx.scene.Node;

public interface StylizedNode {

    public static void setStyleID(Node node, Class nodeClass) {
        System.out.println(nodeClass.getSimpleName());
        node.setId(nodeClass.getSimpleName());
    }

    Node getView();

}
