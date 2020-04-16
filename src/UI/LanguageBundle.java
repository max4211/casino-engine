package UI;

import java.util.ResourceBundle;

public class LanguageBundle {

    private String myLanguage;
    private ResourceBundle myResources;

    public LanguageBundle(String language) {
        this.myLanguage = language;
        assignBundle();
    }

    private void assignBundle() {
        this.myResources = ResourceBundle.getBundle(this.myLanguage);
    }

    public void setLanguage(String language) {
        this.myLanguage = language;
        assignBundle();
    }

    public ResourceBundle getBundle() {
        return this.myResources;
    }

}
