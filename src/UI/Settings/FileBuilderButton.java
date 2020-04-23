package UI.Settings;

import UI.FileConstruction.FileEditorConstructor;
import UI.LobbyView.Icon;

public class FileBuilderButton extends Icon {

    public FileBuilderButton(String constructorIconPath) {
        super((constructorIconPath));
        myButton.setOnAction(e -> {
            FileEditorConstructor newFileEditor = new FileEditorConstructor();
        });
    }
}
