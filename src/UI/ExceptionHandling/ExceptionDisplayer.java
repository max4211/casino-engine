package UI.ExceptionHandling;

import UI.LanguageBundle;
import UI.LobbyView.Icon;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ExceptionDisplayer {

    private Alert myDisplay;
    private static final String ERROR_CONFIRMATION_KEY = "ErrorConfirmation";
    private LanguageBundle myLanguageBundle;

    private static final String PATH_TO_STYLESHEETS = "styleSheets/exceptions/";

    private static final String EXCEPTION_SHOWER_ID = "exception-dialogue";

    public ExceptionDisplayer(String icon, String cssFile, LanguageBundle languageBundle) {
        myDisplay = new Alert(Alert.AlertType.WARNING);
        myDisplay.getDialogPane().setId(EXCEPTION_SHOWER_ID);
        setStyle(icon, cssFile);
        myLanguageBundle = languageBundle;
    }

    public void displayException(Exception ex) {
        myDisplay.setHeaderText(ex.getMessage());
        myDisplay.showAndWait();
    }

    public void setStyle(String icon, String newStyleSheet) {
        myDisplay.getDialogPane().getStylesheets().add(PATH_TO_STYLESHEETS.concat(newStyleSheet));
        Icon newIcon = new Icon(icon);
        myDisplay.getDialogPane().setGraphic(newIcon.getView());
    }

    private void updateLanguage() {
        myDisplay.getButtonTypes().setAll(new ButtonType(myLanguageBundle.getBundle().getString(ERROR_CONFIRMATION_KEY)));
    }
}
