package UI.WebView;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;

public class Browser implements BrowserInterface {

    private static final String TEST_PAGE = "http://www.casino308.com";

    private WebView myWebView;
    private WebEngine myWebEngine;
    private TextField myURLDisplay;

    public Browser() {
        this.myWebView = new WebView();
        this.myWebEngine = this.myWebView.getEngine();
        this.myWebEngine.load(TEST_PAGE);
    }

    @Override
    public Scene getScene(int width, int height) {
        BorderPane root = new BorderPane();
        root.setCenter(makePageDisplay());
        Scene scene = new Scene(root, width, height);
        return scene;
    }

    private Node makePageDisplay() {
        return this.myWebView;
    }
}
