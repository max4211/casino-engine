package UI.Interfaces;

/**
 * Interface that is implemented in all classes which have text displayed in the UI that is dependent on the language setting.
 * Using a LanguageBundle, this method is called whenever the ResourceBundle the LanguageBundle wraps around is changed.
 * The method simply tells the classes that are dependent on language to update their views based on the change.
 * @author Eric Doppelt
 */
public interface LanguageResponder {

    /**
     * Method that informs the class to update their languages by means of new translations.
     * Using the LanguageBundle, implementing classes simply request the strings held in the new bundle by means of the same keys.
     * No parameters or return value are needed, since implementing classes are given access to the bundle at construction,
     */
    void updateLanguage();
}
