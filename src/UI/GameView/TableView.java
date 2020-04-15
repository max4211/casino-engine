package UI.GameView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import UI.Interfaces.NodeViewInterface;

public class TableView implements NodeViewInterface {

    private static final String FOLDER = "tableImages/";
    private ImageView myTable;

    private static final double TABLE_HEIGHT =150;
    private static final double TABLE_WIDTH = 400;

    public TableView(String fileName) {
        myTable = new ImageView();
        Image tableImage = new Image(FOLDER + fileName);
        myTable.setImage(tableImage);
        myTable.setFitHeight(TABLE_HEIGHT);
        myTable.setFitWidth(TABLE_WIDTH);
    }

    @Override
    public ImageView getView() {
        return myTable;
    }

}
