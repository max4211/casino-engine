package UI.LobbyView;

import UI.Interfaces.NodeViewInterface;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.*;
import java.util.function.Consumer;

public class XMLButton implements NodeViewInterface {

    private static final String ACCEPTED_XML_DESCRIPTION = "All XMLs";
    private static final String ACCEPTED_XML_EXTENSION = "*.xml";

    private static final String DIRECTORY_TO_XML = System.getProperty("user.dir") + "/src/data/";
    private static final String BUNDLE_NAME = "English";
    private static final ResourceBundle myResources = ResourceBundle.getBundle(BUNDLE_NAME);
    private static final String XML_INPUT_KEY = "XMLChooser";
    private static final String XML_BUTTON_KEY = "XMLButton";
    private Button myButton;

    public XMLButton(List<String> allXMLs, Consumer<Map<String, File>> myGameGenerator) {
        myButton = new Button();
        myButton.setText(myResources.getString(XML_BUTTON_KEY));
        myButton.setOnAction(e -> {
            Map<String, File> returnedXML = new HashMap<>();
            for (String xmlType : allXMLs) {
                File fileChosen = makeChooser(xmlType).showOpenDialog(new Stage());
                returnedXML.put(xmlType, fileChosen);
            }
            myGameGenerator.accept(returnedXML);
        });
    }

    private static FileChooser makeChooser(String xmlPackage) {
        System.out.println(xmlPackage);
        FileChooser result = new FileChooser();
        result.setInitialDirectory(new File(DIRECTORY_TO_XML + xmlPackage));
        result.setSelectedExtensionFilter(new FileChooser.ExtensionFilter(ACCEPTED_XML_DESCRIPTION, ACCEPTED_XML_EXTENSION));
        return result;
    }

    @Override
    public Button getView() {
        return myButton;
    }
}
