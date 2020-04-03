# Design
> By Max Smith and Eric Doppelt

### Introduction
> This section describes the problem your team is trying to solve by writing this program, the primary design goals of the project (i.e., where is it most flexible), and the primary architecture of the design (i.e., what is closed and what is open). Discuss the design at a high-level (i.e., without referencing specific classes, data structures, or code).

Our final project is focused on creating a flexible game engine that supports card-based games, such as poker or blackjack. The functionality of the program should resemble that of a small, online casino, wherein multiple games can be played and a bankroll can be managed.

The primary design goal of the program is to be data driven, in the sense that a new game type can be trivially created through the modification of data files. The only limiting factor to new game types is having Java code to support the data tags. We will identify overlap between games in our genre and the concepts that vary, and then encapsulate them in an abstraction leveraging substitution and composition design patterns to create novel games.

With this mind, precoded games will only be able to be modified via data files that address rules, preferences, and logic of each game. The actual implementation of any game will be closed, and the opennness will be mainly through these data files and subclasses of abstract, similar game components. 

Furthermore, we will be utilizing the MVC design pattern to implement the game. In this sense, the View will be the Player, and the engine will be initialized with the Data, and run by the controller and model. Once these three main objects have been created, they will all communicate through an explicitly defined network of internal/external API calls.


### Overview
> This section serves as a map of your design for other programmers to gain a general understanding of how and why the program was divided up, and how the individual parts work together to provide the desired functionality. Describe specific modules you intend to create, their purpose with regards to the program's functionality, and how they collaborate with each other, focusing specifically on each one's API. Include a picture of how the modules are related (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a UML design program). Discuss specific classes, methods, and data structures, but not individual lines of code.

Our program is divided up into the following modules:
* Game Generation
* XML Parsing
* Card Distribution
* Hand Evaluator
* Hands Comparison
* Bet Evaluator
* Table
* Action Generator
* GameView
* LobbyView

