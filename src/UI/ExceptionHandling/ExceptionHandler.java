package UI.ExceptionHandling;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class ExceptionHandler {


    private static final String EMPTY_MESSAGE = "";
    //TODO: resourcebundle
    private static final String BUTTON_MESSAGE = "Got it!";

    public static void displayException(String iconImage, String cssFile, Exception ex) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        System.out.println(cssFile);
        alert.getDialogPane().getStylesheets().add(cssFile);
        alert.setContentText(ex.getMessage());
        alert.setHeaderText(EMPTY_MESSAGE);
        alert.getButtonTypes().setAll(new ButtonType(BUTTON_MESSAGE));
        System.out.println(iconImage);
        alert.getDialogPane().setGraphic(new ImageView(new Image(iconImage)));
        alert.showAndWait();
    }

    public static void displayException(Exception ex) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(ex.getMessage());
        alert.setHeaderText(ex.getClass().toString());
        alert.showAndWait();
    }

    private static Background createBackground(String color) {
        return new Background(new BackgroundFill(Color.web(color), CornerRadii.EMPTY, Insets.EMPTY));
    }
}
