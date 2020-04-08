package GameView;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.InputStream;

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
