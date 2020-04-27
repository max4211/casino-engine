# API_CHANGES.md

## Overview
As a whole, the majority of our APIs have held up in the final implementation of our project. Specifically model/controller/data APIs, who all had an extensive design period have remained consistent throughout the project.

External APIs to the view have had considerable change. The `GameView.java` class has become a pseudo-controller within the View, routing method calls to their appropriate nodes. However, we would like to note that all of these changes were in response to new functionality/features that we had not envisioned at the start of the project.

A key feature of our APIs is that they are all included in an interface and overriden in the implementing class. This allows us to easily identify the available public method calls on an object, and helped in the design by contract development pipeline.

## Specific Changes

### Action Factory
The original `ActionFactoryInterface` method which returns an Action after a call of `Action createAction(String)` still exists; however, it has been slightly modified. There are now two ActionFactories (`IndividualActionFactory` and `GroupActionFactory`) which create the two types of Actions in the game.

Additionally, all Actions now follow the command design pattern and execute with a set of lambdas. Upon diving deeper into development, it became apparent that to ensure we had funcitonal objects actions would  need more than just a `Bet` object to execute on. This also ensures that the `Controller` is not a pure manager class. For example, an `Action` in a group game needs an ability to get specific cards. However, to preserve the Model View separation, we decided to pass in a functional interface as a parameter to the Action.

Example `GroupAction.execute()` method:
```java3
    void execute(Player p, Bet b,
                 WagerSelector selectWager, Consumer<Double> setTableBet, Consumer<Bet> activatePlayers,
                 double tableMin, double tableMax, double currentBet) throws ActionException;
```

