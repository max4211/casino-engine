package UI.Settings;

import UI.Icons.Icon;
import UI.WebView.Browser;

/**
 * Basic help button that extends Icon, receiving the functionality of a pre-formatted ImageView.
 * Creates a simple dispaly that, when clicked, opens a web browser.
 * @author Eric Doppelt handled the basic shell, but Max Smith added the web browsing implementation.
 */
public class HelpButton extends Icon {

    /**
     * Basic constructor that creates the Icon and adds functionality to browse the application website on click.
     * @param helpIconPath is the path to the image of the icon, which should be in gameImages or lobbyIcons.
     * @param webURL is the URL to the help page.
     */
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
