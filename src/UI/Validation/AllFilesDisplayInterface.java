package UI.Validation;

import UI.Interfaces.GameCaller;

public interface AllFilesDisplayInterface {

    public void updateStatus(XMLFile type, FileStatus ready);

    public void enableGameButton(GameCaller initializer);

}