### Bet Evaluator
Bet evaluation was appended to include an evaluator on only two PlayerHands to accomodate `Adversary` game types. The change was relatively minor, and was required
in order to reinforce the simplicity in the Adversary object (it only encapuslates a hand, doesn't need a Bet).

### XMLParserInterface
The XMLParserInterface has had a fair amount of changes. In the development of additional games, we realized that many pieces that construct a game are completely independent (e.g. Deck, Players, and Game Sequence). We decided to separate these components into independent files.

Additionally, to ensure that we did not have a single 600+ line XMLParser which can parse all files and had many unused methods for specific files, each file has its own associated XMLReader (e.g. `Deck.xml` has a `DeckReader.java` which implements a `DeckReaderInterface`).

Example `XMLReader` interfaces:

```java3
public interface DeckReaderInterface {

    /**
     * Method that translates the XML Deck tag to a List of Pair objects holding the Suit (String) and Value (Integer) for each Card.
     * Called in CardDistribution module to assemble the deck.
     *
     * @return a List with each Pair entry containing the suit and value of a card in the deck.
     */
    List<StringPair> getDeck();

}

public interface PlayerReaderInterface {

    /**
     * Translates the XML tag for players in a game into a Map
     * The String keys are the names for the players, and the Doubles are their initial bankroll
     *
     * @return a collection of pairscontaining all the information for players mapping their names to their bankrolls
     */
    Collection<Pair> getPlayers();
    
}
```

### GameView

Generally, the API of the GameView was changed the most during the first two Sprints. It became apparent that our team was more focused on the backend design during the planning process, and multiple times the front-end needed to add new methods to support some extended functionality. Additionally, a variety of methods that aimed to make the View mutable with ID tracking for specific rendered nodes were added and then removed. This was because the UI was originally going to render itself all-at-once on every change. It was then modified to only change specific nodes on individual turns. However, it was then changed back to ensure total separation between the model and the view. As such the changes are broken up into these two categories below. 

**Changes from Tracking System**

1. *The Adversary*


The adversary is *not* always rerendered, so we split this method up into three new methods in the second Sprint in order to allow it to be "mutable". The three methods are as follows:
	
	a. void renderAdversary(List<CardTriplet> hand);
	b. void showAdversaryCard(int cardId);
	c. void addAdversaryCard(CardTriplet cardID);
	
The `renderAdversary(List<CardTriplet> hand)` has the functionality of the original `public void renderAdversary();` method  but instead of adding cards to the adversary via a tracking nmber, they instead are added with specific calls. Additionally, a `void clearAdversary();` method was added to make rerendering (which is covered in the other subsection) easier.

2. *Card Handling*

At first, cards were added using the three methods:

	a. void giveCard(BetView b, Card c);
	b. public void showCard(BetView b, Card c);
	c. public void showCards(BetView b);

This was changed to the following three methods:

	a. void addCardIfAbsent(CardTriplet cardInfo, int playerID, int betID);
	b. void showCard(int playerID, int betID, int cardID);
	c. void hideCard(int playerID, int betID, int cardID);

This change supported the all-at-once rerendering after user input. The backend now adds every card to a hand without worrying about duplicating a hand. It then calls every card to either show or hide itself. Once again, if a card is already being shown, the call does nothing. This allowed the backend to update everything without worrying about changing existing statuses. Additionally, cards are always added on a individual basis now, only for the reason of simplication in the backend.

3. *Bet Handling*

The following three methods were added soon after the project began. At first, we believed that a BetView would render itself if a Card was added via an ID that did not exist. That is, if `public void giveCard(BetView b, Card c);` was called on a non-existent BetView, it would then create itself. This was changed, however, since the Controller has no need to know about individual BetView objects. Instead, bets are created with IDs now, which allows both the controller and view to create an understanding of which ID points to which bet without having View objects inside the Controller.

	a. void addBet(List<CardTriplet> handInfo, double wager, int playerID, int betID);
	b. void removeBet(int playerId, int betId);


4. *Clearing*

Instead of individually deleting every card, the UI was modified to create "clearing" methods which would allow the backend to easily wipe the screen the new game. These methods are as follows:

	a. void clearAllBets();
	b. void clearAdversary();


**New Functionality**

1. *NodeView Interface*

The `void renderGame(File image);` method was refactored into a `getView()` method which displays the object's view representation. This method is a part of an interface that most classes in the UI now use, adding the implementation as a public method.

2. *Player Information*

Players were added to the UI. They were lacking in the original API which was going to be only bet-centric. At first, bets would include their player name. However, we soon realized that having information including a player's wager and bankroll would be beneficial. As such, the following methods were added, which also implement the tracking system. Here, a Player can be added, and he is then either set to the Main Player, or an Other Player. The Main Player is in the center of the game, while the other players are on the side.

	a. void addPlayer(String name, int playerId, double bankroll);
	b. void setMainPlayer(int playerId);
	c. void setWager(double newWager, int playerID, int BetID);
	d. void setBankRoll(double newBankroll, int playerID);

3. *Common Cards*

At first, the adversary was going to include the common card functionality. That is, common cards could be rendered via an adversary's hand. This was based on the fact that no standard game that we could think to implement had both common cards *and* an adversary. However, we then realized that a *custom*, data-driven game could do such a thing. Adding common cards occurred in the second sprint, when we implemented the poker-blackjack hybrid, realizing that this would allow more oddball data combinations.

	a. void renderCommonCards(List<CardTriplet> hand);
	b. void showCommonCard(int cardID);

4. *Wager Selection*

The original API had an Action Selector; however, we soon realized that Wager selection is also needed user input. This was added quickly in the first sprint and has extremely similar functionality.

	a. double selectWager(double minBet, double maxBet);

5. *Nuances*

After our Sprint 1 demo, Prof. Duvall recommended a few nuances to our game. These were to make players have a "losing" color that can be set after a folded hand. We were also recommended to create a button that starts a new game, as opposed to simply running a new game immediately. Lastly, a splashscreen depicting the results of the game was added to further add clarity. This manifests itself with the following calls.

	a. void promptNewGame(GameCaller startNewGame);
	b. void displayText(String s);
	c. void setLoser(int betID);

### Internal GameView Nodes

The individual components of a GameView were broken up into Nodes. Nearly all of the above method calls, which are external to the View, are also internal method calls. Via a large composition in GameView, these external calls are mapped to smaller components, which then enact the change internally to the UI. For example, adding a bet via GameView's `addBet()`, calls an internal `addBet()` method on a PlayerView object.

Additionally, many classes now implement a `TaggableInterface` which specifies a `public boolean hasSameID(int ID);` method. This was used to implement the tracking system.

Lastly, a 'LanguageBundleInterface' was added. Many nodes have text that needs to be rerendered on a lanuage change, so the `    void updateLanguage(String newLanguage);` adds this functionality.

### LangaugeBundle

A wrapper object around a ResourceBundle was created, which allows GameView Nodes to have a pointer to the same language file. This was not considered in our original plan, so a new internal Language compoment was added to the UI. This has the following two methods and was added towards the end of sprint two.

a. `public void setLanguage(String language);`
b. `public ResourceBundle getBundle();`

Note that these are not changes to an existing API but instead a new API that was created.

### LobbyView

As mentioned earlier, LobbyView implements the `NodeView` interface which has added a `getView()` method. This was added in the second sprint.