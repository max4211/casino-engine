package UI.Utilities;

import UI.Interfaces.LanguageBundleInterface;

import java.util.ResourceBundle;

/**
 * Basic class that wraps around a ResourceBundle, allowing multiple classes to reference the same underlying ResourceBundle.
 * This allows an easy way to have all classes within a particular group be automatically synced to the same ResourceBundle.
 * @author Eric Doppelt
 */
public class LanguageBundle implements LanguageBundleInterface {

    private static final String PATH_TO_LANGUAGE = "languages/";
    private String myLanguage;
    private ResourceBundle myResources;

    /**
     * Basic constructor that creates the underlying ResourceBundle by creating a path to it and setting the myResources variable to it.
     * @param language is the initial language the program is being run in, which corresponds to a ResourceBundle of the same name.
     */
    public LanguageBundle(String language) {
        this.myLanguage = language;
        assignBundle();
    }

    /**
     * This updates the underlying ResourceBundle to be of the name given by the String parameter.
     * Assumes that the language has a ResourceBundle to support it.
     * @param language is the new language to display text in, with a ResourceBundle existing to support it already.
     */
    @Override
    public void setLanguage(String language) {
        this.myLanguage = language;
        assignBundle();
    }

    /**
     * Basic getter method to access the ResourceBundle.
     * Used in classes implementing the LanguageResponder interface to get translated text.
     * @return the ResourceBundle this class wraps around.
     */
    @Override
    public ResourceBundle getBundle() {
        return this.myResources;
    }

    private void assignBundle() {
        this.myResources = ResourceBundle.getBundle(PATH_TO_LANGUAGE.concat(this.myLanguage));
    }
}
