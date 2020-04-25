package UI.Settings;

import UI.Icons.Icon;
import UI.WebView.Browser;

public class HelpButton extends Icon {

    public HelpButton(String helpIconPath, String webURL) {
        super(helpIconPath);
        myIcon.setOnMouseClicked(e -> {
            try {
                Browser myBrowser = new Browser(webURL);
                myBrowser.render();
            } catch (Exception ex) {
               //FIXME: throw an error here
            }
        });
    }
}
