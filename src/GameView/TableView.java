package GameView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TableView implements ViewInterface {

    private static final String FOLDER = "tableImages/";
    private ImageView myTable;

    public TableView(String fileName) {
        myTable = new ImageView();
        Image tableImage = new Image(FOLDER + fileName);
        myTable.setImage(tableImage);
    }

    @Override
    public ImageView getView() {
        return myTable;
    }
}