![](https://i.imgur.com/5xVDisT.jpg)

The general structure is that of an MVC (Model - View - Controller). The View itself is a tree structure of subcomponents which will all take the form of JavaFX elements. The View will be rendered by the Controller. The Game logic is dictated by data files (our format of choice is XML) and encompassed within the HandEvaluation, AvailableActions, and GarbageCollection.

When a user wants to join a game, they select an XML file which is then parsed by an XML reader (plus additional writing capabilities for user selected preferences @ run time) and then a GameConstructor generates the remaining objects in the ecosystem. They are designed to work in their own universe, so multiple games can be constructed and run independently of eachother.

Within an individual game, the Controller manifets itself in a `Table` which controls the game flow (e.g. prompts players for actions). Upon action selection, a Hand is then assembled and processed via a HandEvaluator and referenced against preconfigured (via XML file) hands (e.g. `StraightFlush`).

At the end of a Game cycle, all `Bets` (which have a `Hand` and `Wager`) in the same competitive circle are evaluated against eachother using a `compareTo` method inside of the `HandComparator`, resulting in one (or more in the case of ties) winners who split their shared prize pool (the pot).

### Design Details
>This section describes each module introduced in the Overview in detail (as well as any other sub-modules that may be needed but are not significant to include in a high-level description of the program). Describe how each module's API handles specific features given in the assignment specification, what resources it might use, how it collaborates with other modules, and how each could be extended to include additional requirements (from the assignment specification or discussed by your team). Finally, justify the decision to create each module with respect to the design's key goals, principles, and abstractions.

#### Game Generation

*Functional Goal:*
To use the XML Parsing external API to construct the Game ecosystem.

*Classes and Objects:*
There will be a `CardFactory` which generates individual `Card` objects, packages them in a List which is held by the `Deck`, and then the `Deck` is given to the `Dealer`.

There will be a `PlayerFactory` which creates `Player` objects that store a bankroll and an initially empty collection of `Bets` that are populated later on.

A `Table`, serving as a controller, is constructed with a List of `Players`, a `Dealer`, a `HandClassifier` (identifies possible hands), a `HandEvaluator` (contains bet hierarchies), and a `BetEvaluator` (marks winning tickets with optional payoff multipliers, default to 1). It creates an XMLReader to read the file given to it by the frontend.

*External API Contract:*
As of now, there is only one method call in the external API for GameGeneration which takes in an XML file.:

```java3
public void createGame(File file)
```

#### XML Parsing

*Functional Goal:*
To translate an XML file into data structures containing the user preferences in Java-friendly formatting.

*Classes and Objects:*
There will be an `XML Reader` and `XML Writer`. The `XML Reader` parses data and contsructs native Java Objects (e.g. Maps, Lists, etc.) from predefined XML tags. One example of this is the ability to parse a Deck XML file and create a Collection of Cards which is later defined as a `Deck`.

*External API Contract:*
The XML Parser will be prompted by the Game Generator Module for specific tags (e.g. DeckStyle). The GameGenerator module will then use this data (now in Java) to construct the appropriate objects.

```java3
public XMLReader(File file)

public Map<String, Double> getDeck()

public Map<String, Double> getPayoutOdds()

public List<String> getWinningHands()

public List<String> getLosingHands()

public Map<String, List> getActions()

public Map<String, Double> getPlayers()
```

#### Card Distribution
*Functional Goal:*
To hand out `Card` objects from a data-defined `Deck` to `Hands`.

*Classes and Objects:*
`Card` wraps information regarding a Suit and a Value, where Suit is an enumerated String type and Value is a double. These values are defined by a deck XML file, where each line represents a card. The `CardFactory` class takes the pair of suit and value found in the data, and constructs a `Card` object with it.

A `Deck` encapsulates a collection of `Card` objects.

A `Dealer` holds a `Deck`. It can deal individual cards to players, or it can deal communal cards to the whole table. These cards are randomly generated. After a card has been distributed, the dealer removes it from the deck, until it is shuffled again. To ensure data is maintained throughout multiple rounds, the dealer stores two decks, where one is the full collection of the cards, and the other is a subset with dealt cards removed.

*External API Contract:*
This module only needs to be able to distribute cards on command and shuffle the deck. As such, the API calls (by the `Table`) are:
```java3
public void shuffle()

public Card dealCard()
```

#### Hand Classification

*Functional Goal:* 
To identify a generic Hand as a specific type (e.g. Full House, Pair). A Hand Classifier is simply a class that can identiy whether or not a group of cards has a certain pattern to it.

*Classes and Objects:*
There will be a variety of classes that all check for a specific card pattern. The ones that we identify as relevant for card-based games are `HighCard`, `Pair`, `Triple` `TwoPair`, `Straight`, `Flush`, `FullHouse`, `Four of a Kind`, `StraightFlush`, `RoyalStraightFlush`, `UnderX` (checks if the sum of cards is under a number X), `OverX` (checks if the sum of cards is over a number X) `TwoCardSumX`, (checks if two cards add to a number X).

Additionally, there will be a hierarchy of `ClassifiedHands` that are instance variables.

*External API Contract:*
```java3
public void classifyHand(Hand h)
```

*Internal API Contract:*
Note, we see some duplication here and will likely refactor to a single loop over all possible card combinations given your hand, and then in order of the winning hand hierarchy. Using reflection to check conditions and then assignment of classified hand with another reflective call to construction.
```java3
private boolean isFlush(Hand h) {
    if (suitsMatch(h)) {
        h.setClassifiedHand(new Flush())
    }
}
```

#### Hand Evaluation

*Functional Goal:*
To compare two classified hands based on the hierarchy given via XML. This is done in two stages. First, the rank in the hierarchy given is checked; if equal hands, the value of the card is checked.

*Classes and Objects:*
There is one class, `HandComparator` that only implements one relevant method: compare(Hand one, Hand two). This takes in two hands and returns 1 if the former is the winner, -1 if the latter is the winner, and 0 if it a push.

*External API Contract:*
The single method call:
```java3
public int compare(Hand h1, Hand h2)
```

*Internal API Contract:*
Internally, there are two helper methods to support the external facing function.
```java3
public int compareHierarchy(ClassifiedHand ch1, ClassifiedHand ch2)

public int compareHighCard(ClassifiedHand ch1, ClassifiedHand ch2)
```

#### Bet Evaluation

*Functional Goal:* Allows bets to be marked as winners or losers, and if winners, have their values updated based on payout odds/multipliers. It evaluates bets in a competitive circle (either full table or dealer against player)

*Classes and Objects:*
One class is of value here which is `BetEvaluator`. This takes in a collection of bets and, using the hand evaluator, determines the winning hand. Then, using the payout odds read in through the FileReader, it updates the value of the ticket. 

*External API Contract:*
```java3
public void evaluateBets(Collection Bets)
```

#### Table

*Functional Goal:*
The `Table` is acting as controller and coordinates the logic of the game, as given to it by the `GameGenerator`. It determines the sequence of events, as dictated by XML files (e.g. card distribution, bet payoffs, hand hierarchy, number of players, etc.).

*Classes and Objects:*
A `Table`, serving as a controller, manages the game by dealing with `Players`, their `Bets`, a `Dealer`, and a series of evaluators and comparators for `Hands` and `Bets`. It also interacts with `Actions` as given below, to run turns of each game.


*External API Contract:*
There are no external API calls on the Table beyond its construction (which may require various setters) within the GameGeneration. The entire game functions within this controller.

#### Action Factory

*Functional Goal:* To create unit-like actions that can affect Bet objects, such as classics of Hit and Check in poker games.

*Classes and Objects:*
There is an inheritance heirarchy within our action module wherein every concrete `Action` is a subclass of an abstract Action that defines one method (Command design pattern).

Then, an `ActionFactory` will be capable of producing these Action subclasses based on reflection using the XML tags.

*External API Contract:*
```java3
public Action createAction(String name)

public void execute(Bet target)
```

#### GameView

*Functional Goal:* To display the poker game in the UI.

*Classes and Objects:*
The `TableView` class is just a large composition of classes that exist to represent back-end objects. The following classes are needed: 
1. `PlayerView`: Displays Name, Bankroll `BetView`s and `ActionBoxView`
2. `BetView`: Displays cards and wager with `CardView`s and `WagerView` wrapped in HBox.
3. `CardView`: Displays cards in classic, rectangular format with their value. The default is off, and they will be toggled on during your turn.
4. `WagerView`: Shows a numeric formatted into a JavaFX Node is large font to be included in the BetView.
5. `AdversaryView`: Displays the "dealer" figure that is a player's one oppoenent with hidden cards.
6. `OpponentView`: Displays all (expanded or simplified) `PlayerViews` of competitors in an HBox.
7. `TableView`: Displaying an image as specified in data files for the chosen game type (e.g. classic casino table).
8. `ActionBoxView`: Shows all possible actions that a user may take, which is delivered via the Controller.
9. `MenuView`: Simple Box that opens a new pane with options including Dark Mode and return to lobby.
10. `ErrorView`: Displays a warning PopUp with a message given via the controller
11. `WagerInputView`: PopUp used to place a bet via a slider from a minimum value to a maximum given by the Controller.

*External API Contract:*
The external API will be filled with render calls to the various components as they are update in normal operating conditions. There will additionally be a pipeline to dispaly errors. Some examples are below:
```java3
public void displayExceptions(Exception e)

public void renderTable(Image image)

public void selectAction(Player p1)
```

#### LobbyView

*Functional Goal:* The goal of the lobby is for the user to be welcomed into the game environment with a splash screen. It allows for the user to implicitly select an XML file.

*Classes and Objects:*
Each XML file has a `GameIconView` representation. When selected, the icon acts as a link, resulting in the construction of a Game via the XML Parsing module.

*External API Contract:*
The only public API call is to render the lobby, which can be done at any time (upon game startup, or can be entered after exiting a Casino).

#### Justifications and Handling Features
`XML Parsing`: The XML parser is needed to support the data-driven nature of our game. Our group went with XML parsing because the use of tags makes it easy to identify subsections within the file that can be changed to manage specific aspects such as payout odds and handle heirarchy. This makes the game flexible and supportive of all game types. It also supports the Save Game and Load Game functionalities.

`Card Distribution`: The Cards are needed obviously as we are doing a card-based game genre. This module supports the data-driven of the game because it interfaces with the XML Parser to construct Decks from individual files. This allows our game to accomodate any variation in Decks, since one can just list out the cards.

Moreover, the use of a Dealer encapsulates the card data and randomization to best match the actual structre of a poker game. The simple API of getting a card further allows any type of game with differing amounts of cards, since the XML only needs to tell the dealer how many times to deal. This is a specific extension of changing Game Rules by affecting the Deck.

`Hand Classification`: This module is universal to all games and provides the commonality between poker types that allows us to build a universal card game engine. By identifying poker types and even hands in blackjack (such as literal blackhacks and over-21 hands), the classifier allows our program to recognize types of hands. This creates a "common language" which a user can then work with via XML to change the hierarchy of hands that is used to judge, as explained below.

Moreover, the classifier works with our "garbage collector" in the Table to recognize hands that should be removed from gameplay.

`Hand Evaluation`: This module allows for significant changes to the Dynamic Game Rules. Essentially, after the classifier identifies each hand in the game, the evaluator compares them via the heirarchy given by the XML file. 

This flexible nature of the evaluator allows the engine to run any type of card game that can be repersented by hand-based heirarchies. In the future, if we implement Roulette, which we hope to do, this will allow the implementation, since winning tickets can be viewed as Pairs (which can be identified) between the bet ticket and wheel value.

`Bet Evaluation`:
The bet evaluation allows for flexibility in its payout structure. By working with the XMLReader to obtain a map of payout odds, the user can easily set this. Moreover, via basic setter methods, a Game Area Editor could manipulate these payout odds to allow blackjacks to pay out 10 to 1, for example.

`Table`: This is an all purpose controller that centralizes the game in one place. It is needed to bridge the frontend to the backend, and it also works with the Action Factory to allow Substution to take place in the Controller. The abstract inheritance model specifically supports this substition in the Controller, since all concrete actions must have an `execute()` method.

`Action Factory`: This works with the table to create mid-game functionality that is data-driven from the XML. The Action objects are all subclasses and can be replaced in the Table controller. This allows us to support games that have different mid-turn options, such as Blackjack allowing Hits and Poker allowing raises.

`LobbyView`: The Lobby View is necessary since it can be rendered before game preferences have been set. This allows the Game Engine to run its course prior to initializing a GameView, as described below. Moreover, in the LobbyView, a Game Aread Editor could later be added, where it could work with the XMLWriter to save preferences and change in-game settings.

`GameView`: The Game View allows the game to be run, and it also allows users to pick their own mid-game actions. There is no extended functionality that this really offers, but it is certainly necessary to view the model and allow user input, which the Table then operates on.

### Example Games
> Describe three example games that differ significantly in detail. Clearly identify how the functional differences in these games is supported by your design. Use these examples to help clarify the abstractions in your design.

We are planning on implementing at least three games. Upon careful reflection (no pun intended), we have identified several key commonalities between each game type, and have attempted to abstract the concepts that change into data files. 

#### BlackJack
Our first game is blackjack. For clarifications, a good tutorial is found [here](https://www.blackjackapprenticeship.com/how-to-play-blackjack/). One major functional difference between Blackjack and games in the poker family is who the competitive circle is (e.g. the entire table, or just the dealer). We have abstracted this difference into an XML tag, and a Blackjack game will have an Adversary (dealer) who plays against the players. The UI supports this by having an AdversaryView which is optionally dispalyed based on this tag.

Additionally, we have broken the game of Blackjack into "poker-like" terms, wherein hands can be evaluated against each other via a simple hierarchy given in XML tags that state that a blackjack beats a under-21 sum, which beats an automatically losing over-21 sum. Moreover, we have added flexibility in the sense that we have defined Hands to be UnderX, OverX, and TwoCardSumX. This allows the number 21 to be viewed as arbitrary, and the game can be played where a BlackJack is a two card sum equalling 11 instead.

Lastly, we support casino-like payout odds for Blackjacks (which is about 1.5 to 1) by allowing these preferences to also be saved in the data.


#### Texas Hold'em
Our next game is texas holdem, where players compete against eachother. Additionally, to address the dual goals of:
1. Being the last player standing
2. Having the best hand at showdown

We have developed a system of garbage collection and tagging bets which filters out losing hands. As `Actions` are selected by the user, `Hands` are classified, and if they are classified as a `LosingHand`, then they are garbage collected. Or if there are multiple players at showdown, there is a `BetEvaluation` module which iterates over all combinations of bets, compares their best (we only care about the best bet) classifications using a `HandComparator`, and assigns Win and Loss. Finally, the table calls all Bets to be *cashed*. When this call is made, winning bets cash based on their competitive circle (Poker hands cash their portion of the pot).  This enables a single logical process to govern all games.

#### Five-Card Draw

This game is a natural extension to our poker setup and shows the ease that one gets when implementing a new game.

The distinction between five-card draw and texas hold'em boils down to little more than a few XMl tags. The mid-turn Actions that a user can take is modified to implement a replace card action, as is allowed in the game (and not hold'em). Moreover, the number of cards distributed to a player is changed in the XML, but aside from that, little has to be done, especially the games implement the same ranking heirarchy.

If one wanted to change this heirarchy, it would be extrememly easy. If someone wanted a two-pair to be the best possible hand, they could restructure the XML tag to look like the following:

```htmlmixed
<Hierarchy>
    <WinningHands>TwoPair, RoyalStraightFlush, StraightFlush, etc. </WinningHands>         
    <LosingHands>Fold</LosingHands>
</Heirarchy>
```

### Design Considerations
>This section describes any issues which need to be addressed or resolved before attempting to devise a complete design solution. Include any design decisions discussed at length (include pros and cons from all sides of the discussion) as well as any ambiguities, assumptions, or dependencies regarding the program that impact the overall design.

One question that constantly arose is to what extent are players self-automating their actions. As it stands, the Controller and the User manipulate *every* action that may be taken. However, this may shift too much responsability onto the Controller, and it may be reasonable to create a functionality in the adversary, for example, that tells it to automatically hit until 16. From a conceptual standpoint, it makes sense to encode this feature in the player; however, as it stands, players are nothing more than a model to store individual data, and implementing this change for the adversary breaks that current structure, so we are currently keeping that functionality in the controller.

Another question is how to determine winners in various game types (e.g Player vs. Dealer, Player(s) vs. Player (s), etc.). To allow for a single process to govern game types of arbitrary numbers of players, we decided to abstract the concept into a data tag called CompetitiveCircle. The Controller (`Table`) understands this circle, and feeds collections of `Bets` through the `BetEvaluator`, whose sole functionality is to determine Winners and Losers in a collection of Bets by using a `HandComparator`.

Lastly, we recognize that our Tabel class blends the line between Model and Controller. It holds player data and operates on it frequently using Action classes. We believe that this Table is necessary for our project, since its ability to access data and manipulate via Actions allows this substition to create great flexibility within in mid-turn functionality.

**Note**: We are going to also do our best to implement Roulette, as we think our current design can handle it well. However, due to the plethora of bet options, we are still hashing out a way to make the game data-driven. After talking with Prof. Duvall, his advice was to focus our attention on card games now, while creating a design that could incorporate it in the future. We believe that we have done this, and the only addition that would be needed for Roulette would be a customizable Bet Tag in every XML file detailing what types of specific bets are allowed.

