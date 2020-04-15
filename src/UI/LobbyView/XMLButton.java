package UI.LobbyView;

import UI.Interfaces.NodeViewInterface;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class XMLButton implements NodeViewInterface {

    private static final String ACCEPTED_EXTENSION = ".xml";
    private static final String PATH_TO_XML = "./././data/";
    private static final String BUNDLE_NAME = "English";
    private static final ResourceBundle myResources = ResourceBundle.getBundle(BUNDLE_NAME);
    private static final String XML_INPUT_KEY = "XMLChooser";
    private Button myButton;

    public XMLButton(List<String> allXMLs, Consumer<List<File>> myGameGenerator) {
        myButton = new Button();
        myButton.setOnAction(e -> {
            List<File> returnedXML = new ArrayList<>();
            for (String xmlType : allXMLs) {
                String chooserTitle = myResources.getString(XML_INPUT_KEY) + returnedXML;
                File fileChosen = makeChooser(chooserTitle, xmlType).showOpenDialog(new Stage());
                returnedXML.add(fileChosen);
            }
            myGameGenerator.accept(returnedXML);
        });
    }

    private static FileChooser makeChooser(String stageTitle, String xmlPackage) {
        FileChooser result = new FileChooser();
        result.setTitle(stageTitle);
        result.setInitialFileName(PATH_TO_XML + xmlPackage);
        result.setSelectedExtensionFilter(new FileChooser.ExtensionFilter(ACCEPTED_EXTENSION));
        return result;
    }

    @Override
    public Button getView() {
        return myButton;
    }
}
