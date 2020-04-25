package UI.GameView;

/*
@Deprecated
class GameViewTest extends DukeApplicationTest {

    private FxRobot myRobot;
    private BorderPane myGamePane;
    @BeforeEach
    public void setUp() throws Exception {
        launch(Main.class);
        myRobot = new FxRobot();
        Button myBlackJackGameIcon = myRobot.lookup("#blackJack-icon").query();
        myRobot.clickOn(myBlackJackGameIcon);
    }
    // Happy Path :)
    @Test
    public void testGoodWagerInput() {
        Button readyButton = myRobot.lookup("#ready-button").queryButton();
        myRobot.clickOn(readyButton);
        DialogPane wagerPane = myRobot.lookup("#wager-dialogue-pane").query();
        assertEquals(wagerPane.getContentText(), "Enter a bet from 5.0 to 100.0 please!");
        myRobot.write("100");
        myRobot.clickOn(1015, 450);
        HBox wagerView = myRobot.lookup("#wager-view").query();
        Label inputedName = (Label)wagerView.getChildren().get(1);
        assertEquals("100.0", inputedName.getText());
    }

    // Sad Path :(
    @Test
    public void testBadWagerInput() {
        Button readyButton = myRobot.lookup("#ready-button").queryButton();
        myRobot.clickOn(readyButton);
        DialogPane wagerPane = myRobot.lookup("#wager-dialogue-pane").query();
        assertEquals(wagerPane.getContentText(), "Enter a bet from 5.0 to 100.0 please!");
        myRobot.write("-1");
        myRobot.clickOn(1015, 450);
        assertThrows(EmptyNodeQueryException.class, () -> myRobot.lookup("#wager-view").query());
    }

    // Happy Path :)
    @Test
    public void testColorPicking() {
        ComboBox languagePicker = myRobot.lookup("#css-combo-box").query();
        myRobot.clickOn(languagePicker);
        myRobot.clickOn(1166, 208);
        myGamePane = myRobot.lookup("#game-border-pane").query();
        assertEquals(myGamePane.getStylesheets().size(), 1);
        assertEquals(myGamePane.getStylesheets().get(0), "Coral.css");
    }

    public void clickReadyforInput() {
        Button readyButton = myRobot.lookup("#ready-button").queryButton();
        myRobot.clickOn(readyButton);
    }
}
*/
