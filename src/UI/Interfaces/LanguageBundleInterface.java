package UI.Interfaces;

import java.util.ResourceBundle;

/**
 * Interface implemented by the LanguageBundle class, created by means of design by contract.
 * Simply specifies the methods that are used in the LanguageBundle, which wraps around a ResourceBundle.
 */
public interface LanguageBundleInterface {

    /**
     * This updates the underlying ResourceBundle to be of the name given by the String parameter.
     * Assumes that the language has a ResourceBundle to support it.
     * @param language is the new language to display text in, with a ResourceBundle existing to support it already.
     */
    void setLanguage(String language);

    /**
     * This simply returns the ResourceBundle that is held by the LanguageBundle.
     * This is used to access text after a translation occurs, since text is still accessed through the bundle.
     * @return the ResourceBundle that is held by the LanguageBundle.
     */
    ResourceBundle getBundle();
}
