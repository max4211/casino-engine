package UI.ExceptionHandling;

import UI.LanguageBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ExceptionDisplayer {

    private Alert myDisplay = new Alert(Alert.AlertType.WARNING);
    private static final String ERROR_CONFIRMATION_KEY = "ErrorConfirmation";
    private LanguageBundle myLanguageBundle;

    public ExceptionDisplayer(String icon, String cssFile, LanguageBundle languageBundle) {
        setStyleSheet(icon, cssFile);
        myLanguageBundle = languageBundle;
    }

    public void displayException(Exception ex) {
        myDisplay.setHeaderText(ex.getMessage());
        myDisplay.showAndWait();
    }

    public void setStyleSheet(String icon, String newStyleSheet) {
        myDisplay.getDialogPane().getStylesheets().add(newStyleSheet);
        System.out.println(icon);
        Image newIcon = new Image(icon);
        ImageView newGraphic = new ImageView(newIcon);
        myDisplay.getDialogPane().setGraphic(newGraphic);
    }

    private void updateLanguage() {
        myDisplay.getButtonTypes().setAll(new ButtonType(myLanguageBundle.getBundle().getString(ERROR_CONFIRMATION_KEY)));
    }
}
