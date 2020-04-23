package ooga;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class MultipleFileChooser extends Application {

    private List<File> fileList;
    private static final String ACCEPTED_XML_DESCRIPTION = "All XMLs";
    private static final String ACCEPTED_XML_EXTENSION = "*.xml";
    private static final String DIRECTORY_TO_XML = System.getProperty("user.dir") + "/data/xml/";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Multiple File Chooser");

        final VBox labelFile = new VBox();

        Button btn = new Button();
        btn.setText("Open FileChooser to select multiple files");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                // Set extension filter
                FileChooser fileChooser = new FileChooser();
                fileChooser.setInitialDirectory(new File(DIRECTORY_TO_XML));
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(ACCEPTED_XML_DESCRIPTION, ACCEPTED_XML_EXTENSION));

                /* Show open file dialog to select multiple files. */
                fileList = fileChooser.showOpenMultipleDialog(null);
                if (fileList != null && fileList.size() > 0) {
                    labelFile.getChildren().clear();
                    for (File file : fileList) {
                        labelFile.getChildren().add(new Label(file.getPath()));
                    }
                }
            }
        });

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);
        vBox.getChildren().addAll(btn, labelFile);

        StackPane root = new StackPane();
        root.getChildren().add(vBox);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
