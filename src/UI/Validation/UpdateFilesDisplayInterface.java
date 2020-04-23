package UI.Validation;

import Utility.CardTriplet;

@FunctionalInterface
public interface UpdateFilesDisplayInterface {

    void updateStatus(XMLFile file, FileStatus status);

}
