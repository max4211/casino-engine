package UI.ExceptionHandling;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ResourceBundle;

public class ExceptionHandler {

    private static Alert myDisplay = new Alert(Alert.AlertType.WARNING);
    private static String myLanguage = "English";
    private static final ResourceBundle myResources = ResourceBundle.getBundle(myLanguage);
    private static final String ERROR_CONFIRMATION_KEY = "ErrorConfirmation";

    public static void displayException(Exception ex) {
        updateLanguage();
        myDisplay.setHeaderText(ex.getMessage());
        myDisplay.showAndWait();
    }

    public static void setStyleSheet(String icon, String newStyleSheet) {
        myDisplay.getDialogPane().getStylesheets().add(newStyleSheet);
        System.out.println(icon);
        Image newIcon = new Image(icon);
        ImageView newGraphic = new ImageView(newIcon);
        myDisplay.getDialogPane().setGraphic(newGraphic);
    }

    private static void updateLanguage() {
        myDisplay.getButtonTypes().setAll(new ButtonType(myResources.getString(ERROR_CONFIRMATION_KEY)));
    }
}
