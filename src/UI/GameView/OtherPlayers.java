package UI.GameView;

import UI.Interfaces.LanguageResponder;
import UI.Interfaces.StylizedNode;
import UI.Utilities.Formatter;
import UI.Utilities.LanguageBundle;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class

OtherPlayers implements StylizedNode, LanguageResponder {

    private VBox myPlayers;
    private List<PlayerView>  allPlayers;

    public OtherPlayers() {
        myPlayers = new VBox();
        Formatter.formatOtherPlayers(myPlayers);
        StylizedNode.setStyleID(myPlayers, this.getClass());
        allPlayers = new ArrayList<>();
    }

    public void addPlayer(String name, int playerID, double bankroll, LanguageBundle languageBundle) {
        PlayerView addedPlayerView = new PlayerView(name, playerID, bankroll, languageBundle);
        addPlayer(addedPlayerView);
    }

    public void addPlayer(PlayerView addedPlayer) {
        myPlayers.getChildren().add(addedPlayer.getView());
        allPlayers.add(addedPlayer);
    }

    public void removePlayer(int playerID) {
        PlayerView removedPlayerView = getPlayerView(playerID);
        myPlayers.getChildren().remove(removedPlayerView.getView());
        allPlayers.remove(removedPlayerView);
    }

    public PlayerView getPlayerView(int playerID) {
        for (PlayerView tempPlayerView : allPlayers)
            if (tempPlayerView.hasSameID(playerID)) return tempPlayerView;
        return null;
    }

    public boolean hasPlayerView(int playerID) {
        return getPlayerView(playerID) != null;
    }

    public void clearBets() {
        for (PlayerView tempPlayerView : allPlayers) tempPlayerView.clearBets();
    }

    @Override
    public VBox getView() {
        return myPlayers;
    }

    @Override
    public void updateLanguage() {
        for (PlayerView tempPlayer : allPlayers) {
            tempPlayer.updateLanguage();
        }
    }
}
