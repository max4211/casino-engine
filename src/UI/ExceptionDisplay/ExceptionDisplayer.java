package UI.ExceptionDisplay;

import UI.Interfaces.LanguageResponder;
import UI.Interfaces.StylizedNode;
import UI.Utilities.LanguageBundle;
import UI.Icons.Icon;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ExceptionDisplayer implements LanguageResponder {

    private Alert myDisplay;
    private LanguageBundle myLanguageBundle;

    private static final String ERROR_CONFIRMATION_KEY = "ErrorConfirmation";
    private static final String PATH_TO_ICONS = "iconImages/exceptionIcons/";
    private static final String PATH_TO_STYLESHEETS = "styleSheets/exceptions/";
    private static final String CSS_EXTENSION = ".css";

    public ExceptionDisplayer(String iconName, String cssFile, LanguageBundle languageBundle) {
        this.myDisplay = new Alert(Alert.AlertType.WARNING);
        StylizedNode.setStyleID(this.myDisplay.getDialogPane(), this.getClass());
        this.myLanguageBundle = languageBundle;
        setImage(iconName);
        setStyle(cssFile);
    }

    public void displayException(Exception ex) {
        this.myDisplay.setHeaderText(ex.getMessage());
        myDisplay.showAndWait();
    }

    private void setImage(String iconName) {
        String iconPath = formatIconFilePath(iconName);
        Icon newIcon = new Icon(iconPath);
        myDisplay.getDialogPane().setGraphic(newIcon.getView());
    }

    public void setStyle(String newStyleSheet) {
        String cssFile = formatCSSFilePath(newStyleSheet);
        myDisplay.getDialogPane().getStylesheets().add(cssFile);
    }

    public void updateLanguage() {
        String newButtonMesage = myLanguageBundle.getBundle().getString(ERROR_CONFIRMATION_KEY);
        ButtonType newButtonType = new ButtonType(newButtonMesage);
        myDisplay.getButtonTypes().setAll(newButtonType);
    }

    private String formatCSSFilePath(String fileName) {
        return PATH_TO_STYLESHEETS.concat(fileName).concat(CSS_EXTENSION);
    }

    private String formatIconFilePath(String fileName) {
        return PATH_TO_ICONS.concat(fileName);
    }
}
