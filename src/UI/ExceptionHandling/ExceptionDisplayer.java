package UI.ExceptionHandling;

import UI.Interfaces.LanguageResponder;
import UI.Interfaces.StylizedNode;
import UI.LanguageBundle;
import UI.LobbyView.Icon;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ExceptionDisplayer implements LanguageResponder {

    private Alert myDisplay;
    private static final String ERROR_CONFIRMATION_KEY = "ErrorConfirmation";
    private LanguageBundle myLanguageBundle;

    private static final String PATH_TO_STYLESHEETS = "styleSheets/exceptions/";
    private static final String CSS_EXTENSION = ".css";

    private static final String PATH_TO_ICONS = "iconImages/exceptionIcons/";



    public ExceptionDisplayer(String icon, String cssFile, LanguageBundle languageBundle) {
        myDisplay = new Alert(Alert.AlertType.WARNING);
        StylizedNode.setStyleID(myDisplay.getDialogPane(), this.getClass());
        setStyle(icon, cssFile);
        myLanguageBundle = languageBundle;
    }

    public void displayException(Exception ex) {
        myDisplay.setHeaderText(ex.getMessage());
        myDisplay.showAndWait();
    }

    public void setStyle(String icon, String newStyleSheet) {
        myDisplay.getDialogPane().getStylesheets().add(PATH_TO_STYLESHEETS.concat(newStyleSheet).concat(CSS_EXTENSION));
        Icon newIcon = new Icon(PATH_TO_ICONS.concat(icon));
        myDisplay.getDialogPane().setGraphic(newIcon.getView());
    }

    public void updateLanguage() {
        myDisplay.getButtonTypes().setAll(new ButtonType(myLanguageBundle.getBundle().getString(ERROR_CONFIRMATION_KEY)));
    }
}
