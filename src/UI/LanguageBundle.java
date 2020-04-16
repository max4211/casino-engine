package UI;

import UI.Selectors.LanguageBundleInterface;

import java.util.ResourceBundle;

public class LanguageBundle implements LanguageBundleInterface {

    private String myLanguage;
    private ResourceBundle myResources;

    public LanguageBundle(String language) {
        this.myLanguage = language;
        assignBundle();
    }

    private void assignBundle() {
        this.myResources = ResourceBundle.getBundle(this.myLanguage);
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
