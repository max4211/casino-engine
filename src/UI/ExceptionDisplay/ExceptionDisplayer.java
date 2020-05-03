package UI.ExceptionDisplay;

import UI.Interfaces.LanguageResponder;
import UI.Interfaces.StylizedNode;
import UI.Utilities.LanguageBundle;
import UI.Icons.Icon;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * The purpose of this class is to display exceptions in a user-friendly way to the user of the application.
 * Exceptions are displayed in the form of a JavaFX Alert.
 * The content of the display that is preset, such as the confirmation button, can have its language changed via a LanguageBundle, implementing the LanguageResponder interface.
 * The style can be set which occurs at initialization of the game.
 * The functionality of the class is embedded in the displayException() method, which displays the message of the exception.
 * This class assumes that the information necessary to the exception displayed will be contained in its message. If not, it will print a blank warning.
 * @author Eric Doppelt
 */
public class ExceptionDisplayer implements LanguageResponder {

    private Alert myDisplay;
    private LanguageBundle myLanguageBundle;

    private static final String ERROR_CONFIRMATION_KEY = "ErrorConfirmation";
    private static final String PATH_TO_ICONS = "iconImages/exceptionIcons/";
    private static final String PATH_TO_STYLESHEETS = "styleSheets/exceptions/";
    private static final String CSS_EXTENSION = ".css";

    /**
     * General constructor to create the DialoguePane used in the Alert that is shown on calls to display exceptions. Note that the constructor itself displays nothing.
     * This assumes the icons and css files given are in the exceptionIcons and exceptions packages within Resources. If not, an error will be thrown.
     * @param iconName is the name of the warning icon shown in the Alert (this is only the name and the path is added through construction).
     * @param cssFile is the name of the css file to add to the display (this is only the name and the path is added through construction).
     * @param languageBundle is the LanguageBundle to use for the ExceptionDisplayer which is referenced on language updates.
     */
    public ExceptionDisplayer(String iconName, String cssFile, LanguageBundle languageBundle) {
        this.myDisplay = new Alert(Alert.AlertType.WARNING);
        StylizedNode.setStyleID(this.myDisplay.getDialogPane(), this.getClass());
        this.myLanguageBundle = languageBundle;
        setImage(iconName);
        setStyle(cssFile);
    }

    /**
     * This displays an exception in a user-friendly way via a JavaFX Alert. The header of the alert is the exception's message.
     * This assumes the exception will have an informative message. Empty messages create empty displays with only a Warning icon.
     * @param ex is the exception to display.
     */
    public void displayException(Exception ex) {
        this.myDisplay.setHeaderText(ex.getMessage());
        myDisplay.showAndWait();
    }

    /**
     * This is called to tell the ExceptionDisplayer to update its language. This change affects the confirmation button.
     * Note that no parameters are passed in since the ResourceBundle is updated elsewhere, and once this method is called, it is assumed that the ResourceBundle it points to has changed.
     * If the LanguageBundle has not changed despite an update call, the display will not change.
     */
    public void updateLanguage() {
        String newButtonMesage = myLanguageBundle.getBundle().getString(ERROR_CONFIRMATION_KEY);
        ButtonType newButtonType = new ButtonType(newButtonMesage);
        myDisplay.getButtonTypes().setAll(newButtonType);
    }

    /**
     * This sets the style of the DialoguePane of the Alert displayed.
     * @param newStyleSheet is the new CSS file to apply to the DialoguePane. It is assumed that this is in the exceptions CSS folder.
     */
    public void setStyle(String newStyleSheet) {
        String cssFile = formatCSSFilePath(newStyleSheet);
        myDisplay.getDialogPane().getStylesheets().add(cssFile);
    }

    private void setImage(String iconName) {
        String iconPath = formatIconFilePath(iconName);
        Icon newIcon = new Icon(iconPath);
        myDisplay.getDialogPane().setGraphic(newIcon.getView());
    }
    private String formatCSSFilePath(String fileName) {
        return PATH_TO_STYLESHEETS.concat(fileName).concat(CSS_EXTENSION);
    }

    private String formatIconFilePath(String fileName) {
        return PATH_TO_ICONS.concat(fileName);
    }
}
