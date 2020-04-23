package UI.Validation;

import UI.Interfaces.GameCaller;

public interface ValidFilesDisplayInterface {

    public void updateStatus(XMLFile type, FileStatus ready);

    public void renderGameButton(GameCaller initializer);

}
