package UI.Selectors;

import java.util.ResourceBundle;

public interface LanguageBundleInterface {

    /**
     * Set language internally bundle
     * @param language
     */
    void setLanguage(String language);

    /**
     * Resource bundle fetched, called when you want to get info from a language
     * @return
     */
    ResourceBundle getBundle();
}
