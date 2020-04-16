package UI.ExceptionHandling;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ResourceBundle;

public class ExceptionHandler {


    private static Alert myDisplay = new Alert(Alert.AlertType.WARNING);
    private static String myLangauge = "English";
    private static final ResourceBundle myResources = ResourceBundle.getBundle(myLangauge);

    //TODO: resourcebundle
    private static final String BUTTON_MESSAGE = "Got it!";

    public static void displayException(Exception ex) {
        myDisplay.setContentText(ex.getMessage());
        myDisplay.showAndWait();
    }

    public static void setStyleSheet(String icon, String newStyleSheet) {
        myDisplay.getDialogPane().getStylesheets().add(newStyleSheet);
        Image newIcon = new Image(icon);
        ImageView newGraphic = new ImageView(newIcon);
        myDisplay.getDialogPane().setGraphic(newGraphic);
    }
}
