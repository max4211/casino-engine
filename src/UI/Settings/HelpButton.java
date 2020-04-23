package UI.Settings;

import UI.LobbyView.Icon;
import UI.WebView.Browser;

public class HelpButton extends Icon {

    private static final int BROWSER_SIZE = 400;

    public HelpButton(String helpIconPath) {
        super(helpIconPath);
        myIcon.setOnMouseClicked(e -> {
            try {
                Browser myBrowser = new Browser();
                myBrowser.render();
            } catch (Exception ex) {
               //FIXME: throw an error here
            }
        });

    }
}
