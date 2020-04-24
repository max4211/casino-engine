package UI;

import UI.Interfaces.LanguageBundleInterface;

import java.util.ResourceBundle;

public class LanguageBundle implements LanguageBundleInterface {

    private static final String PATH_TO_LANGUAGE = "languages/";
    private String myLanguage;
    private ResourceBundle myResources;

    public LanguageBundle(String language) {
        this.myLanguage = language;
        assignBundle();
    }

    private void assignBundle() {
        this.myResources = ResourceBundle.getBundle(PATH_TO_LANGUAGE.concat(this.myLanguage));
    }

    @Override
    public void setLanguage(String language) {
        this.myLanguage = language;
        assignBundle();
    }

    @Override
    public ResourceBundle getBundle() {
        return this.myResources;
    }

}
