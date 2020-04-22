package UI.WebView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;
import java.net.URL;

public class Browser implements BrowserInterface {

    private static final String HTML_FILE = "resources/website/index.html";
    private static final String STYLESHEETS = "website/style.css";
    private static final String TEST_PAGE = "https://stackoverflow.com/questions/35703884/trying-to-load-a-local-page-into-javafx-webengine";

    private WebView myWebView;
    private WebEngine myWebEngine;
    private TextField myURLDisplay;

    public Browser() {
        this.myWebView = new WebView();
        this.myWebEngine = this.myWebView.getEngine();
        File file = new File(HTML_FILE);
//        String url = file.toURI().toString();
        this.myWebEngine.load(TEST_PAGE);
    }

    @Override
    public Scene getScene(int width, int height) {
        BorderPane root = new BorderPane();
        root.setCenter(makePageDisplay());
        Scene scene = new Scene(root, width, height);
//        scene.getStylesheets().add(STYLESHEETS);
        return scene;
    }

    private Node makePageDisplay() {
        return this.myWebView;
    }
}
