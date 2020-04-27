# Post DESIGN.md

## By Max Smith and Eric Doppelt
[Casino308.com](http://casino308.com/)

## Roles
Both Max and Eric spent time designing the overall game, with an emphasis on game engine and data as well as touching on the view macro functionality.

Max was the backend developer. He implemented the game engine and handled the design of the game engine, controller and xml generation, parsing, and validation with regular input from Eric.

Eric was the frontend developer. He implemented the UI and its related classes (GameView, LobbyView, Validation, Settings, Utilities, Icons). He handled the overall design of the View with regular input from Max. Note that Max handled the WebView package and the Help Button functionality.

## Project's Design Goals
> What features are easy to add?

It is extremely easy to create new games as combinations of pre existing data files (to construct a complete game, one must supply a `Deck`, `Game,` `Hands`, `View` and `Players` file). There is a theoretically infinite amount of games that can be assembled, from new decks, new dealer sequences, new actions, etc.

The vast majority of the applicaiton is data driven. From aesthetic components (languages, icons, and css styling) to in game functionality (hand types, multipliers, dealer sequences, player actions) they all come from data files (xml and properties) and are validated by `XSD schema` files.

## High Level Design
> How do core classes interact?

The game is a pure MVC model. There is a central `Controller` object which mediates information exchange between the `Engine` (all game logic) with the `View` (all JavaFX). We preserved this separation as much as possible, leveraging lambdas throughout our code base to ensure that the model has no knowledge/dependencies on view frameworks. 

The `View` has no knowledge of any game logic, and is instructed by the `Controller` to render objects to reflect what is going on in the backend.  The gameplay sequence involves a single bet being placed to begin a round (selected in the view, registered in the model), followed by alternating Dealer Actions (distributing cards) and player actions.

Within the `Engine`, there is a `Table` (with an internal transitive layer of possession `Players` --> `Bets` --> `Hands` --> `Cards`). There is a separate parallel module which evalutes Hands (classification) and Bets (evaluation/comparison of hands).

![](https://i.imgur.com/79d4KDc.jpg)

![](https://i.imgur.com/UgUcy3R.jpg)

![](https://i.imgur.com/c5TsIii.jpg)

![](https://i.imgur.com/ZydPLUx.jpg)

As the above UML diagram shows, the View is composed into three sections. There is the `LobbyView` first. This contains `GameStarters`, a `SettingsBar`, and an `XMLChooser`. This allows user to click on preloaded games through XML (these are `GameStarters`) and set styling and get help through the `SettingsBar`. A XMLChooser is used to load a LobbyView data file if the one in `Main` is not valid.

Then, the files are transported to the Validation module through the `.getFiles()` method in a `GameStarter`. Here, the Files are displayed individually in a `FileDisplay`, and these are collected into one unified `AllFilesDisplay` which contains a List of `FileDisplays` that are presented. A `MultipleXMLChooser` is used to select files, if need be.

Lastly, the valid files are then sent to the backend to render the controller, and through them, a`GameView` is initialized. This contains many smaller, internal components via composition. It contains `PlayerView` objects which themselves are composed of `PlayerInfo` (Name and Bankroll) and a collection of `BetViews`. These objects contain a `BetInfo` (wager amount and classification) and `HandView`. These HandViews then contain a Collection of `CardViews`. This entire package represents a player in his or her totality.

Players are visualized through the `MainPlayer` and `OtherPlayer` classes. The former displays the `PlayerView` in the bottom center of the screen, and the latter wraps a VBox of `PlayerViews` that appear on the left side of the screen.

Additionally, like the `LobbyView`, a `SettingsBar` is rendered on top of the screen with the same functionality.

There are then three optional renderings which can be made. An adversary, which is a dealer, can be rendered in GameView, and this is just a `HandView` object. A set of Common Cards can be rendered, and this is also a `HandView` object. These components are stored differently, however, since some games could employ both.

Lastly, a `PotView` can be rendered in Poker games. This simply calculates the total of the bets on the table, and it appears on the right side of the `GameView`.

## Assumptions/Simplications
* You are only looking at/playing the game when it is your turn
* No changes will occur mid game (once you enter a table, those conditions will hold for the duration of gameplay).
* Aces are either ones or elevens, and cannot exist as both.
* Poker has a simplified set of hands, but it would easy to extend the others.


## Guide to Adding New Features

### Roulette/Craps Style Betting
Another game type which our application is designed to accomodate is a game type such as roulette/craps, where instead of placing a wager on a `Generic` bet/hand (like in Poker/Blackjack before you recieve your cards), bets are placed on a specific event occuring (e.g. a specific card or suit or sequence happening).

To accomodate such a change, a new `SoloController` class would need to be constructed which extends the abstract  `Controller`. The `Controller` class manages the flow of the game, and in these types of games there are repeated(or not) rounds of betting, where your bet is evaluated against what actually occured (this would likely be a parameter in the `Competition` XML tag). Additionally, the `SoloController` would need to override all abstract methods in the `Controller`

* inRoundLoop
    * Cylce through bets, continously allow bets to be placed
* postRoundLoop
    * Evaluate bets (based on dealing communal cards), update wagers (based on multipliers)
    * Above same as before, new step is to clear bets or not
* promptForEntrybet
    * Single player (or more, parametrize in XML) allowed to place bets
* promptForActions
    * Only action is new wager, would continuously call player wager placement
* computePayoffs
    * Same as poker, use communal cards and classify
* classifyHand
    * Include Win/Loss `HandOutcome` assignment, only evaluated against yourself

The View would need the ability for players to select from an enumerated collection of bettable events, in addition to the already existing wager placed on all bets(e.g. `Red` suit or a specific `Numer` landing in Roulette). Furthermore, once the Player selects their Bet (wager and event), they would then recieve that `Card` object (by requesting it from the dealer). The `Deck` that the dealer holds is different than in an Adversary or Group game because the cards are replaced (e.g. in Roulette when you bet on a `5`, there should still be a `5` in the wheel or it would not make sense to bet on that event).

### Adding New Hand Types
To add a new Hand Type, two changes must occur:
1. Create a new concrete implementation of `Hand`within the `engine.evaluator.HandType` package. Implement the evaluate method. Assign meaningful defaults (e.g. if parameters are missing) and power to the hand
2. Enumerate the allowable hand in the `xml/schema/handschema.xsd` file
3. Add the new hand class name, multiplier, parameters, and optional view name to the selected `HANDS_xxxx.xml` file

The rest is taken care of by reflection and classificaiton cycles.

### Goals, Actions, and CardShow Policies
Goals, Actions, and CardShow policies both follow the same delegation style structure. They are all generated via an Abstract Factory, into a command design pattern following object which is held by the view (during a game for `Goal` and `CardShow`, transient holding for `Actions`). Each new class would need to extend the Abstract class and implement the `execute` method (`show` in `CardShow` and `evaluate` in `Goals`). Additionally, these new values would need to be enumerated in the `GAME_xxx.xsd` schema file.

### Adding a New Custom, Data-Driven Game

First, one must create *five* XML files needed to run a game: Deck, Game, Hands, Players, and View, . They are well documented at this [link](http://casino308.com/). After doing so, one should create a new *bundle* in the LobbyView XML. This has three components: the name of the game, an image to associate with it, and the five files. The user must also include translations of the game  by adding to the langauage Property Bundles. After this, one must run their files through the schema and ensure their validation.

### Adding New Icons

To customize the *look* of the game, simply create new Icon Bundles. These are properties files that map keys to the names of images. This process is the same for *any* Icon Bundle. First, create a bundle matching keys to their desired icons. The Deck key, for example, represents an icon showing a deck. Further, note that all icons *must* be in the appropriate package in the `iconImages` resource subpackage. After doing so, add the proper package in the `iconBundles` resource subpackage.

After doing so, go into the XML View file that is related to the icons. File Statuses, File Types, and Lobby Icons are all found in the LobbyView XML file. Game Icons are added to a View file.

Lastly, one must add Schema validation for the new bundles. Simply add to the enumerated types for the icon bundle added the name of the new resource. This will have the Validator approve the new look!

### Adding a New Stylsheet

This is rather simple. Create a new CSS style in the `styleSheets` resource subpackage. Then, add the style sheet to two places. First, add it with a new XML tag in either a LobbyView or simple View file with a Stylesheet tag. Then, add the new name to its respective enumerated type in the corresponding Schema validation file. After doing so, the file will be added to the game!

### Adding New Card Designs

This can be done by utilizing the two aforementioned steps. Shown cards are styalized with CSS using the tag: `#shown-card`. Hidden cards are given as an Icon picture via the `HiddenCard` key in a Game Icon Bundle.





