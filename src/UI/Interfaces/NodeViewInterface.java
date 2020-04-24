package UI.Interfaces;

import javafx.scene.Node;

public abstract class TaggedNodeView {

    private Node myNode;

    public TaggedNodeView() {
        setID();
    }

    public abstract Node getView();

    protected void setID() {
        myNode.setId(myNode.getClass().toString());
    }
}
