package UI.Settings;

import UI.LobbyView.Icon;

import java.awt.*;
import java.net.URL;

public class HelpButton extends Icon {

    private static final String WEBSITE_LINK = "https://www.casino308.com/#";

    public HelpButton(String helpIconPath) {
        super(helpIconPath);
        myIcon.setOnMouseClicked(e -> {
            try {
                Desktop.getDesktop().browse(new URL(WEBSITE_LINK).toURI());
            } catch (Exception ex) {
               //FIXME: throw an error here
            }
        });

    }
}
